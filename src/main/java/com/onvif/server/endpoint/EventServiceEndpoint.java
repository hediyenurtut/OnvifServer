package com.onvif.server.endpoint;

import com.onvif.server.model.event.EndpointReference;
import com.onvif.server.model.event.SubscribeResponse;
import jakarta.jws.*;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.*;
import jakarta.xml.ws.BindingType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ONVIF Event Service – Profile T zorunlu servisi.
 * WS-BaseNotification tabanlı; PullPoint ve Push aboneliklerini destekler.
 * SOAP 1.2 / Document-Literal-Wrapped bağlaması kullanır.
 *
 * Endpoint: /onvif/event_service
 * Namespace: http://www.onvif.org/ver10/events/wsdl
 */
@Component
@WebService(
    serviceName     = "EventService",
    portName        = "EventPort",
    targetNamespace = "http://www.onvif.org/ver10/events/wsdl"
)
@SOAPBinding(
    style          = Style.DOCUMENT,
    use            = Use.LITERAL,
    parameterStyle = ParameterStyle.WRAPPED
)
@BindingType(jakarta.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class EventServiceEndpoint {

    @Value("${server.port:8080}")
    private int serverPort;

    /** Active subscriptions: subscriptionId -> terminationTime */
    private final ConcurrentHashMap<String, Instant> subscriptions = new ConcurrentHashMap<>();

    // ----------------------------------------------------------------
    // GetServiceCapabilities
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetServiceCapabilities")
    @WebResult(name = "Capabilities",
               targetNamespace = "http://www.onvif.org/ver10/events/wsdl")
    public String getServiceCapabilities() {
        return "WSSubscriptionPolicySupport=true WSPullPointSupport=true " +
               "WSPausableSubscriptionManagerInterfaceSupport=false " +
               "MaxNotificationProducers=10 MaxPullPoints=10 " +
               "PersistentNotificationStorage=false";
    }

    // ----------------------------------------------------------------
    // GetEventProperties
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetEventProperties")
    @WebResult(name = "TopicNamespaceLocation",
               targetNamespace = "http://www.onvif.org/ver10/events/wsdl")
    public String getEventProperties() {
        return "tns1:RuleEngine//.* " +
               "tns1:VideoAnalytics/.* " +
               "tns1:Device/.* " +
               "tns1:VideoSource/.* " +
               "tns1:PTZ/.* " +
               "tns1:AudioAnalytics/.*";
    }

    // ----------------------------------------------------------------
    // Subscribe (WS-BaseNotification)
    // ----------------------------------------------------------------

    @WebMethod(operationName = "Subscribe")
    @WebResult(name = "SubscribeResponse",
               targetNamespace = "http://www.onvif.org/ver10/events/wsdl")
    public SubscribeResponse subscribe(
            @WebParam(name = "ConsumerReference",
                      targetNamespace = "http://www.onvif.org/ver10/events/wsdl")
            String consumerReference,
            @WebParam(name = "Filter",
                      targetNamespace = "http://www.onvif.org/ver10/events/wsdl")
            String filter,
            @WebParam(name = "InitialTerminationTime",
                      targetNamespace = "http://www.onvif.org/ver10/events/wsdl")
            String initialTerminationTime) {

        String subId = "sub_" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        Instant terminationTime = Instant.now().plus(1, ChronoUnit.HOURS);
        subscriptions.put(subId, terminationTime);

        String subAddress = "http://localhost:" + serverPort +
                "/onvif/event_service/subscriptions/" + subId;
        System.out.printf("[Event] Subscribe: id=%s consumer=%s%n", subId, consumerReference);

        return new SubscribeResponse(
                new EndpointReference(subAddress),
                Instant.now().toString(),
                terminationTime.toString());
    }

    // ----------------------------------------------------------------
    // Renew
    // ----------------------------------------------------------------

    @WebMethod(operationName = "Renew")
    @WebResult(name = "TerminationTime",
               targetNamespace = "http://www.onvif.org/ver10/events/wsdl")
    public String renew(
            @WebParam(name = "TerminationTime",
                      targetNamespace = "http://www.onvif.org/ver10/events/wsdl")
            String terminationTime) {
        Instant newTermination = Instant.now().plus(1, ChronoUnit.HOURS);
        System.out.printf("[Event] Renew until: %s%n", newTermination);
        return newTermination.toString();
    }

    // ----------------------------------------------------------------
    // Unsubscribe
    // ----------------------------------------------------------------

    @WebMethod(operationName = "Unsubscribe")
    public void unsubscribe(
            @WebParam(name = "SubscriptionId",
                      targetNamespace = "http://www.onvif.org/ver10/events/wsdl")
            String subscriptionId) {
        if (subscriptionId != null) {
            subscriptions.remove(subscriptionId);
        }
        System.out.printf("[Event] Unsubscribe: id=%s%n", subscriptionId);
    }

    // ----------------------------------------------------------------
    // CreatePullPointSubscription  (Profile T pull-mode events)
    // ----------------------------------------------------------------

    @WebMethod(operationName = "CreatePullPointSubscription")
    @WebResult(name = "SubscriptionReference",
               targetNamespace = "http://www.onvif.org/ver10/events/wsdl")
    public SubscribeResponse createPullPointSubscription(
            @WebParam(name = "Filter",
                      targetNamespace = "http://www.onvif.org/ver10/events/wsdl")
            String filter,
            @WebParam(name = "InitialTerminationTime",
                      targetNamespace = "http://www.onvif.org/ver10/events/wsdl")
            String initialTerminationTime,
            @WebParam(name = "SubscriptionPolicy",
                      targetNamespace = "http://www.onvif.org/ver10/events/wsdl")
            String subscriptionPolicy) {

        String subId = "pullsub_" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        Instant terminationTime = Instant.now().plus(1, ChronoUnit.HOURS);
        subscriptions.put(subId, terminationTime);

        String pullAddress = "http://localhost:" + serverPort +
                "/onvif/event_service/pullpoint/" + subId;
        System.out.printf("[Event] CreatePullPointSubscription: id=%s%n", subId);

        return new SubscribeResponse(
                new EndpointReference(pullAddress),
                Instant.now().toString(),
                terminationTime.toString());
    }

    // ----------------------------------------------------------------
    // PullMessages  (pull events from the PullPoint)
    // ----------------------------------------------------------------

    @WebMethod(operationName = "PullMessages")
    @WebResult(name = "PullMessagesResponse",
               targetNamespace = "http://www.onvif.org/ver10/events/wsdl")
    public String pullMessages(
            @WebParam(name = "Timeout",
                      targetNamespace = "http://www.onvif.org/ver10/events/wsdl")
            String timeout,
            @WebParam(name = "MessageLimit",
                      targetNamespace = "http://www.onvif.org/ver10/events/wsdl")
            int messageLimit) {
        // Returns empty notification list (no real events in this implementation)
        return "CurrentTime=" + Instant.now() +
               " TerminationTime=" + Instant.now().plus(1, ChronoUnit.HOURS) +
               " NotificationMessages=[]";
    }
}
