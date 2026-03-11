package com.onvif.server.model.device;

import jakarta.xml.bind.annotation.*;

/**
 * ONVIF Capabilities response – Profile T mandatory services.
 */
@XmlRootElement(name = "Capabilities", namespace = "http://www.onvif.org/ver10/schema")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Capabilities", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"analytics", "device", "events", "imaging", "media", "ptz"})
public class DeviceCapabilities {

    @XmlElement(name = "Analytics", namespace = "http://www.onvif.org/ver10/schema")
    private AnalyticsCapabilities analytics;

    @XmlElement(name = "Device", namespace = "http://www.onvif.org/ver10/schema")
    private DeviceCapabilityInfo device;

    @XmlElement(name = "Events", namespace = "http://www.onvif.org/ver10/schema")
    private EventCapabilities events;

    @XmlElement(name = "Imaging", namespace = "http://www.onvif.org/ver10/schema")
    private SimpleXAddr imaging;

    @XmlElement(name = "Media", namespace = "http://www.onvif.org/ver10/schema")
    private MediaCapabilities media;

    @XmlElement(name = "PTZ", namespace = "http://www.onvif.org/ver10/schema")
    private SimpleXAddr ptz;

    public DeviceCapabilities() {}

    public AnalyticsCapabilities getAnalytics() { return analytics; }
    public void setAnalytics(AnalyticsCapabilities analytics) { this.analytics = analytics; }

    public DeviceCapabilityInfo getDevice() { return device; }
    public void setDevice(DeviceCapabilityInfo device) { this.device = device; }

    public EventCapabilities getEvents() { return events; }
    public void setEvents(EventCapabilities events) { this.events = events; }

    public SimpleXAddr getImaging() { return imaging; }
    public void setImaging(SimpleXAddr imaging) { this.imaging = imaging; }

    public MediaCapabilities getMedia() { return media; }
    public void setMedia(MediaCapabilities media) { this.media = media; }

    public SimpleXAddr getPtz() { return ptz; }
    public void setPtz(SimpleXAddr ptz) { this.ptz = ptz; }
}
