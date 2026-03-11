package com.onvif.server.model.device;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeZone", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"tz"})
public class TimeZoneType {

    @XmlElement(name = "TZ", namespace = "http://www.onvif.org/ver10/schema")
    private String tz;

    public TimeZoneType() {}

    public TimeZoneType(String tz) {
        this.tz = tz;
    }

    public String getTz() { return tz; }
    public void setTz(String tz) { this.tz = tz; }
}
