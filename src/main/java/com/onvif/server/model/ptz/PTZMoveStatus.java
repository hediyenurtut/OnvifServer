package com.onvif.server.model.ptz;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PTZMoveStatus", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"panTilt", "zoom"})
public class PTZMoveStatus {

    @XmlElement(name = "PanTilt", namespace = "http://www.onvif.org/ver10/schema")
    private String panTilt;

    @XmlElement(name = "Zoom", namespace = "http://www.onvif.org/ver10/schema")
    private String zoom;

    public PTZMoveStatus() {}

    public PTZMoveStatus(String panTilt, String zoom) {
        this.panTilt = panTilt;
        this.zoom = zoom;
    }

    public String getPanTilt() { return panTilt; }
    public void setPanTilt(String panTilt) { this.panTilt = panTilt; }

    public String getZoom() { return zoom; }
    public void setZoom(String zoom) { this.zoom = zoom; }
}
