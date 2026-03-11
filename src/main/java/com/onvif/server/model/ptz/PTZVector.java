package com.onvif.server.model.ptz;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PTZVector", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"panTilt", "zoom"})
public class PTZVector {

    @XmlElement(name = "PanTilt", namespace = "http://www.onvif.org/ver10/schema")
    private Vector2D panTilt;

    @XmlElement(name = "Zoom", namespace = "http://www.onvif.org/ver10/schema")
    private Vector1D zoom;

    public PTZVector() {}

    public PTZVector(Vector2D panTilt, Vector1D zoom) {
        this.panTilt = panTilt;
        this.zoom = zoom;
    }

    public Vector2D getPanTilt() { return panTilt; }
    public void setPanTilt(Vector2D panTilt) { this.panTilt = panTilt; }

    public Vector1D getZoom() { return zoom; }
    public void setZoom(Vector1D zoom) { this.zoom = zoom; }
}
