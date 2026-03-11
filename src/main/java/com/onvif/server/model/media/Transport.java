package com.onvif.server.model.media;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Transport", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"protocol", "tunnel"})
public class Transport {

    /** RTSP, UDP, HTTP, TCP */
    @XmlElement(name = "Protocol", namespace = "http://www.onvif.org/ver10/schema")
    private String protocol;

    @XmlElement(name = "Tunnel", namespace = "http://www.onvif.org/ver10/schema")
    private Transport tunnel;

    public Transport() {}

    public Transport(String protocol) {
        this.protocol = protocol;
    }

    public String getProtocol() { return protocol; }
    public void setProtocol(String protocol) { this.protocol = protocol; }

    public Transport getTunnel() { return tunnel; }
    public void setTunnel(Transport tunnel) { this.tunnel = tunnel; }
}
