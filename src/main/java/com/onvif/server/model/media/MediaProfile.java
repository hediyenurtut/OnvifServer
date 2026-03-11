package com.onvif.server.model.media;

import jakarta.xml.bind.annotation.*;

/**
 * ONVIF Media Profile – Profile T uses MediaProfile2 which supports
 * H.264 / H.265 video encoders and metadata streaming.
 */
@XmlRootElement(name = "MediaProfile", namespace = "http://www.onvif.org/ver20/media/wsdl")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MediaProfile", namespace = "http://www.onvif.org/ver20/media/wsdl",
         propOrder = {"name", "configurations"})
public class MediaProfile {

    @XmlAttribute(name = "token")
    private String token;

    @XmlAttribute(name = "fixed")
    private Boolean fixed;

    @XmlElement(name = "Name", namespace = "http://www.onvif.org/ver20/media/wsdl")
    private String name;

    @XmlElement(name = "Configurations",
                namespace = "http://www.onvif.org/ver20/media/wsdl")
    private ProfileConfigurations configurations;

    public MediaProfile() {}

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public Boolean getFixed() { return fixed; }
    public void setFixed(Boolean fixed) { this.fixed = fixed; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public ProfileConfigurations getConfigurations() { return configurations; }
    public void setConfigurations(ProfileConfigurations configurations) {
        this.configurations = configurations;
    }
}
