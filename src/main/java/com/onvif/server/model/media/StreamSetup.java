package com.onvif.server.model.media;

import jakarta.xml.bind.annotation.*;

/**
 * StreamSetup – configures RTP transport for GetStreamUri.
 * Profile T mandates RTSP/RTP streaming with H.264 or H.265.
 */
@XmlRootElement(name = "StreamSetup", namespace = "http://www.onvif.org/ver10/schema")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StreamSetup", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"stream", "transport"})
public class StreamSetup {

    /** RTP-Unicast or RTP-Multicast */
    @XmlElement(name = "Stream", namespace = "http://www.onvif.org/ver10/schema")
    private String stream;

    @XmlElement(name = "Transport", namespace = "http://www.onvif.org/ver10/schema")
    private Transport transport;

    public StreamSetup() {}

    public StreamSetup(String stream, Transport transport) {
        this.stream = stream;
        this.transport = transport;
    }

    public String getStream() { return stream; }
    public void setStream(String stream) { this.stream = stream; }

    public Transport getTransport() { return transport; }
    public void setTransport(Transport transport) { this.transport = transport; }
}
