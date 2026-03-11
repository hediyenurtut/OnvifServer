package com.onvif.server.model.ptz;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "PTZConfiguration", namespace = "http://www.onvif.org/ver10/schema")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PTZConfiguration", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"name", "nodeToken", "defaultAbsolutePantTiltPositionSpace",
                      "defaultAbsoluteZoomPositionSpace", "defaultRelativePanTiltTranslationSpace",
                      "defaultRelativeZoomTranslationSpace", "defaultContinuousPanTiltVelocitySpace",
                      "defaultContinuousZoomVelocitySpace", "defaultPTZSpeed", "defaultPTZTimeout",
                      "panTiltLimits", "zoomLimits"})
public class PTZConfiguration {

    @XmlAttribute(name = "token")
    private String token;

    @XmlElement(name = "Name", namespace = "http://www.onvif.org/ver10/schema")
    private String name;

    @XmlElement(name = "NodeToken", namespace = "http://www.onvif.org/ver10/schema")
    private String nodeToken;

    @XmlElement(name = "DefaultAbsolutePantTiltPositionSpace", namespace = "http://www.onvif.org/ver10/schema")
    private String defaultAbsolutePantTiltPositionSpace;

    @XmlElement(name = "DefaultAbsoluteZoomPositionSpace", namespace = "http://www.onvif.org/ver10/schema")
    private String defaultAbsoluteZoomPositionSpace;

    @XmlElement(name = "DefaultRelativePanTiltTranslationSpace", namespace = "http://www.onvif.org/ver10/schema")
    private String defaultRelativePanTiltTranslationSpace;

    @XmlElement(name = "DefaultRelativeZoomTranslationSpace", namespace = "http://www.onvif.org/ver10/schema")
    private String defaultRelativeZoomTranslationSpace;

    @XmlElement(name = "DefaultContinuousPanTiltVelocitySpace", namespace = "http://www.onvif.org/ver10/schema")
    private String defaultContinuousPanTiltVelocitySpace;

    @XmlElement(name = "DefaultContinuousZoomVelocitySpace", namespace = "http://www.onvif.org/ver10/schema")
    private String defaultContinuousZoomVelocitySpace;

    @XmlElement(name = "DefaultPTZSpeed", namespace = "http://www.onvif.org/ver10/schema")
    private PTZSpeed defaultPTZSpeed;

    @XmlElement(name = "DefaultPTZTimeout", namespace = "http://www.onvif.org/ver10/schema")
    private String defaultPTZTimeout;

    @XmlElement(name = "PanTiltLimits", namespace = "http://www.onvif.org/ver10/schema")
    private PanTiltLimits panTiltLimits;

    @XmlElement(name = "ZoomLimits", namespace = "http://www.onvif.org/ver10/schema")
    private ZoomLimits zoomLimits;

    public PTZConfiguration() {}

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getNodeToken() { return nodeToken; }
    public void setNodeToken(String nodeToken) { this.nodeToken = nodeToken; }

    public String getDefaultAbsolutePantTiltPositionSpace() { return defaultAbsolutePantTiltPositionSpace; }
    public void setDefaultAbsolutePantTiltPositionSpace(String s) { this.defaultAbsolutePantTiltPositionSpace = s; }

    public String getDefaultAbsoluteZoomPositionSpace() { return defaultAbsoluteZoomPositionSpace; }
    public void setDefaultAbsoluteZoomPositionSpace(String s) { this.defaultAbsoluteZoomPositionSpace = s; }

    public String getDefaultRelativePanTiltTranslationSpace() { return defaultRelativePanTiltTranslationSpace; }
    public void setDefaultRelativePanTiltTranslationSpace(String s) { this.defaultRelativePanTiltTranslationSpace = s; }

    public String getDefaultRelativeZoomTranslationSpace() { return defaultRelativeZoomTranslationSpace; }
    public void setDefaultRelativeZoomTranslationSpace(String s) { this.defaultRelativeZoomTranslationSpace = s; }

    public String getDefaultContinuousPanTiltVelocitySpace() { return defaultContinuousPanTiltVelocitySpace; }
    public void setDefaultContinuousPanTiltVelocitySpace(String s) { this.defaultContinuousPanTiltVelocitySpace = s; }

    public String getDefaultContinuousZoomVelocitySpace() { return defaultContinuousZoomVelocitySpace; }
    public void setDefaultContinuousZoomVelocitySpace(String s) { this.defaultContinuousZoomVelocitySpace = s; }

    public PTZSpeed getDefaultPTZSpeed() { return defaultPTZSpeed; }
    public void setDefaultPTZSpeed(PTZSpeed speed) { this.defaultPTZSpeed = speed; }

    public String getDefaultPTZTimeout() { return defaultPTZTimeout; }
    public void setDefaultPTZTimeout(String timeout) { this.defaultPTZTimeout = timeout; }

    public PanTiltLimits getPanTiltLimits() { return panTiltLimits; }
    public void setPanTiltLimits(PanTiltLimits panTiltLimits) { this.panTiltLimits = panTiltLimits; }

    public ZoomLimits getZoomLimits() { return zoomLimits; }
    public void setZoomLimits(ZoomLimits zoomLimits) { this.zoomLimits = zoomLimits; }
}
