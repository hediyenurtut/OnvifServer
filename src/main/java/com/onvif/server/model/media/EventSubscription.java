package com.onvif.server.model.media;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EventSubscription", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"filter"})
public class EventSubscription {

    @XmlElement(name = "Filter", namespace = "http://www.onvif.org/ver10/schema")
    private String filter;

    public EventSubscription() {}

    public EventSubscription(String filter) {
        this.filter = filter;
    }

    public String getFilter() { return filter; }
    public void setFilter(String filter) { this.filter = filter; }
}
