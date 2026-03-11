package com.onvif.server.model.device;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RealTimeStreamingCapabilities", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"rtpMulticast", "rtp_TCP", "rtp_RTSP_TCP"})
public class RealTimeStreamingCapabilities {

    @XmlElement(name = "RTPMulticast", namespace = "http://www.onvif.org/ver10/schema")
    private boolean rtpMulticast;

    @XmlElement(name = "RTP_TCP", namespace = "http://www.onvif.org/ver10/schema")
    private boolean rtp_TCP;

    @XmlElement(name = "RTP_RTSP_TCP", namespace = "http://www.onvif.org/ver10/schema")
    private boolean rtp_RTSP_TCP;

    public RealTimeStreamingCapabilities() {}

    public RealTimeStreamingCapabilities(boolean rtpMulticast, boolean rtp_TCP, boolean rtp_RTSP_TCP) {
        this.rtpMulticast = rtpMulticast;
        this.rtp_TCP = rtp_TCP;
        this.rtp_RTSP_TCP = rtp_RTSP_TCP;
    }

    public boolean isRtpMulticast() { return rtpMulticast; }
    public void setRtpMulticast(boolean v) { this.rtpMulticast = v; }

    public boolean isRtp_TCP() { return rtp_TCP; }
    public void setRtp_TCP(boolean v) { this.rtp_TCP = v; }

    public boolean isRtp_RTSP_TCP() { return rtp_RTSP_TCP; }
    public void setRtp_RTSP_TCP(boolean v) { this.rtp_RTSP_TCP = v; }
}
