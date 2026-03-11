package com.onvif.server.endpoint;

import com.onvif.server.model.media.*;
import com.onvif.server.model.ptz.PTZConfiguration;
import com.onvif.server.model.ptz.PTZSpeed;
import com.onvif.server.model.ptz.Vector1D;
import com.onvif.server.model.ptz.Vector2D;
import jakarta.jws.*;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.*;
import jakarta.xml.ws.BindingType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * ONVIF Media2 Service – Profile T zorunlu servisi.
 * H.264 / H.265 video kodlama ve metadata streaming desteği içerir.
 * SOAP 1.2 / Document-Literal-Wrapped bağlaması kullanır.
 *
 * Endpoint: /onvif/media2_service
 * Namespace: http://www.onvif.org/ver20/media/wsdl
 */
@Component
@WebService(
    serviceName     = "Media2Service",
    portName        = "Media2Port",
    targetNamespace = "http://www.onvif.org/ver20/media/wsdl"
)
@SOAPBinding(
    style          = Style.DOCUMENT,
    use            = Use.LITERAL,
    parameterStyle = ParameterStyle.WRAPPED
)
@BindingType(jakarta.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class Media2ServiceEndpoint {

    @Value("${server.port:8080}")
    private int serverPort;

    @Value("${onvif.rtsp.host:192.168.1.100}")
    private String rtspHost;

    @Value("${onvif.rtsp.port:554}")
    private int rtspPort;

    // ----------------------------------------------------------------
    // GetServiceCapabilities
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetServiceCapabilities")
    @WebResult(name = "Capabilities",
               targetNamespace = "http://www.onvif.org/ver20/media/wsdl")
    public String getServiceCapabilities() {
        return "SnapshotUri=true Rotation=false VideoSourceMode=false " +
               "OSD=false TemporaryOSDText=false EXICompression=false " +
               "ProfileCapabilities.MaximumNumberOfProfiles=10 " +
               "ProfileCapabilities.ConfigurationsSupported=VideoSource " +
               "VideoEncoder Audio AudioSource AudioDecoder Metadata Analytics PTZ " +
               "StreamingCapabilities.RTSPStreaming=true " +
               "StreamingCapabilities.RTPMulticast=true " +
               "StreamingCapabilities.RTP_TCP=true " +
               "StreamingCapabilities.RTP_RTSP_TCP=true " +
               "EncodingCapabilities.H264=true EncodingCapabilities.H265=true " +
               "MediaCapabilities.SnapshotUri=true";
    }

    // ----------------------------------------------------------------
    // GetProfiles  (Profile T: Media2 profiles)
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetProfiles")
    @WebResult(name = "Profiles",
               targetNamespace = "http://www.onvif.org/ver20/media/wsdl")
    public List<MediaProfile> getProfiles(
            @WebParam(name = "Token",
                      targetNamespace = "http://www.onvif.org/ver20/media/wsdl")
            String token,
            @WebParam(name = "Type",
                      targetNamespace = "http://www.onvif.org/ver20/media/wsdl")
            List<String> types) {
        return buildProfiles();
    }

    // ----------------------------------------------------------------
    // GetVideoSourceConfigurations
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetVideoSourceConfigurations")
    @WebResult(name = "Configurations",
               targetNamespace = "http://www.onvif.org/ver20/media/wsdl")
    public List<VideoSourceConfiguration> getVideoSourceConfigurations(
            @WebParam(name = "ConfigurationToken",
                      targetNamespace = "http://www.onvif.org/ver20/media/wsdl")
            String configToken,
            @WebParam(name = "ProfileToken",
                      targetNamespace = "http://www.onvif.org/ver20/media/wsdl")
            String profileToken) {
        return buildVideoSourceConfigurations();
    }

    // ----------------------------------------------------------------
    // GetVideoEncoderConfigurations
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetVideoEncoderConfigurations")
    @WebResult(name = "Configurations",
               targetNamespace = "http://www.onvif.org/ver20/media/wsdl")
    public List<VideoEncoderConfiguration> getVideoEncoderConfigurations(
            @WebParam(name = "ConfigurationToken",
                      targetNamespace = "http://www.onvif.org/ver20/media/wsdl")
            String configToken,
            @WebParam(name = "ProfileToken",
                      targetNamespace = "http://www.onvif.org/ver20/media/wsdl")
            String profileToken) {
        return buildVideoEncoderConfigurations();
    }

    // ----------------------------------------------------------------
    // GetMetadataConfigurations
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetMetadataConfigurations")
    @WebResult(name = "Configurations",
               targetNamespace = "http://www.onvif.org/ver20/media/wsdl")
    public List<MetadataConfiguration> getMetadataConfigurations(
            @WebParam(name = "ConfigurationToken",
                      targetNamespace = "http://www.onvif.org/ver20/media/wsdl")
            String configToken,
            @WebParam(name = "ProfileToken",
                      targetNamespace = "http://www.onvif.org/ver20/media/wsdl")
            String profileToken) {
        List<MetadataConfiguration> list = new ArrayList<>();
        list.add(buildMetadataConfiguration());
        return list;
    }

    // ----------------------------------------------------------------
    // GetStreamUri  (Profile T: RTSP stream)
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetStreamUri")
    @WebResult(name = "Uri",
               targetNamespace = "http://www.onvif.org/ver20/media/wsdl")
    public MediaUri getStreamUri(
            @WebParam(name = "ProfileToken",
                      targetNamespace = "http://www.onvif.org/ver20/media/wsdl")
            String profileToken,
            @WebParam(name = "Protocol",
                      targetNamespace = "http://www.onvif.org/ver20/media/wsdl")
            String protocol) {

        String streamProfile = (profileToken != null && !profileToken.isBlank())
                ? profileToken : "profile_main_h264";
        String uri = "rtsp://" + rtspHost + ":" + rtspPort + "/live/" + streamProfile;
        System.out.printf("[Media2] GetStreamUri: profile=%s uri=%s%n", profileToken, uri);
        return new MediaUri(uri, false, false, "PT0S");
    }

    // ----------------------------------------------------------------
    // GetSnapshotUri
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetSnapshotUri")
    @WebResult(name = "Uri",
               targetNamespace = "http://www.onvif.org/ver20/media/wsdl")
    public MediaUri getSnapshotUri(
            @WebParam(name = "ProfileToken",
                      targetNamespace = "http://www.onvif.org/ver20/media/wsdl")
            String profileToken) {

        String snapshotProfile = (profileToken != null && !profileToken.isBlank())
                ? profileToken : "profile_main_h264";
        String uri = "http://" + rtspHost + ":" + serverPort
                + "/onvif/snapshot/" + snapshotProfile;
        System.out.printf("[Media2] GetSnapshotUri: profile=%s uri=%s%n", profileToken, uri);
        return new MediaUri(uri, false, false, "PT30S");
    }

    // ----------------------------------------------------------------
    // AddConfiguration
    // ----------------------------------------------------------------

    @WebMethod(operationName = "AddConfiguration")
    public void addConfiguration(
            @WebParam(name = "ProfileToken",
                      targetNamespace = "http://www.onvif.org/ver20/media/wsdl")
            String profileToken,
            @WebParam(name = "Configuration",
                      targetNamespace = "http://www.onvif.org/ver20/media/wsdl")
            String configuration) {
        System.out.printf("[Media2] AddConfiguration: profile=%s config=%s%n",
                profileToken, configuration);
    }

    // ----------------------------------------------------------------
    // CreateProfile
    // ----------------------------------------------------------------

    @WebMethod(operationName = "CreateProfile")
    @WebResult(name = "Token",
               targetNamespace = "http://www.onvif.org/ver20/media/wsdl")
    public String createProfile(
            @WebParam(name = "Name",
                      targetNamespace = "http://www.onvif.org/ver20/media/wsdl")
            String name,
            @WebParam(name = "Configuration",
                      targetNamespace = "http://www.onvif.org/ver20/media/wsdl")
            List<String> configurations) {
        String token = "profile_" + System.currentTimeMillis();
        System.out.printf("[Media2] CreateProfile: name=%s token=%s%n", name, token);
        return token;
    }

    // ----------------------------------------------------------------
    // DeleteProfile
    // ----------------------------------------------------------------

    @WebMethod(operationName = "DeleteProfile")
    public void deleteProfile(
            @WebParam(name = "Token",
                      targetNamespace = "http://www.onvif.org/ver20/media/wsdl")
            String token) {
        System.out.printf("[Media2] DeleteProfile: token=%s%n", token);
    }

    // ================================================================
    // Builder helpers
    // ================================================================

    private List<MediaProfile> buildProfiles() {
        List<MediaProfile> profiles = new ArrayList<>();

        // H.264 main profile
        MediaProfile h264Profile = new MediaProfile();
        h264Profile.setToken("profile_main_h264");
        h264Profile.setName("Main H.264 Profile");
        h264Profile.setFixed(false);
        ProfileConfigurations h264Configs = new ProfileConfigurations();
        h264Configs.setVideoSource(buildVideoSourceConfiguration("vsc_main", "vs_main"));
        h264Configs.setVideoEncoder(buildH264EncoderConfiguration());
        h264Configs.setPtz(buildPtzConfiguration());
        h264Configs.setMetadata(buildMetadataConfiguration());
        h264Profile.setConfigurations(h264Configs);
        profiles.add(h264Profile);

        // H.265 / HEVC profile (Profile T)
        MediaProfile h265Profile = new MediaProfile();
        h265Profile.setToken("profile_main_h265");
        h265Profile.setName("Main H.265 Profile");
        h265Profile.setFixed(false);
        ProfileConfigurations h265Configs = new ProfileConfigurations();
        h265Configs.setVideoSource(buildVideoSourceConfiguration("vsc_main", "vs_main"));
        h265Configs.setVideoEncoder(buildH265EncoderConfiguration());
        h265Configs.setPtz(buildPtzConfiguration());
        h265Configs.setMetadata(buildMetadataConfiguration());
        h265Profile.setConfigurations(h265Configs);
        profiles.add(h265Profile);

        return profiles;
    }

    private List<VideoSourceConfiguration> buildVideoSourceConfigurations() {
        List<VideoSourceConfiguration> list = new ArrayList<>();
        list.add(buildVideoSourceConfiguration("vsc_main", "vs_main"));
        return list;
    }

    private VideoSourceConfiguration buildVideoSourceConfiguration(String token, String sourceToken) {
        VideoSourceConfiguration vsc = new VideoSourceConfiguration();
        vsc.setToken(token);
        vsc.setName("VideoSourceConfig_Main");
        vsc.setUseCount(2);
        vsc.setSourceToken(sourceToken);
        vsc.setBounds(new IntRectangle(0, 0, 1920, 1080));
        return vsc;
    }

    private List<VideoEncoderConfiguration> buildVideoEncoderConfigurations() {
        List<VideoEncoderConfiguration> list = new ArrayList<>();
        list.add(buildH264EncoderConfiguration());
        list.add(buildH265EncoderConfiguration());
        return list;
    }

    private VideoEncoderConfiguration buildH264EncoderConfiguration() {
        VideoEncoderConfiguration vec = new VideoEncoderConfiguration();
        vec.setToken("vec_h264_main");
        vec.setName("H264_Main_1080p");
        vec.setUseCount(1);
        vec.setEncoding("H264");
        vec.setResolution(new VideoResolution(1920, 1080));
        vec.setQuality(4.0f);
        vec.setRateControl(new VideoRateControl(30.0f, 1, 4096));
        vec.setGovLength(30);
        vec.setProfile("High");
        MulticastConfiguration mc = new MulticastConfiguration();
        mc.setAddress(new IPAddress("IPv4", "0.0.0.0"));
        mc.setPort(0);
        mc.setTtl(5);
        mc.setAutoStart(false);
        vec.setMulticast(mc);
        return vec;
    }

    private VideoEncoderConfiguration buildH265EncoderConfiguration() {
        VideoEncoderConfiguration vec = new VideoEncoderConfiguration();
        vec.setToken("vec_h265_main");
        vec.setName("H265_Main_1080p");
        vec.setUseCount(1);
        vec.setEncoding("H265");
        vec.setResolution(new VideoResolution(1920, 1080));
        vec.setQuality(4.0f);
        vec.setRateControl(new VideoRateControl(30.0f, 1, 2048));
        vec.setGovLength(30);
        vec.setProfile("Main");
        MulticastConfiguration mc = new MulticastConfiguration();
        mc.setAddress(new IPAddress("IPv4", "0.0.0.0"));
        mc.setPort(0);
        mc.setTtl(5);
        mc.setAutoStart(false);
        vec.setMulticast(mc);
        return vec;
    }

    private PTZConfiguration buildPtzConfiguration() {
        PTZConfiguration ptzConf = new PTZConfiguration();
        ptzConf.setToken("ptz_config_main");
        ptzConf.setName("PTZConfig_Main");
        ptzConf.setNodeToken("ptz_node_main");
        ptzConf.setDefaultAbsolutePantTiltPositionSpace(
                "http://www.onvif.org/ver10/tptz/PanTiltSpaces/PositionGenericSpace");
        ptzConf.setDefaultAbsoluteZoomPositionSpace(
                "http://www.onvif.org/ver10/tptz/ZoomSpaces/PositionGenericSpace");
        ptzConf.setDefaultPTZTimeout("PT10S");
        ptzConf.setDefaultPTZSpeed(new PTZSpeed(
                new Vector2D(0.5f, 0.5f), new Vector1D(0.5f)));
        return ptzConf;
    }

    private MetadataConfiguration buildMetadataConfiguration() {
        MetadataConfiguration mc = new MetadataConfiguration();
        mc.setToken("metadata_config_main");
        mc.setName("MetadataConfig_Main");
        mc.setUseCount(1);
        mc.setAnalytics(true);
        mc.setSessionTimeout("PT1M");
        mc.setPtzStatus(new PTZFilter(true, true));
        mc.setEvents(new EventSubscription(
                "tns1:RuleEngine//.* tns1:Device//.* tns1:VideoAnalytics//.*"));
        mc.setCompressionType("None");
        return mc;
    }
}
