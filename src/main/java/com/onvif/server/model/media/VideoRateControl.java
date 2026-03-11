package com.onvif.server.model.media;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VideoRateControl", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"frameRateLimit", "encodingInterval", "bitrateLimit"})
public class VideoRateControl {

    @XmlElement(name = "FrameRateLimit", namespace = "http://www.onvif.org/ver10/schema")
    private float frameRateLimit;

    @XmlElement(name = "EncodingInterval", namespace = "http://www.onvif.org/ver10/schema")
    private int encodingInterval;

    @XmlElement(name = "BitrateLimit", namespace = "http://www.onvif.org/ver10/schema")
    private int bitrateLimit;

    public VideoRateControl() {}

    public VideoRateControl(float frameRateLimit, int encodingInterval, int bitrateLimit) {
        this.frameRateLimit = frameRateLimit;
        this.encodingInterval = encodingInterval;
        this.bitrateLimit = bitrateLimit;
    }

    public float getFrameRateLimit() { return frameRateLimit; }
    public void setFrameRateLimit(float frameRateLimit) { this.frameRateLimit = frameRateLimit; }

    public int getEncodingInterval() { return encodingInterval; }
    public void setEncodingInterval(int encodingInterval) { this.encodingInterval = encodingInterval; }

    public int getBitrateLimit() { return bitrateLimit; }
    public void setBitrateLimit(int bitrateLimit) { this.bitrateLimit = bitrateLimit; }
}
