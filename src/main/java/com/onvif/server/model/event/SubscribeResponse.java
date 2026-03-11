package com.onvif.server.model.event;

import jakarta.xml.bind.annotation.*;

/**
 * Subscription manager reference returned by Subscribe and CreatePullPointSubscription.
 */
@XmlRootElement(name = "SubscriptionReference",
                namespace = "http://www.onvif.org/ver10/events/wsdl")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubscriptionReference",
         namespace = "http://www.onvif.org/ver10/events/wsdl",
         propOrder = {"subscriptionReference", "currentTime", "terminationTime"})
public class SubscribeResponse {

    @XmlElement(name = "SubscriptionReference",
                namespace = "http://www.onvif.org/ver10/events/wsdl")
    private EndpointReference subscriptionReference;

    @XmlElement(name = "CurrentTime",
                namespace = "http://www.onvif.org/ver10/events/wsdl")
    private String currentTime;

    @XmlElement(name = "TerminationTime",
                namespace = "http://www.onvif.org/ver10/events/wsdl")
    private String terminationTime;

    public SubscribeResponse() {}

    public SubscribeResponse(EndpointReference ref, String currentTime, String terminationTime) {
        this.subscriptionReference = ref;
        this.currentTime = currentTime;
        this.terminationTime = terminationTime;
    }

    public EndpointReference getSubscriptionReference() { return subscriptionReference; }
    public void setSubscriptionReference(EndpointReference ref) { this.subscriptionReference = ref; }

    public String getCurrentTime() { return currentTime; }
    public void setCurrentTime(String currentTime) { this.currentTime = currentTime; }

    public String getTerminationTime() { return terminationTime; }
    public void setTerminationTime(String terminationTime) { this.terminationTime = terminationTime; }
}
