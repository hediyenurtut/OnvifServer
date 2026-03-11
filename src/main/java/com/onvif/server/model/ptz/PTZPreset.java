package com.onvif.server.model.ptz;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "PTZPreset", namespace = "http://www.onvif.org/ver10/schema")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PTZPreset", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"name", "ptzPosition"})
public class PTZPreset {

    @XmlAttribute(name = "token")
    private String token;

    @XmlElement(name = "Name", namespace = "http://www.onvif.org/ver10/schema")
    private String name;

    @XmlElement(name = "PTZPosition", namespace = "http://www.onvif.org/ver10/schema")
    private PTZVector ptzPosition;

    public PTZPreset() {}

    public PTZPreset(String token, String name, PTZVector position) {
        this.token = token;
        this.name = name;
        this.ptzPosition = position;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public PTZVector getPtzPosition() { return ptzPosition; }
    public void setPtzPosition(PTZVector ptzPosition) { this.ptzPosition = ptzPosition; }
}
