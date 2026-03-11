package com.onvif.server.model.media;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "VideoSourceConfiguration", namespace = "http://www.onvif.org/ver10/schema")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VideoSourceConfiguration", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"name", "useCount", "viewMode", "sourceToken", "bounds"})
public class VideoSourceConfiguration {

    @XmlAttribute(name = "token")
    private String token;

    @XmlElement(name = "Name", namespace = "http://www.onvif.org/ver10/schema")
    private String name;

    @XmlElement(name = "UseCount", namespace = "http://www.onvif.org/ver10/schema")
    private int useCount;

    @XmlElement(name = "ViewMode", namespace = "http://www.onvif.org/ver10/schema")
    private String viewMode;

    @XmlElement(name = "SourceToken", namespace = "http://www.onvif.org/ver10/schema")
    private String sourceToken;

    @XmlElement(name = "Bounds", namespace = "http://www.onvif.org/ver10/schema")
    private IntRectangle bounds;

    public VideoSourceConfiguration() {}

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getUseCount() { return useCount; }
    public void setUseCount(int useCount) { this.useCount = useCount; }

    public String getViewMode() { return viewMode; }
    public void setViewMode(String viewMode) { this.viewMode = viewMode; }

    public String getSourceToken() { return sourceToken; }
    public void setSourceToken(String sourceToken) { this.sourceToken = sourceToken; }

    public IntRectangle getBounds() { return bounds; }
    public void setBounds(IntRectangle bounds) { this.bounds = bounds; }
}
