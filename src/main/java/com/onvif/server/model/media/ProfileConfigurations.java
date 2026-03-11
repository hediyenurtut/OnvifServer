package com.onvif.server.model.media;

import com.onvif.server.model.ptz.PTZConfiguration;
import jakarta.xml.bind.annotation.*;

/**
 * Media2 profile configurations wrapper – holds all sub-configurations
 * for a media profile as required by Profile T / Media2 Service.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConfigurationSet", namespace = "http://www.onvif.org/ver20/media/wsdl",
         propOrder = {"videoSource", "videoEncoder", "ptz", "metadata"})
public class ProfileConfigurations {

    @XmlElement(name = "VideoSource", namespace = "http://www.onvif.org/ver20/media/wsdl")
    private VideoSourceConfiguration videoSource;

    @XmlElement(name = "VideoEncoder", namespace = "http://www.onvif.org/ver20/media/wsdl")
    private VideoEncoderConfiguration videoEncoder;

    @XmlElement(name = "PTZ", namespace = "http://www.onvif.org/ver20/media/wsdl")
    private PTZConfiguration ptz;

    @XmlElement(name = "Metadata", namespace = "http://www.onvif.org/ver20/media/wsdl")
    private MetadataConfiguration metadata;

    public ProfileConfigurations() {}

    public VideoSourceConfiguration getVideoSource() { return videoSource; }
    public void setVideoSource(VideoSourceConfiguration vs) { this.videoSource = vs; }

    public VideoEncoderConfiguration getVideoEncoder() { return videoEncoder; }
    public void setVideoEncoder(VideoEncoderConfiguration ve) { this.videoEncoder = ve; }

    public PTZConfiguration getPtz() { return ptz; }
    public void setPtz(PTZConfiguration ptz) { this.ptz = ptz; }

    public MetadataConfiguration getMetadata() { return metadata; }
    public void setMetadata(MetadataConfiguration metadata) { this.metadata = metadata; }
}
