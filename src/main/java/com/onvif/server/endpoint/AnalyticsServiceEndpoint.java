package com.onvif.server.endpoint;

import jakarta.jws.*;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.*;
import jakarta.xml.ws.BindingType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ONVIF Analytics Service – Profile T zorunlu servisi.
 * Video analitik kuralları ve modüllerini yönetir.
 * (nesne tespiti, yüz tanıma, plaka okuma vb. kurallara temel sağlar)
 * SOAP 1.2 / Document-Literal-Wrapped bağlaması kullanır.
 *
 * Endpoint: /onvif/analytics_service
 * Namespace: http://www.onvif.org/ver20/analytics/wsdl
 */
@Component
@WebService(
    serviceName     = "AnalyticsService",
    portName        = "AnalyticsPort",
    targetNamespace = "http://www.onvif.org/ver20/analytics/wsdl"
)
@SOAPBinding(
    style          = Style.DOCUMENT,
    use            = Use.LITERAL,
    parameterStyle = ParameterStyle.WRAPPED
)
@BindingType(jakarta.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class AnalyticsServiceEndpoint {

    /** Store: ruleToken -> rule description */
    private final ConcurrentHashMap<String, String> rules = new ConcurrentHashMap<>();

    /** Store: moduleToken -> module description */
    private final ConcurrentHashMap<String, String> analyticsModules = new ConcurrentHashMap<>();

    // ----------------------------------------------------------------
    // GetServiceCapabilities
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetServiceCapabilities")
    @WebResult(name = "Capabilities",
               targetNamespace = "http://www.onvif.org/ver20/analytics/wsdl")
    public String getServiceCapabilities() {
        return "RuleSupport=true AnalyticsModuleSupport=true " +
               "CellBasedSceneDescriptionSupported=false " +
               "RuleOptions=true AnalyticsModuleOptions=true";
    }

    // ----------------------------------------------------------------
    // GetSupportedRules
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetSupportedRules")
    @WebResult(name = "SupportedRules",
               targetNamespace = "http://www.onvif.org/ver20/analytics/wsdl")
    public String getSupportedRules(
            @WebParam(name = "ConfigurationToken",
                      targetNamespace = "http://www.onvif.org/ver20/analytics/wsdl")
            String configToken) {
        return "RuleType.tt:LineDetector RuleType.tt:FieldDetector " +
               "RuleType.tt:TamperDetector RuleType.tt:MotionRegionDetector " +
               "RuleType.tt:ObjectClassifier";
    }

    // ----------------------------------------------------------------
    // GetRules
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetRules")
    @WebResult(name = "Rule",
               targetNamespace = "http://www.onvif.org/ver20/analytics/wsdl")
    public List<String> getRules(
            @WebParam(name = "ConfigurationToken",
                      targetNamespace = "http://www.onvif.org/ver20/analytics/wsdl")
            String configToken) {
        return new ArrayList<>(rules.values());
    }

    // ----------------------------------------------------------------
    // CreateRules
    // ----------------------------------------------------------------

    @WebMethod(operationName = "CreateRules")
    public void createRules(
            @WebParam(name = "ConfigurationToken",
                      targetNamespace = "http://www.onvif.org/ver20/analytics/wsdl")
            String configToken,
            @WebParam(name = "Rule",
                      targetNamespace = "http://www.onvif.org/ver20/analytics/wsdl")
            List<String> newRules) {
        if (newRules != null) {
            for (int i = 0; i < newRules.size(); i++) {
                String token = "rule_" + (rules.size() + i);
                rules.put(token, newRules.get(i));
                System.out.printf("[Analytics] CreateRule: token=%s%n", token);
            }
        }
    }

    // ----------------------------------------------------------------
    // DeleteRules
    // ----------------------------------------------------------------

    @WebMethod(operationName = "DeleteRules")
    public void deleteRules(
            @WebParam(name = "ConfigurationToken",
                      targetNamespace = "http://www.onvif.org/ver20/analytics/wsdl")
            String configToken,
            @WebParam(name = "RuleName",
                      targetNamespace = "http://www.onvif.org/ver20/analytics/wsdl")
            List<String> ruleNames) {
        if (ruleNames != null) {
            ruleNames.forEach(name -> {
                rules.remove(name);
                System.out.printf("[Analytics] DeleteRule: %s%n", name);
            });
        }
    }

    // ----------------------------------------------------------------
    // GetSupportedAnalyticsModules
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetSupportedAnalyticsModules")
    @WebResult(name = "SupportedAnalyticsModules",
               targetNamespace = "http://www.onvif.org/ver20/analytics/wsdl")
    public String getSupportedAnalyticsModules(
            @WebParam(name = "ConfigurationToken",
                      targetNamespace = "http://www.onvif.org/ver20/analytics/wsdl")
            String configToken) {
        return "AnalyticsModuleType.tt:ObjectTracker " +
               "AnalyticsModuleType.tt:FaceRecognition " +
               "AnalyticsModuleType.tt:LicensePlateRecognition " +
               "AnalyticsModuleType.tt:ObjectClassifier";
    }

    // ----------------------------------------------------------------
    // GetAnalyticsModules
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetAnalyticsModules")
    @WebResult(name = "AnalyticsModule",
               targetNamespace = "http://www.onvif.org/ver20/analytics/wsdl")
    public List<String> getAnalyticsModules(
            @WebParam(name = "ConfigurationToken",
                      targetNamespace = "http://www.onvif.org/ver20/analytics/wsdl")
            String configToken) {
        return new ArrayList<>(analyticsModules.values());
    }

    // ----------------------------------------------------------------
    // CreateAnalyticsModules
    // ----------------------------------------------------------------

    @WebMethod(operationName = "CreateAnalyticsModules")
    public void createAnalyticsModules(
            @WebParam(name = "ConfigurationToken",
                      targetNamespace = "http://www.onvif.org/ver20/analytics/wsdl")
            String configToken,
            @WebParam(name = "AnalyticsModule",
                      targetNamespace = "http://www.onvif.org/ver20/analytics/wsdl")
            List<String> modules) {
        if (modules != null) {
            for (int i = 0; i < modules.size(); i++) {
                String token = "module_" + (analyticsModules.size() + i);
                analyticsModules.put(token, modules.get(i));
                System.out.printf("[Analytics] CreateAnalyticsModule: token=%s%n", token);
            }
        }
    }

    // ----------------------------------------------------------------
    // DeleteAnalyticsModules
    // ----------------------------------------------------------------

    @WebMethod(operationName = "DeleteAnalyticsModules")
    public void deleteAnalyticsModules(
            @WebParam(name = "ConfigurationToken",
                      targetNamespace = "http://www.onvif.org/ver20/analytics/wsdl")
            String configToken,
            @WebParam(name = "AnalyticsModuleName",
                      targetNamespace = "http://www.onvif.org/ver20/analytics/wsdl")
            List<String> moduleNames) {
        if (moduleNames != null) {
            moduleNames.forEach(name -> {
                analyticsModules.remove(name);
                System.out.printf("[Analytics] DeleteAnalyticsModule: %s%n", name);
            });
        }
    }

    // ----------------------------------------------------------------
    // GetOptions (rule/module options)
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetOptions")
    @WebResult(name = "RuleOptions",
               targetNamespace = "http://www.onvif.org/ver20/analytics/wsdl")
    public String getOptions(
            @WebParam(name = "RuleType",
                      targetNamespace = "http://www.onvif.org/ver20/analytics/wsdl")
            String ruleType,
            @WebParam(name = "ConfigurationToken",
                      targetNamespace = "http://www.onvif.org/ver20/analytics/wsdl")
            String configToken) {
        return "RuleType=" + ruleType + " Supported=true";
    }
}
