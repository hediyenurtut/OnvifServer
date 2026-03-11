package com.onvif.server.model.ptz;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZoomLimits", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"range"})
public class ZoomLimits {

    @XmlElement(name = "Range", namespace = "http://www.onvif.org/ver10/schema")
    private Space1DDescription range;

    public ZoomLimits() {}

    public ZoomLimits(Space1DDescription range) {
        this.range = range;
    }

    public Space1DDescription getRange() { return range; }
    public void setRange(Space1DDescription range) { this.range = range; }
}
