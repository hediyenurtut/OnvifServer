package com.onvif.server.model.ptz;

import jakarta.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "PTZNode", namespace = "http://www.onvif.org/ver10/schema")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PTZNode", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"name", "supportedPTZSpaces", "maximumNumberOfPresets", "homeSupported"})
public class PTZNode {

    @XmlAttribute(name = "token")
    private String token;

    @XmlAttribute(name = "fixedHomePosition")
    private Boolean fixedHomePosition;

    @XmlElement(name = "Name", namespace = "http://www.onvif.org/ver10/schema")
    private String name;

    @XmlElement(name = "SupportedPTZSpaces", namespace = "http://www.onvif.org/ver10/schema")
    private PTZSpaces supportedPTZSpaces;

    @XmlElement(name = "MaximumNumberOfPresets", namespace = "http://www.onvif.org/ver10/schema")
    private int maximumNumberOfPresets;

    @XmlElement(name = "HomeSupported", namespace = "http://www.onvif.org/ver10/schema")
    private boolean homeSupported;

    public PTZNode() {}

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public Boolean getFixedHomePosition() { return fixedHomePosition; }
    public void setFixedHomePosition(Boolean fixedHomePosition) { this.fixedHomePosition = fixedHomePosition; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public PTZSpaces getSupportedPTZSpaces() { return supportedPTZSpaces; }
    public void setSupportedPTZSpaces(PTZSpaces spaces) { this.supportedPTZSpaces = spaces; }

    public int getMaximumNumberOfPresets() { return maximumNumberOfPresets; }
    public void setMaximumNumberOfPresets(int max) { this.maximumNumberOfPresets = max; }

    public boolean isHomeSupported() { return homeSupported; }
    public void setHomeSupported(boolean homeSupported) { this.homeSupported = homeSupported; }
}
