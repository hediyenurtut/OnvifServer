package com.onvif.server.model.device;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Time", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"hour", "minute", "second"})
public class Time {

    @XmlElement(name = "Hour", namespace = "http://www.onvif.org/ver10/schema")
    private int hour;

    @XmlElement(name = "Minute", namespace = "http://www.onvif.org/ver10/schema")
    private int minute;

    @XmlElement(name = "Second", namespace = "http://www.onvif.org/ver10/schema")
    private int second;

    public Time() {}

    public Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public int getHour() { return hour; }
    public void setHour(int hour) { this.hour = hour; }

    public int getMinute() { return minute; }
    public void setMinute(int minute) { this.minute = minute; }

    public int getSecond() { return second; }
    public void setSecond(int second) { this.second = second; }
}
