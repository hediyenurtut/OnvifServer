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
 * ONVIF Media Service – Profile S servisi.
 * H.264 video kodlama ve temel media işlemleri için kullanılır.
 * SOAP 1.2 / Document-Literal-Wrapped bağlaması kullanır.
 *
 * Endpoint: /onvif/media_service
 * Namespace: http://www.onvif.org/ver10/media/wsdl
 */
@Component
@WebService(
    serviceName     = "MediaService",
    portName        = "MediaPort",
    targetNamespace = "http://www.onvif.org/ver10/media/wsdl"
)
@SOAPBinding(
    style          = Style.DOCUMENT,
    use            = Use.LITERAL,
    parameterStyle = ParameterStyle.WRAPPED
)
@BindingType(jakarta.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class MediaServiceEndpoint {

    @Value("${server.port:8080}")
    private int serverPort;

    @Value("${onvif.rtsp.host:192.168.1.100}")
    private String rtspHost;

    @Value("${onvif.rtsp.port:554}")
    private int rtspPort;

    // ----------------------------------------------------------------
    // GetVideoSources
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetVideoSources")
    @WebResult(name = "VideoSources",
               targetNamespace = "http://www.onvif.org/ver10/media/wsdl")
    public List<VideoSource> getVideoSources() {
        List<VideoSource> sources = new ArrayList<>();
        sources.add(new VideoSource("vs_main", 30.0f, new VideoResolution(1920, 1080)));
        return sources;
    }

    // ----------------------------------------------------------------
    // GetProfiles
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetProfiles")
    @WebResult(name = "Profiles",
               targetNamespace = "http://www.onvif.org/ver10/media/wsdl")
    public List<Profile> getProfiles(
            @WebParam(name = "ProfileToken",
                      targetNamespace = "http://www.onvif.org/ver10/media/wsdl")
            String profileToken) {
        return buildProfiles();
    }

    // ----------------------------------------------------------------
    // GetVideoSourceConfigurations
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetVideoSourceConfigurations")
    @WebResult(name = "VideoSourceConfigurations",
               targetNamespace = "http://www.onvif.org/ver10/media/wsdl")
    public List<VideoSourceConfiguration> getVideoSourceConfigurations() {
        List<VideoSourceConfiguration> list = new ArrayList<>();
        list.add(buildVideoSourceConfiguration("vsc_main", "vs_main"));
        return list;
    }

    // ----------------------------------------------------------------
    // GetVideoEncoderConfigurations
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetVideoEncoderConfigurations")
    @WebResult(name = "VideoEncoderConfigurations",
               targetNamespace = "http://www.onvif.org/ver10/media/wsdl")
    public List<VideoEncoderConfiguration> getVideoEncoderConfigurations() {
        return buildVideoEncoderConfigurations();
    }

    // ----------------------------------------------------------------
    // GetStreamUri
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetStreamUri")
    @WebResult(name = "MediaUri",
               targetNamespace = "http://www.onvif.org/ver10/media/wsdl")
    public MediaUri getStreamUri(
            @WebParam(name = "StreamSetup",
                      targetNamespace = "http://www.onvif.org/ver10/media/wsdl")
            StreamSetup streamSetup,
            @WebParam(name = "ProfileToken",
                      targetNamespace = "http://www.onvif.org/ver10/media/wsdl")
            String profileToken) {

        String streamProfile = (profileToken != null && !profileToken.isBlank())
                ? profileToken : "profile_main_h264";
        String uri = "rtsp://" + rtspHost + ":" + rtspPort + "/live/" + streamProfile;
        System.out.printf("[Media] GetStreamUri: profile=%s uri=%s%n", profileToken, uri);
        return new MediaUri(uri, false, false, "PT0S");
    }

    // ----------------------------------------------------------------
    // GetSnapshotUri
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetSnapshotUri")
    @WebResult(name = "MediaUri",
               targetNamespace = "http://www.onvif.org/ver10/media/wsdl")
    public MediaUri getSnapshotUri(
            @WebParam(name = "ProfileToken",
                      targetNamespace = "http://www.onvif.org/ver10/media/wsdl")
            String profileToken) {

        String snapshotProfile = (profileToken != null && !profileToken.isBlank())
                ? profileToken : "profile_main_h264";
        String uri = "http://" + rtspHost + ":" + serverPort
                + "/onvif/snapshot/" + snapshotProfile;
        System.out.printf("[Media] GetSnapshotUri: profile=%s uri=%s%n", profileToken, uri);
        return new MediaUri(uri, false, false, "PT30S");
    }

    // ================================================================
    // Builder helpers
    // ================================================================

    private List<Profile> buildProfiles() {
        List<Profile> profiles = new ArrayList<>();

        Profile h264Profile = new Profile();
        h264Profile.setToken("profile_main_h264");
        h264Profile.setName("Main H.264 Profile");
        h264Profile.setFixed(false);
        h264Profile.setVideoSourceConfiguration(buildVideoSourceConfiguration("vsc_main", "vs_main"));
        h264Profile.setVideoEncoderConfiguration(buildH264EncoderConfiguration());
        h264Profile.setPtzConfiguration(buildPtzConfiguration());
        h264Profile.setMetadataConfiguration(buildMetadataConfiguration());
        profiles.add(h264Profile);

        return profiles;
    }

    private VideoSourceConfiguration buildVideoSourceConfiguration(String token, String sourceToken) {
        VideoSourceConfiguration vsc = new VideoSourceConfiguration();
        vsc.setToken(token);
        vsc.setName("VideoSourceConfig_Main");
        vsc.setUseCount(1);
        vsc.setSourceToken(sourceToken);
        vsc.setBounds(new IntRectangle(0, 0, 1920, 1080));
        return vsc;
    }

    private List<VideoEncoderConfiguration> buildVideoEncoderConfigurations() {
        List<VideoEncoderConfiguration> list = new ArrayList<>();
        list.add(buildH264EncoderConfiguration());
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
