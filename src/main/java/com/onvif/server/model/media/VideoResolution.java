package com.onvif.server.model.media;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VideoResolution", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"width", "height"})
public class VideoResolution {

    @XmlElement(name = "Width", namespace = "http://www.onvif.org/ver10/schema")
    private int width;

    @XmlElement(name = "Height", namespace = "http://www.onvif.org/ver10/schema")
    private int height;

    public VideoResolution() {}

    public VideoResolution(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }

    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
}
