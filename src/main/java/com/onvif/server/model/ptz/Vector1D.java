package com.onvif.server.model.ptz;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Vector1D", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"x", "space"})
public class Vector1D {

    @XmlAttribute(name = "x")
    private float x;

    @XmlAttribute(name = "space")
    private String space;

    public Vector1D() {}

    public Vector1D(float x) {
        this.x = x;
    }

    public Vector1D(float x, String space) {
        this.x = x;
        this.space = space;
    }

    public float getX() { return x; }
    public void setX(float x) { this.x = x; }

    public String getSpace() { return space; }
    public void setSpace(String space) { this.space = space; }
}
