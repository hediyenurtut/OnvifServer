package com.onvif.server.model.device;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Date", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"year", "month", "day"})
public class Date {

    @XmlElement(name = "Year", namespace = "http://www.onvif.org/ver10/schema")
    private int year;

    @XmlElement(name = "Month", namespace = "http://www.onvif.org/ver10/schema")
    private int month;

    @XmlElement(name = "Day", namespace = "http://www.onvif.org/ver10/schema")
    private int day;

    public Date() {}

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public int getMonth() { return month; }
    public void setMonth(int month) { this.month = month; }

    public int getDay() { return day; }
    public void setDay(int day) { this.day = day; }
}
