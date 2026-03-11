package com.onvif.server.model.media;

import jakarta.xml.bind.annotation.*;

/**
 * MetadataConfiguration – required by ONVIF Profile T for analytics/metadata streaming.
 */
@XmlRootElement(name = "MetadataConfiguration", namespace = "http://www.onvif.org/ver10/schema")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MetadataConfiguration", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"name", "useCount", "sessionTimeout", "analytics", "ptzStatus",
                      "events", "multicast", "compressionType"})
public class MetadataConfiguration {

    @XmlAttribute(name = "token")
    private String token;

    @XmlElement(name = "Name", namespace = "http://www.onvif.org/ver10/schema")
    private String name;

    @XmlElement(name = "UseCount", namespace = "http://www.onvif.org/ver10/schema")
    private int useCount;

    @XmlElement(name = "SessionTimeout", namespace = "http://www.onvif.org/ver10/schema")
    private String sessionTimeout;

    @XmlElement(name = "Analytics", namespace = "http://www.onvif.org/ver10/schema")
    private boolean analytics;

    @XmlElement(name = "PTZStatus", namespace = "http://www.onvif.org/ver10/schema")
    private PTZFilter ptzStatus;

    @XmlElement(name = "Events", namespace = "http://www.onvif.org/ver10/schema")
    private EventSubscription events;

    @XmlElement(name = "Multicast", namespace = "http://www.onvif.org/ver10/schema")
    private MulticastConfiguration multicast;

    @XmlElement(name = "CompressionType", namespace = "http://www.onvif.org/ver10/schema")
    private String compressionType;

    public MetadataConfiguration() {}

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getUseCount() { return useCount; }
    public void setUseCount(int useCount) { this.useCount = useCount; }

    public String getSessionTimeout() { return sessionTimeout; }
    public void setSessionTimeout(String sessionTimeout) { this.sessionTimeout = sessionTimeout; }

    public boolean isAnalytics() { return analytics; }
    public void setAnalytics(boolean analytics) { this.analytics = analytics; }

    public PTZFilter getPtzStatus() { return ptzStatus; }
    public void setPtzStatus(PTZFilter ptzStatus) { this.ptzStatus = ptzStatus; }

    public EventSubscription getEvents() { return events; }
    public void setEvents(EventSubscription events) { this.events = events; }

    public MulticastConfiguration getMulticast() { return multicast; }
    public void setMulticast(MulticastConfiguration multicast) { this.multicast = multicast; }

    public String getCompressionType() { return compressionType; }
    public void setCompressionType(String compressionType) { this.compressionType = compressionType; }
}
