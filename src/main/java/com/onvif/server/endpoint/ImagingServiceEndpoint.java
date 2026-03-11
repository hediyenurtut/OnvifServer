package com.onvif.server.endpoint;

import com.onvif.server.model.imaging.*;
import jakarta.jws.*;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.*;
import jakarta.xml.ws.BindingType;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * ONVIF Imaging Service – Profile T koşullu servisi.
 * Parlaklık, kontrast, pozlama, beyaz denge ve odak ayarlarını yönetir.
 * SOAP 1.2 / Document-Literal-Wrapped bağlaması kullanır.
 *
 * Endpoint: /onvif/imaging_service
 * Namespace: http://www.onvif.org/ver20/imaging/wsdl
 */
@Component
@WebService(
    serviceName     = "ImagingService",
    portName        = "ImagingPort",
    targetNamespace = "http://www.onvif.org/ver20/imaging/wsdl"
)
@SOAPBinding(
    style          = Style.DOCUMENT,
    use            = Use.LITERAL,
    parameterStyle = ParameterStyle.WRAPPED
)
@BindingType(jakarta.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class ImagingServiceEndpoint {

    /** Per-videoSource imaging settings store */
    private final ConcurrentHashMap<String, ImagingSettings> settingsStore =
            new ConcurrentHashMap<>();

    // ----------------------------------------------------------------
    // GetServiceCapabilities
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetServiceCapabilities")
    @WebResult(name = "Capabilities",
               targetNamespace = "http://www.onvif.org/ver20/imaging/wsdl")
    public String getServiceCapabilities() {
        return "ImageStabilization=false Presets=true AdaptablePreset=false";
    }

    // ----------------------------------------------------------------
    // GetImagingSettings
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetImagingSettings")
    @WebResult(name = "ImagingSettings",
               targetNamespace = "http://www.onvif.org/ver20/imaging/wsdl")
    public ImagingSettings getImagingSettings(
            @WebParam(name = "VideoSourceToken",
                      targetNamespace = "http://www.onvif.org/ver20/imaging/wsdl")
            String videoSourceToken) {
        return settingsStore.computeIfAbsent(
                videoSourceToken != null ? videoSourceToken : "vs_main",
                k -> buildDefaultImagingSettings());
    }

    // ----------------------------------------------------------------
    // SetImagingSettings
    // ----------------------------------------------------------------

    @WebMethod(operationName = "SetImagingSettings")
    public void setImagingSettings(
            @WebParam(name = "VideoSourceToken",
                      targetNamespace = "http://www.onvif.org/ver20/imaging/wsdl")
            String videoSourceToken,
            @WebParam(name = "ImagingSettings",
                      targetNamespace = "http://www.onvif.org/ver20/imaging/wsdl")
            ImagingSettings imagingSettings,
            @WebParam(name = "ForcePersistence",
                      targetNamespace = "http://www.onvif.org/ver20/imaging/wsdl")
            Boolean forcePersistence) {
        if (imagingSettings != null) {
            String key = videoSourceToken != null ? videoSourceToken : "vs_main";
            settingsStore.put(key, imagingSettings);
            System.out.printf("[Imaging] SetImagingSettings: source=%s%n", key);
        }
    }

    // ----------------------------------------------------------------
    // GetOptions
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetOptions")
    @WebResult(name = "ImagingOptions",
               targetNamespace = "http://www.onvif.org/ver20/imaging/wsdl")
    public String getOptions(
            @WebParam(name = "VideoSourceToken",
                      targetNamespace = "http://www.onvif.org/ver20/imaging/wsdl")
            String videoSourceToken) {
        return "Brightness.Min=0 Brightness.Max=100 " +
               "ColorSaturation.Min=0 ColorSaturation.Max=100 " +
               "Contrast.Min=0 Contrast.Max=100 " +
               "Sharpness.Min=0 Sharpness.Max=100 " +
               "WideDynamicRange.Mode=ON,OFF " +
               "WhiteBalance.Mode=AUTO,MANUAL " +
               "Exposure.Mode=AUTO,MANUAL " +
               "Focus.AutoFocusMode=AUTO,MANUAL";
    }

    // ----------------------------------------------------------------
    // GetStatus
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetStatus")
    @WebResult(name = "Status",
               targetNamespace = "http://www.onvif.org/ver20/imaging/wsdl")
    public String getStatus(
            @WebParam(name = "VideoSourceToken",
                      targetNamespace = "http://www.onvif.org/ver20/imaging/wsdl")
            String videoSourceToken) {
        return "FocusStatus20.Status=IDLE FocusStatus20.Position=0.5";
    }

    // ----------------------------------------------------------------
    // Move (focus motor control)
    // ----------------------------------------------------------------

    @WebMethod(operationName = "Move")
    public void move(
            @WebParam(name = "VideoSourceToken",
                      targetNamespace = "http://www.onvif.org/ver20/imaging/wsdl")
            String videoSourceToken,
            @WebParam(name = "Focus",
                      targetNamespace = "http://www.onvif.org/ver20/imaging/wsdl")
            String focus) {
        System.out.printf("[Imaging] Move: source=%s focus=%s%n", videoSourceToken, focus);
    }

    // ----------------------------------------------------------------
    // Stop
    // ----------------------------------------------------------------

    @WebMethod(operationName = "Stop")
    public void stop(
            @WebParam(name = "VideoSourceToken",
                      targetNamespace = "http://www.onvif.org/ver20/imaging/wsdl")
            String videoSourceToken) {
        System.out.printf("[Imaging] Stop: source=%s%n", videoSourceToken);
    }

    // ================================================================
    // Builder helpers
    // ================================================================

    private ImagingSettings buildDefaultImagingSettings() {
        ImagingSettings settings = new ImagingSettings();
        settings.setBrightness(50.0f);
        settings.setColorSaturation(50.0f);
        settings.setContrast(50.0f);
        settings.setSharpness(50.0f);

        WideDynamicRange wdr = new WideDynamicRange("OFF", null);
        settings.setWideDynamicRange(wdr);

        WhiteBalance wb = new WhiteBalance("AUTO");
        settings.setWhiteBalance(wb);

        Exposure exposure = new Exposure();
        exposure.setMode("AUTO");
        exposure.setPriority("LowNoise");
        settings.setExposure(exposure);

        FocusConfiguration focus = new FocusConfiguration("AUTO");
        focus.setDefaultSpeed(0.5f);
        settings.setFocus(focus);

        return settings;
    }
}
