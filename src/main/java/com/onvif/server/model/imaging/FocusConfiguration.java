package com.onvif.server.model.imaging;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FocusConfiguration20", namespace = "http://www.onvif.org/ver20/imaging/wsdl",
         propOrder = {"autoFocusMode", "defaultSpeed", "nearLimit", "farLimit"})
public class FocusConfiguration {

    @XmlElement(name = "AutoFocusMode", namespace = "http://www.onvif.org/ver20/imaging/wsdl")
    private String autoFocusMode; // AUTO | MANUAL

    @XmlElement(name = "DefaultSpeed", namespace = "http://www.onvif.org/ver20/imaging/wsdl")
    private Float defaultSpeed;

    @XmlElement(name = "NearLimit", namespace = "http://www.onvif.org/ver20/imaging/wsdl")
    private Float nearLimit;

    @XmlElement(name = "FarLimit", namespace = "http://www.onvif.org/ver20/imaging/wsdl")
    private Float farLimit;

    public FocusConfiguration() {}

    public FocusConfiguration(String autoFocusMode) {
        this.autoFocusMode = autoFocusMode;
    }

    public String getAutoFocusMode() { return autoFocusMode; }
    public void setAutoFocusMode(String autoFocusMode) { this.autoFocusMode = autoFocusMode; }

    public Float getDefaultSpeed() { return defaultSpeed; }
    public void setDefaultSpeed(Float defaultSpeed) { this.defaultSpeed = defaultSpeed; }

    public Float getNearLimit() { return nearLimit; }
    public void setNearLimit(Float nearLimit) { this.nearLimit = nearLimit; }

    public Float getFarLimit() { return farLimit; }
    public void setFarLimit(Float farLimit) { this.farLimit = farLimit; }
}
