package com.onvif.server.model.ptz;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PanTiltLimits", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"range"})
public class PanTiltLimits {

    @XmlElement(name = "Range", namespace = "http://www.onvif.org/ver10/schema")
    private Space2DDescription range;

    public PanTiltLimits() {}

    public PanTiltLimits(Space2DDescription range) {
        this.range = range;
    }

    public Space2DDescription getRange() { return range; }
    public void setRange(Space2DDescription range) { this.range = range; }
}
