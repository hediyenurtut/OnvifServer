package com.onvif.server.model.imaging;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WideDynamicRange20", namespace = "http://www.onvif.org/ver20/imaging/wsdl",
         propOrder = {"mode", "level"})
public class WideDynamicRange {

    @XmlElement(name = "Mode", namespace = "http://www.onvif.org/ver20/imaging/wsdl")
    private String mode; // ON | OFF

    @XmlElement(name = "Level", namespace = "http://www.onvif.org/ver20/imaging/wsdl")
    private Float level;

    public WideDynamicRange() {}

    public WideDynamicRange(String mode, Float level) {
        this.mode = mode;
        this.level = level;
    }

    public String getMode() { return mode; }
    public void setMode(String mode) { this.mode = mode; }

    public Float getLevel() { return level; }
    public void setLevel(Float level) { this.level = level; }
}
