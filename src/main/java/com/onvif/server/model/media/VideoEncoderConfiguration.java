package com.onvif.server.model.media;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "VideoEncoder2Configuration", namespace = "http://www.onvif.org/ver10/schema")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VideoEncoder2Configuration", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"name", "useCount", "encoding", "resolution", "quality",
                      "rateControl", "multicast", "govLength", "profile"})
public class VideoEncoderConfiguration {

    @XmlAttribute(name = "token")
    private String token;

    @XmlElement(name = "Name", namespace = "http://www.onvif.org/ver10/schema")
    private String name;

    @XmlElement(name = "UseCount", namespace = "http://www.onvif.org/ver10/schema")
    private int useCount;

    /**
     * Encoding: H264, H265, JPEG (Profile T requires H.264 or H.265)
     */
    @XmlElement(name = "Encoding", namespace = "http://www.onvif.org/ver10/schema")
    private String encoding;

    @XmlElement(name = "Resolution", namespace = "http://www.onvif.org/ver10/schema")
    private VideoResolution resolution;

    @XmlElement(name = "Quality", namespace = "http://www.onvif.org/ver10/schema")
    private float quality;

    @XmlElement(name = "RateControl", namespace = "http://www.onvif.org/ver10/schema")
    private VideoRateControl rateControl;

    @XmlElement(name = "Multicast", namespace = "http://www.onvif.org/ver10/schema")
    private MulticastConfiguration multicast;

    /** Group of Video frames length */
    @XmlElement(name = "GovLength", namespace = "http://www.onvif.org/ver10/schema")
    private int govLength;

    /** H264: Baseline/Main/High/High10/High422/High444, H265: Main/Main10/... */
    @XmlElement(name = "Profile", namespace = "http://www.onvif.org/ver10/schema")
    private String profile;

    public VideoEncoderConfiguration() {}

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getUseCount() { return useCount; }
    public void setUseCount(int useCount) { this.useCount = useCount; }

    public String getEncoding() { return encoding; }
    public void setEncoding(String encoding) { this.encoding = encoding; }

    public VideoResolution getResolution() { return resolution; }
    public void setResolution(VideoResolution resolution) { this.resolution = resolution; }

    public float getQuality() { return quality; }
    public void setQuality(float quality) { this.quality = quality; }

    public VideoRateControl getRateControl() { return rateControl; }
    public void setRateControl(VideoRateControl rateControl) { this.rateControl = rateControl; }

    public MulticastConfiguration getMulticast() { return multicast; }
    public void setMulticast(MulticastConfiguration multicast) { this.multicast = multicast; }

    public int getGovLength() { return govLength; }
    public void setGovLength(int govLength) { this.govLength = govLength; }

    public String getProfile() { return profile; }
    public void setProfile(String profile) { this.profile = profile; }
}
