package com.onvif.server.endpoint;

import jakarta.jws.*;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.*;
import jakarta.xml.ws.BindingType;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ONVIF Profile T - PTZ Service
 * SOAP 1.2 binding
 */
@Component
@WebService(
    serviceName     = "PTZService",
    portName        = "PTZPort",
    targetNamespace = "http://www.onvif.org/ver20/ptz/wsdl",
    endpointInterface = "com.onvif.server.endpoint.PtzServiceEndpoint"
)
@SOAPBinding(
    style          = Style.DOCUMENT,
    use            = Use.LITERAL,
    parameterStyle = ParameterStyle.WRAPPED
)
@BindingType(jakarta.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class PtzServiceEndpoint {

    private float panPos  = 0.0f;
    private float tiltPos = 0.0f;
    private float zoomPos = 0.0f;

    private final Map<String, Map<String, Float>> presets = new ConcurrentHashMap<>();

    @WebMethod(operationName = "GetServiceCapabilities")
    public Map<String, Object> getServiceCapabilities() {
        Map<String, Object> caps = new LinkedHashMap<>();
        caps.put("EFlip",              false);
        caps.put("Reverse",            false);
        caps.put("GetCompatibleConfigurations", true);
        caps.put("MoveStatus",         true);
        caps.put("StatusPosition",     true);
        return caps;
    }

    @WebMethod(operationName = "GetNodes")
    public List<Map<String, Object>> getNodes() {
        Map<String, Object> node = new LinkedHashMap<>();
        node.put("token",  "ptz_node_main");
        node.put("Name",   "PTZNode_Main");
        node.put("SupportedPTZSpaces.AbsolutePanTiltPositionSpace",
                 "http://www.onvif.org/ver10/tptz/PanTiltSpaces/PositionGenericSpace");
        node.put("SupportedPTZSpaces.AbsoluteZoomPositionSpace",
                 "http://www.onvif.org/ver10/tptz/ZoomSpaces/PositionGenericSpace");
        node.put("SupportedPTZSpaces.RelativePanTiltTranslationSpace",
                 "http://www.onvif.org/ver10/tptz/PanTiltSpaces/TranslationGenericSpace");
        node.put("SupportedPTZSpaces.ContinuousPanTiltVelocitySpace",
                 "http://www.onvif.org/ver10/tptz/PanTiltSpaces/VelocityGenericSpace");
        node.put("MaximumNumberOfPresets", 100);
        node.put("HomeSupported",          true);
        return List.of(node);
    }

    @WebMethod(operationName = "GetConfigurations")
    public List<Map<String, Object>> getConfigurations() {
        Map<String, Object> config = new LinkedHashMap<>();
        config.put("token",     "ptz_config_main");
        config.put("Name",      "PTZConfig_Main");
        config.put("NodeToken", "ptz_node_main");
        config.put("DefaultAbsolutePantTiltPositionSpace",
                   "http://www.onvif.org/ver10/tptz/PanTiltSpaces/PositionGenericSpace");
        config.put("DefaultPTZSpeed.PanTilt.x", 0.5);
        config.put("DefaultPTZSpeed.PanTilt.y", 0.5);
        config.put("DefaultPTZSpeed.Zoom.x",    0.5);
        config.put("DefaultPTZTimeout",         "PT10S");
        config.put("PanTiltLimits.Range.XRange.Min", -1.0);
        config.put("PanTiltLimits.Range.XRange.Max",  1.0);
        config.put("PanTiltLimits.Range.YRange.Min", -1.0);
        config.put("PanTiltLimits.Range.YRange.Max",  1.0);
        config.put("ZoomLimits.Range.XRange.Min", 0.0);
        config.put("ZoomLimits.Range.XRange.Max", 1.0);
        return List.of(config);
    }

    @WebMethod(operationName = "AbsoluteMove")
    public void absoluteMove(
            @WebParam(name = "ProfileToken") String profileToken,
            @WebParam(name = "Position")     Map<String, Float> position,
            @WebParam(name = "Speed")        Map<String, Float> speed) {
        if (position != null) {
            panPos  = position.getOrDefault("PanTilt.x", panPos);
            tiltPos = position.getOrDefault("PanTilt.y", tiltPos);
            zoomPos = position.getOrDefault("Zoom.x",    zoomPos);
        }
        System.out.printf("[PTZ] AbsoluteMove: profile=%s pan=%.3f tilt=%.3f zoom=%.3f%n",
                profileToken, panPos, tiltPos, zoomPos);
    }

    @WebMethod(operationName = "RelativeMove")
    public void relativeMove(
            @WebParam(name = "ProfileToken") String profileToken,
            @WebParam(name = "Translation")  Map<String, Float> translation,
            @WebParam(name = "Speed")        Map<String, Float> speed) {
        if (translation != null) {
            panPos  += translation.getOrDefault("PanTilt.x", 0.0f);
            tiltPos += translation.getOrDefault("PanTilt.y", 0.0f);
            zoomPos += translation.getOrDefault("Zoom.x",    0.0f);
            panPos  = Math.max(-1.0f, Math.min(1.0f, panPos));
            tiltPos = Math.max(-1.0f, Math.min(1.0f, tiltPos));
            zoomPos = Math.max(0.0f,  Math.min(1.0f, zoomPos));
        }
        System.out.printf("[PTZ] RelativeMove: profile=%s pan=%.3f tilt=%.3f zoom=%.3f%n",
                profileToken, panPos, tiltPos, zoomPos);
    }

    @WebMethod(operationName = "ContinuousMove")
    public void continuousMove(
            @WebParam(name = "ProfileToken") String profileToken,
            @WebParam(name = "Velocity")     Map<String, Float> velocity,
            @WebParam(name = "Timeout")      String timeout) {
        System.out.printf("[PTZ] ContinuousMove: profile=%s velocity=%s timeout=%s%n",
                profileToken, velocity, timeout);
    }

    @WebMethod(operationName = "Stop")
    public void stop(
            @WebParam(name = "ProfileToken") String profileToken,
            @WebParam(name = "PanTilt")      Boolean panTilt,
            @WebParam(name = "Zoom")         Boolean zoom) {
        System.out.printf("[PTZ] Stop: profile=%s panTilt=%b zoom=%b%n", profileToken, panTilt, zoom);
    }

    @WebMethod(operationName = "GetStatus")
    public Map<String, Object> getStatus(
            @WebParam(name = "ProfileToken") String profileToken) {
        Map<String, Object> status = new LinkedHashMap<>();
        status.put("Position.PanTilt.x", panPos);
        status.put("Position.PanTilt.y", tiltPos);
        status.put("Position.Zoom.x",    zoomPos);
        status.put("MoveStatus.PanTilt", "IDLE");
        status.put("MoveStatus.Zoom",    "IDLE");
        status.put("Error",              "");
        status.put("UtcTime",            java.time.Instant.now().toString());
        return status;
    }

    @WebMethod(operationName = "SetPreset")
    public String setPreset(
            @WebParam(name = "ProfileToken") String profileToken,
            @WebParam(name = "PresetName")   String presetName,
            @WebParam(name = "PresetToken")  String presetToken) {
        String tok = (presetToken != null && !presetToken.isBlank())
            ? presetToken : "preset_" + presets.size();
        Map<String, Float> pos = new HashMap<>();
        pos.put("pan",  panPos);
        pos.put("tilt", tiltPos);
        pos.put("zoom", zoomPos);
        presets.put(tok, pos);
        System.out.printf("[PTZ] SetPreset: name=%s token=%s%n", presetName, tok);
        return tok;
    }

    @WebMethod(operationName = "GetPresets")
    public List<Map<String, Object>> getPresets(
            @WebParam(name = "ProfileToken") String profileToken) {
        List<Map<String, Object>> result = new ArrayList<>();
        presets.forEach((tok, pos) -> {
            Map<String, Object> p = new LinkedHashMap<>();
            p.put("token",                 tok);
            p.put("Name",                  "Preset_" + tok);
            p.put("PTZPosition.PanTilt.x", pos.get("pan"));
            p.put("PTZPosition.PanTilt.y", pos.get("tilt"));
            p.put("PTZPosition.Zoom.x",    pos.get("zoom"));
            result.add(p);
        });
        return result;
    }

    @WebMethod(operationName = "GotoPreset")
    public void gotoPreset(
            @WebParam(name = "ProfileToken") String profileToken,
            @WebParam(name = "PresetToken")  String presetToken,
            @WebParam(name = "Speed")        Map<String, Float> speed) {
        Map<String, Float> pos = presets.get(presetToken);
        if (pos == null) throw new RuntimeException("Preset not found: " + presetToken);
        panPos  = pos.get("pan");
        tiltPos = pos.get("tilt");
        zoomPos = pos.get("zoom");
        System.out.printf("[PTZ] GotoPreset: %s -> pan=%.3f tilt=%.3f zoom=%.3f%n",
                presetToken, panPos, tiltPos, zoomPos);
    }

    @WebMethod(operationName = "RemovePreset")
    public void removePreset(
            @WebParam(name = "ProfileToken") String profileToken,
            @WebParam(name = "PresetToken")  String presetToken) {
        presets.remove(presetToken);
        System.out.printf("[PTZ] RemovePreset: %s%n", presetToken);
    }

    @WebMethod(operationName = "GotoHomePosition")
    public void gotoHomePosition(
            @WebParam(name = "ProfileToken") String profileToken,
            @WebParam(name = "Speed")        Map<String, Float> speed) {
        panPos = 0.0f; tiltPos = 0.0f; zoomPos = 0.0f;
        System.out.printf("[PTZ] GotoHomePosition: profile=%s%n", profileToken);
    }

    @WebMethod(operationName = "SetHomePosition")
    public void setHomePosition(
            @WebParam(name = "ProfileToken") String profileToken) {
        System.out.printf("[PTZ] SetHomePosition: profile=%s pan=%.3f tilt=%.3f%n",
                profileToken, panPos, tiltPos);
    }
}