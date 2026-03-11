package com.onvif.server.model.ptz;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FloatRange", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"min", "max"})
public class FloatRange {

    @XmlElement(name = "Min", namespace = "http://www.onvif.org/ver10/schema")
    private float min;

    @XmlElement(name = "Max", namespace = "http://www.onvif.org/ver10/schema")
    private float max;

    public FloatRange() {}

    public FloatRange(float min, float max) {
        this.min = min;
        this.max = max;
    }

    public float getMin() { return min; }
    public void setMin(float min) { this.min = min; }

    public float getMax() { return max; }
    public void setMax(float max) { this.max = max; }
}
