package com.onvif.server.model.device;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DateTime", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"time", "date"})
public class DateTime {

    @XmlElement(name = "Time", namespace = "http://www.onvif.org/ver10/schema")
    private Time time;

    @XmlElement(name = "Date", namespace = "http://www.onvif.org/ver10/schema")
    private Date date;

    public DateTime() {}

    public DateTime(Time time, Date date) {
        this.time = time;
        this.date = date;
    }

    public Time getTime() { return time; }
    public void setTime(Time time) { this.time = time; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
}
