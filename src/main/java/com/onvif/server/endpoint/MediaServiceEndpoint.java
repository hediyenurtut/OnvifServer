package com.onvif.server.endpoint;

import com.onvif.server.model.media.*;
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

    private VideoSourceConfiguration buildVideoSourceConfiguration(String token, String sourceToken) {
        VideoSourceConfiguration vsc = new VideoSourceConfiguration();
        vsc.setToken(token);
        vsc.setName("VideoSourceConfig_Main");
        vsc.setUseCount(1);
        vsc.setSourceToken(sourceToken);
        vsc.setBounds(new IntRectangle(0, 0, 1920, 1080));
        return vsc;
    }
}
