package com.onvif.server.model.media;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "VideoSources", namespace = "http://www.onvif.org/ver10/schema")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VideoSource", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"framerate", "resolution"})
public class VideoSource {

    @XmlAttribute(name = "token")
    private String token;

    @XmlElement(name = "Framerate", namespace = "http://www.onvif.org/ver10/schema")
    private float framerate;

    @XmlElement(name = "Resolution", namespace = "http://www.onvif.org/ver10/schema")
    private VideoResolution resolution;

    public VideoSource() {}

    public VideoSource(String token, float framerate, VideoResolution resolution) {
        this.token = token;
        this.framerate = framerate;
        this.resolution = resolution;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public float getFramerate() { return framerate; }
    public void setFramerate(float framerate) { this.framerate = framerate; }

    public VideoResolution getResolution() { return resolution; }
    public void setResolution(VideoResolution resolution) { this.resolution = resolution; }
}
