package com.onvif.server.model.device;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "SystemDateAndTime", namespace = "http://www.onvif.org/ver10/schema")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SystemDateAndTime", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"dateTimeType", "daylightSavings", "timeZone", "utcDateTime", "localDateTime"})
public class SystemDateAndTime {

    @XmlElement(name = "DateTimeType", namespace = "http://www.onvif.org/ver10/schema")
    private String dateTimeType;

    @XmlElement(name = "DaylightSavings", namespace = "http://www.onvif.org/ver10/schema")
    private boolean daylightSavings;

    @XmlElement(name = "TimeZone", namespace = "http://www.onvif.org/ver10/schema")
    private TimeZoneType timeZone;

    @XmlElement(name = "UTCDateTime", namespace = "http://www.onvif.org/ver10/schema")
    private DateTime utcDateTime;

    @XmlElement(name = "LocalDateTime", namespace = "http://www.onvif.org/ver10/schema")
    private DateTime localDateTime;

    public SystemDateAndTime() {}

    public String getDateTimeType() { return dateTimeType; }
    public void setDateTimeType(String dateTimeType) { this.dateTimeType = dateTimeType; }

    public boolean isDaylightSavings() { return daylightSavings; }
    public void setDaylightSavings(boolean daylightSavings) { this.daylightSavings = daylightSavings; }

    public TimeZoneType getTimeZone() { return timeZone; }
    public void setTimeZone(TimeZoneType timeZone) { this.timeZone = timeZone; }

    public DateTime getUtcDateTime() { return utcDateTime; }
    public void setUtcDateTime(DateTime utcDateTime) { this.utcDateTime = utcDateTime; }

    public DateTime getLocalDateTime() { return localDateTime; }
    public void setLocalDateTime(DateTime localDateTime) { this.localDateTime = localDateTime; }
}
