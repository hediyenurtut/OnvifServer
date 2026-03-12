package com.onvif.server.model.media;

import com.onvif.server.model.ptz.PTZConfiguration;
import jakarta.xml.bind.annotation.*;

/**
 * ONVIF Profile S media profile – represents a tt:Profile from the schema namespace.
 * Used in the GetProfiles response of the Media service (ver10/media/wsdl).
 */
@XmlRootElement(name = "Profiles", namespace = "http://www.onvif.org/ver10/schema")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Profile", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"name", "videoSourceConfiguration", "videoEncoderConfiguration",
                      "ptzConfiguration", "metadataConfiguration"})
public class Profile {

    @XmlAttribute(name = "token")
    private String token;

    @XmlAttribute(name = "fixed")
    private Boolean fixed;

    @XmlElement(name = "Name", namespace = "http://www.onvif.org/ver10/schema")
    private String name;

    @XmlElement(name = "VideoSourceConfiguration", namespace = "http://www.onvif.org/ver10/schema")
    private VideoSourceConfiguration videoSourceConfiguration;

    @XmlElement(name = "VideoEncoderConfiguration", namespace = "http://www.onvif.org/ver10/schema")
    private VideoEncoderConfiguration videoEncoderConfiguration;

    @XmlElement(name = "PTZConfiguration", namespace = "http://www.onvif.org/ver10/schema")
    private PTZConfiguration ptzConfiguration;

    @XmlElement(name = "MetadataConfiguration", namespace = "http://www.onvif.org/ver10/schema")
    private MetadataConfiguration metadataConfiguration;

    public Profile() {}

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public Boolean getFixed() { return fixed; }
    public void setFixed(Boolean fixed) { this.fixed = fixed; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public VideoSourceConfiguration getVideoSourceConfiguration() { return videoSourceConfiguration; }
    public void setVideoSourceConfiguration(VideoSourceConfiguration vsc) { this.videoSourceConfiguration = vsc; }

    public VideoEncoderConfiguration getVideoEncoderConfiguration() { return videoEncoderConfiguration; }
    public void setVideoEncoderConfiguration(VideoEncoderConfiguration vec) { this.videoEncoderConfiguration = vec; }

    public PTZConfiguration getPtzConfiguration() { return ptzConfiguration; }
    public void setPtzConfiguration(PTZConfiguration ptzConfiguration) { this.ptzConfiguration = ptzConfiguration; }

    public MetadataConfiguration getMetadataConfiguration() { return metadataConfiguration; }
    public void setMetadataConfiguration(MetadataConfiguration metadataConfiguration) {
        this.metadataConfiguration = metadataConfiguration;
    }
}
