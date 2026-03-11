package com.onvif.server.model.media;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PTZFilter", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"status", "position"})
public class PTZFilter {

    @XmlElement(name = "Status", namespace = "http://www.onvif.org/ver10/schema")
    private boolean status;

    @XmlElement(name = "Position", namespace = "http://www.onvif.org/ver10/schema")
    private boolean position;

    public PTZFilter() {}

    public PTZFilter(boolean status, boolean position) {
        this.status = status;
        this.position = position;
    }

    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }

    public boolean isPosition() { return position; }
    public void setPosition(boolean position) { this.position = position; }
}
