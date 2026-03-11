package com.onvif.server.model.ptz;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Vector2D", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"x", "y", "space"})
public class Vector2D {

    @XmlAttribute(name = "x")
    private float x;

    @XmlAttribute(name = "y")
    private float y;

    @XmlAttribute(name = "space")
    private String space;

    public Vector2D() {}

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(float x, float y, String space) {
        this.x = x;
        this.y = y;
        this.space = space;
    }

    public float getX() { return x; }
    public void setX(float x) { this.x = x; }

    public float getY() { return y; }
    public void setY(float y) { this.y = y; }

    public String getSpace() { return space; }
    public void setSpace(String space) { this.space = space; }
}
