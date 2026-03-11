package com.onvif.server.endpoint;

import com.onvif.server.model.ptz.*;
import jakarta.jws.*;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.*;
import jakarta.xml.ws.BindingType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ONVIF Profile T – PTZ Service.
 * SOAP 1.2 / Document-Literal-Wrapped bağlaması kullanır.
 *
 * Endpoint: /onvif/ptz_service
 * Namespace: http://www.onvif.org/ver20/ptz/wsdl
 */
@Component
@WebService(
    serviceName     = "PTZService",
    portName        = "PTZPort",
    targetNamespace = "http://www.onvif.org/ver20/ptz/wsdl"
)
@SOAPBinding(
    style          = Style.DOCUMENT,
    use            = Use.LITERAL,
    parameterStyle = ParameterStyle.WRAPPED
)
@BindingType(jakarta.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class PtzServiceEndpoint {

    private volatile float panPos  = 0.0f;
    private volatile float tiltPos = 0.0f;
    private volatile float zoomPos = 0.0f;

    private final ConcurrentHashMap<String, PTZPreset> presets = new ConcurrentHashMap<>();

    // ----------------------------------------------------------------
    // GetServiceCapabilities
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetServiceCapabilities")
    @WebResult(name = "Capabilities",
               targetNamespace = "http://www.onvif.org/ver20/ptz/wsdl")
    public String getServiceCapabilities() {
        return "EFlip=false Reverse=false GetCompatibleConfigurations=true " +
               "MoveStatus=true StatusPosition=true";
    }

    // ----------------------------------------------------------------
    // GetNodes
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetNodes")
    @WebResult(name = "PTZNode",
               targetNamespace = "http://www.onvif.org/ver20/ptz/wsdl")
    public List<PTZNode> getNodes() {
        return List.of(buildPtzNode());
    }

    // ----------------------------------------------------------------
    // GetNode
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetNode")
    @WebResult(name = "PTZNode",
               targetNamespace = "http://www.onvif.org/ver20/ptz/wsdl")
    public PTZNode getNode(
            @WebParam(name = "NodeToken",
                      targetNamespace = "http://www.onvif.org/ver20/ptz/wsdl")
            String nodeToken) {
        return buildPtzNode();
    }

    // ----------------------------------------------------------------
    // GetConfigurations
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetConfigurations")
    @WebResult(name = "PTZConfiguration",
               targetNamespace = "http://www.onvif.org/ver20/ptz/wsdl")
    public List<PTZConfiguration> getConfigurations() {
        return List.of(buildPtzConfiguration());
    }

    // ----------------------------------------------------------------
    // GetConfiguration
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetConfiguration")
    @WebResult(name = "PTZConfiguration",
               targetNamespace = "http://www.onvif.org/ver20/ptz/wsdl")
    public PTZConfiguration getConfiguration(
            @WebParam(name = "PTZConfigurationToken",
                      targetNamespace = "http://www.onvif.org/ver20/ptz/wsdl")
            String configToken) {
        return buildPtzConfiguration();
    }

    // ----------------------------------------------------------------
    // AbsoluteMove
    // ----------------------------------------------------------------

    @WebMethod(operationName = "AbsoluteMove")
    public void absoluteMove(
            @WebParam(name = "ProfileToken",
                      targetNamespace = "http://www.onvif.org/ver20/ptz/wsdl")
            String profileToken,
            @WebParam(name = "Position",
                      targetNamespace = "http://www.onvif.org/ver20/ptz/wsdl")
            PTZVector position,
            @WebParam(name = "Speed",
                      targetNamespace = "http://www.onvif.org/ver20/ptz/wsdl")
            PTZSpeed speed) {
        if (position != null) {
            if (position.getPanTilt() != null) {
                panPos  = position.getPanTilt().getX();
                tiltPos = position.getPanTilt().getY();
            }
            if (position.getZoom() != null) {
                zoomPos = position.getZoom().getX();
            }
        }
        System.out.printf("[PTZ] AbsoluteMove: profile=%s pan=%.3f tilt=%.3f zoom=%.3f%n",
                profileToken, panPos, tiltPos, zoomPos);
    }

    // ----------------------------------------------------------------
    // RelativeMove
    // ----------------------------------------------------------------

    @WebMethod(operationName = "RelativeMove")
    public void relativeMove(
            @WebParam(name = "ProfileToken",
                      targetNamespace = "http://www.onvif.org/ver20/ptz/wsdl")
            String profileToken,
            @WebParam(name = "Translation",
                      targetNamespace = "http://www.onvif.org/ver20/ptz/wsdl")
            PTZVector translation,
            @WebParam(name = "Speed",
                      targetNamespace = "http://www.onvif.org/ver20/ptz/wsdl")
            PTZSpeed speed) {
        if (translation != null) {
            if (translation.getPanTilt() != null) {
                panPos  += translation.getPanTilt().getX();
                tiltPos += translation.getPanTilt().getY();
            }
            if (translation.getZoom() != null) {
                zoomPos += translation.getZoom().getX();
            }
            panPos  = Math.max(-1.0f, Math.min(1.0f, panPos));
            tiltPos = Math.max(-1.0f, Math.min(1.0f, tiltPos));
            zoomPos = Math.max(0.0f,  Math.min(1.0f, zoomPos));
        }
        System.out.printf("[PTZ] RelativeMove: profile=%s pan=%.3f tilt=%.3f zoom=%.3f%n",
                profileToken, panPos, tiltPos, zoomPos);
    }

    // ----------------------------------------------------------------
    // ContinuousMove
    // ----------------------------------------------------------------

    @WebMethod(operationName = "ContinuousMove")
    public void continuousMove(
            @WebParam(name = "ProfileToken",
                      targetNamespace = "http://www.onvif.org/ver20/ptz/wsdl")
            String profileToken,
            @WebParam(name = "Velocity",
                      targetNamespace = "http://www.onvif.org/ver20/ptz/wsdl")
            PTZSpeed velocity,
            @WebParam(name = "Timeout",
                      targetNamespace = "http://www.onvif.org/ver20/ptz/wsdl")
            String timeout) {
        System.out.printf("[PTZ] ContinuousMove: profile=%s timeout=%s%n", profileToken, timeout);
    }

    // ----------------------------------------------------------------
    // Stop
    // ----------------------------------------------------------------

    @WebMethod(operationName = "Stop")
    public void stop(
            @WebParam(name = "ProfileToken",
                      targetNamespace = "http://www.onvif.org/ver20/ptz/wsdl")
            String profileToken,
            @WebParam(name = "PanTilt",
                      targetNamespace = "http://www.onvif.org/ver20/ptz/wsdl")
            Boolean panTilt,
            @WebParam(name = "Zoom",
                      targetNamespace = "http://www.onvif.org/ver20/ptz/wsdl")
            Boolean zoom) {
        System.out.printf("[PTZ] Stop: profile=%s panTilt=%b zoom=%b%n",
                profileToken, panTilt, zoom);
    }

    // ----------------------------------------------------------------
    // GetStatus
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetStatus")
    @WebResult(name = "PTZStatus",
               targetNamespace = "http://www.onvif.org/ver20/ptz/wsdl")
    public PTZStatus getStatus(
            @WebParam(name = "ProfileToken",
                      targetNamespace = "http://www.onvif.org/ver20/ptz/wsdl")
            String profileToken) {
        PTZStatus status = new PTZStatus();
        status.setPosition(new PTZVector(
                new Vector2D(panPos, tiltPos,
                        "http://www.onvif.org/ver10/tptz/PanTiltSpaces/PositionGenericSpace"),
                new Vector1D(zoomPos,
                        "http://www.onvif.org/ver10/tptz/ZoomSpaces/PositionGenericSpace")));
        status.setMoveStatus(new PTZMoveStatus("IDLE", "IDLE"));
        status.setError("");
        status.setUtcTime(java.time.Instant.now().toString());
        return status;
    }

    // ----------------------------------------------------------------
    // SetPreset
    // ----------------------------------------------------------------

    @WebMethod(operationName = "SetPreset")
    @WebResult(name = "PresetToken",
               targetNamespace = "http://www.onvif.org/ver20/ptz/wsdl")
    public String setPreset(
            @WebParam(name = "ProfileToken",
                      targetNamespace = "http://www.onvif.org/ver20/ptz/wsdl")
            String profileToken,
            @WebParam(name = "PresetName",
                      targetNamespace = "http://www.onvif.org/ver20/ptz/wsdl")
            String presetName,
            @WebParam(name = "PresetToken",
                      targetNamespace = "http://www.onvif.org/ver20/ptz/wsdl")
            String presetToken) {
        String tok = (presetToken != null && !presetToken.isBlank())
                ? presetToken : "preset_" + presets.size();
        PTZPreset preset = new PTZPreset(tok, presetName != null ? presetName : "Preset_" + tok,
                new PTZVector(new Vector2D(panPos, tiltPos), new Vector1D(zoomPos)));
        presets.put(tok, preset);
        System.out.printf("[PTZ] SetPreset: name=%s token=%s%n", presetName, tok);
        return tok;
    }

    // ----------------------------------------------------------------
    // GetPresets
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetPresets")
    @WebResult(name = "Preset",
               targetNamespace = "http://www.onvif.org/ver20/ptz/wsdl")
    public List<PTZPreset> getPresets(
            @WebParam(name = "ProfileToken",
                      targetNamespace = "http://www.onvif.org/ver20/ptz/wsdl")
            String profileToken) {
        return new ArrayList<>(presets.values());
    }

    // ----------------------------------------------------------------
    // GotoPreset
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GotoPreset")
    public void gotoPreset(
            @WebParam(name = "ProfileToken",
                      targetNamespace = "http://www.onvif.org/ver20/ptz/wsdl")
            String profileToken,
            @WebParam(name = "PresetToken",
                      targetNamespace = "http://www.onvif.org/ver20/ptz/wsdl")
            String presetToken,
            @WebParam(name = "Speed",
                      targetNamespace = "http://www.onvif.org/ver20/ptz/wsdl")
            PTZSpeed speed) {
        PTZPreset preset = presets.get(presetToken);
        if (preset == null) {
            throw new RuntimeException("Preset not found: " + presetToken);
        }
        if (preset.getPtzPosition() != null) {
            if (preset.getPtzPosition().getPanTilt() != null) {
                panPos  = preset.getPtzPosition().getPanTilt().getX();
                tiltPos = preset.getPtzPosition().getPanTilt().getY();
            }
            if (preset.getPtzPosition().getZoom() != null) {
                zoomPos = preset.getPtzPosition().getZoom().getX();
            }
        }
        System.out.printf("[PTZ] GotoPreset: %s -> pan=%.3f tilt=%.3f zoom=%.3f%n",
                presetToken, panPos, tiltPos, zoomPos);
    }

    // ----------------------------------------------------------------
    // RemovePreset
    // ----------------------------------------------------------------

    @WebMethod(operationName = "RemovePreset")
    public void removePreset(
            @WebParam(name = "ProfileToken",
                      targetNamespace = "http://www.onvif.org/ver20/ptz/wsdl")
            String profileToken,
            @WebParam(name = "PresetToken",
                      targetNamespace = "http://www.onvif.org/ver20/ptz/wsdl")
            String presetToken) {
        presets.remove(presetToken);
        System.out.printf("[PTZ] RemovePreset: %s%n", presetToken);
    }

    // ----------------------------------------------------------------
    // GotoHomePosition
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GotoHomePosition")
    public void gotoHomePosition(
            @WebParam(name = "ProfileToken",
                      targetNamespace = "http://www.onvif.org/ver20/ptz/wsdl")
            String profileToken,
            @WebParam(name = "Speed",
                      targetNamespace = "http://www.onvif.org/ver20/ptz/wsdl")
            PTZSpeed speed) {
        panPos = 0.0f;
        tiltPos = 0.0f;
        zoomPos = 0.0f;
        System.out.printf("[PTZ] GotoHomePosition: profile=%s%n", profileToken);
    }

    // ----------------------------------------------------------------
    // SetHomePosition
    // ----------------------------------------------------------------

    @WebMethod(operationName = "SetHomePosition")
    public void setHomePosition(
            @WebParam(name = "ProfileToken",
                      targetNamespace = "http://www.onvif.org/ver20/ptz/wsdl")
            String profileToken) {
        System.out.printf("[PTZ] SetHomePosition: profile=%s pan=%.3f tilt=%.3f%n",
                profileToken, panPos, tiltPos);
    }

    // ================================================================
    // Builder helpers
    // ================================================================

    private PTZNode buildPtzNode() {
        PTZNode node = new PTZNode();
        node.setToken("ptz_node_main");
        node.setName("PTZNode_Main");
        node.setMaximumNumberOfPresets(100);
        node.setHomeSupported(true);
        node.setFixedHomePosition(false);

        PTZSpaces spaces = new PTZSpaces();
        spaces.getAbsolutePanTiltPositionSpace().add(new Space2DDescription(
                "http://www.onvif.org/ver10/tptz/PanTiltSpaces/PositionGenericSpace",
                -1.0f, 1.0f, -1.0f, 1.0f));
        spaces.getAbsoluteZoomPositionSpace().add(new Space1DDescription(
                "http://www.onvif.org/ver10/tptz/ZoomSpaces/PositionGenericSpace",
                0.0f, 1.0f));
        spaces.getRelativePanTiltTranslationSpace().add(new Space2DDescription(
                "http://www.onvif.org/ver10/tptz/PanTiltSpaces/TranslationGenericSpace",
                -1.0f, 1.0f, -1.0f, 1.0f));
        spaces.getRelativeZoomTranslationSpace().add(new Space1DDescription(
                "http://www.onvif.org/ver10/tptz/ZoomSpaces/TranslationGenericSpace",
                -1.0f, 1.0f));
        spaces.getContinuousPanTiltVelocitySpace().add(new Space2DDescription(
                "http://www.onvif.org/ver10/tptz/PanTiltSpaces/VelocityGenericSpace",
                -1.0f, 1.0f, -1.0f, 1.0f));
        spaces.getContinuousZoomVelocitySpace().add(new Space1DDescription(
                "http://www.onvif.org/ver10/tptz/ZoomSpaces/VelocityGenericSpace",
                -1.0f, 1.0f));
        node.setSupportedPTZSpaces(spaces);
        return node;
    }

    private PTZConfiguration buildPtzConfiguration() {
        PTZConfiguration config = new PTZConfiguration();
        config.setToken("ptz_config_main");
        config.setName("PTZConfig_Main");
        config.setNodeToken("ptz_node_main");
        config.setDefaultAbsolutePantTiltPositionSpace(
                "http://www.onvif.org/ver10/tptz/PanTiltSpaces/PositionGenericSpace");
        config.setDefaultAbsoluteZoomPositionSpace(
                "http://www.onvif.org/ver10/tptz/ZoomSpaces/PositionGenericSpace");
        config.setDefaultRelativePanTiltTranslationSpace(
                "http://www.onvif.org/ver10/tptz/PanTiltSpaces/TranslationGenericSpace");
        config.setDefaultRelativeZoomTranslationSpace(
                "http://www.onvif.org/ver10/tptz/ZoomSpaces/TranslationGenericSpace");
        config.setDefaultContinuousPanTiltVelocitySpace(
                "http://www.onvif.org/ver10/tptz/PanTiltSpaces/VelocityGenericSpace");
        config.setDefaultContinuousZoomVelocitySpace(
                "http://www.onvif.org/ver10/tptz/ZoomSpaces/VelocityGenericSpace");
        config.setDefaultPTZTimeout("PT10S");
        config.setDefaultPTZSpeed(
                new PTZSpeed(new Vector2D(0.5f, 0.5f), new Vector1D(0.5f)));
        config.setPanTiltLimits(new PanTiltLimits(new Space2DDescription(
                "http://www.onvif.org/ver10/tptz/PanTiltSpaces/PositionGenericSpace",
                -1.0f, 1.0f, -1.0f, 1.0f)));
        config.setZoomLimits(new ZoomLimits(new Space1DDescription(
                "http://www.onvif.org/ver10/tptz/ZoomSpaces/PositionGenericSpace",
                0.0f, 1.0f)));
        return config;
    }
}