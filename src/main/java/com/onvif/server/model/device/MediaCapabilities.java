package com.onvif.server.model.device;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MediaCapabilities", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"xAddr", "streamingCapabilities"})
public class MediaCapabilities {

    @XmlElement(name = "XAddr", namespace = "http://www.onvif.org/ver10/schema")
    private String xAddr;

    @XmlElement(name = "StreamingCapabilities", namespace = "http://www.onvif.org/ver10/schema")
    private RealTimeStreamingCapabilities streamingCapabilities;

    public MediaCapabilities() {}

    public MediaCapabilities(String xAddr) {
        this.xAddr = xAddr;
        this.streamingCapabilities = new RealTimeStreamingCapabilities(true, true, true);
    }

    public String getXAddr() { return xAddr; }
    public void setXAddr(String xAddr) { this.xAddr = xAddr; }

    public RealTimeStreamingCapabilities getStreamingCapabilities() { return streamingCapabilities; }
    public void setStreamingCapabilities(RealTimeStreamingCapabilities sc) { this.streamingCapabilities = sc; }
}
