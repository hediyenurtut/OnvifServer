package com.onvif.server.model.imaging;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WhiteBalance20", namespace = "http://www.onvif.org/ver20/imaging/wsdl",
         propOrder = {"mode", "crGain", "cbGain"})
public class WhiteBalance {

    @XmlElement(name = "Mode", namespace = "http://www.onvif.org/ver20/imaging/wsdl")
    private String mode; // AUTO | MANUAL

    @XmlElement(name = "CrGain", namespace = "http://www.onvif.org/ver20/imaging/wsdl")
    private Float crGain;

    @XmlElement(name = "CbGain", namespace = "http://www.onvif.org/ver20/imaging/wsdl")
    private Float cbGain;

    public WhiteBalance() {}

    public WhiteBalance(String mode) {
        this.mode = mode;
    }

    public String getMode() { return mode; }
    public void setMode(String mode) { this.mode = mode; }

    public Float getCrGain() { return crGain; }
    public void setCrGain(Float crGain) { this.crGain = crGain; }

    public Float getCbGain() { return cbGain; }
    public void setCbGain(Float cbGain) { this.cbGain = cbGain; }
}
