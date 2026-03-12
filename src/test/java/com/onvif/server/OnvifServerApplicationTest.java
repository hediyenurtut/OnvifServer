package com.onvif.server;

import com.onvif.server.endpoint.*;
import com.onvif.server.model.ptz.*;
import com.onvif.server.model.device.*;
import com.onvif.server.model.media.*;
import com.onvif.server.model.imaging.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ONVIF Profile T server integration tests.
 * Tüm servislerin beklenen yanıtları döndürdüğünü doğrular.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OnvifServerApplicationTest {

    @Autowired
    private DeviceServiceEndpoint deviceService;

    @Autowired
    private MediaServiceEndpoint mediaService;

    @Autowired
    private Media2ServiceEndpoint media2Service;

    @Autowired
    private PtzServiceEndpoint ptzService;

    @Autowired
    private EventServiceEndpoint eventService;

    @Autowired
    private ImagingServiceEndpoint imagingService;

    @Autowired
    private AnalyticsServiceEndpoint analyticsService;

    // ----------------------------------------------------------------
    // Device Service Tests
    // ----------------------------------------------------------------

    @Test
    void testGetDeviceInformation() {
        DeviceInformation info = deviceService.getDeviceInformation();
        assertNotNull(info);
        assertNotNull(info.getManufacturer());
        assertNotNull(info.getModel());
        assertNotNull(info.getFirmwareVersion());
        assertNotNull(info.getSerialNumber());
        assertNotNull(info.getHardwareId());
    }

    @Test
    void testGetSystemDateAndTime() {
        SystemDateAndTime sdt = deviceService.getSystemDateAndTime();
        assertNotNull(sdt);
        assertNotNull(sdt.getDateTimeType());
        assertNotNull(sdt.getUtcDateTime());
    }

    @Test
    void testGetCapabilities() {
        DeviceCapabilities caps = deviceService.getCapabilities("All");
        assertNotNull(caps);
        assertNotNull(caps.getDevice());
        assertNotNull(caps.getMedia());
        assertNotNull(caps.getPtz());
        assertNotNull(caps.getEvents());
        assertNotNull(caps.getImaging());
        assertNotNull(caps.getAnalytics());
    }

    @Test
    void testGetHostname() {
        String hostname = deviceService.getHostname();
        assertNotNull(hostname);
        assertFalse(hostname.isBlank());
    }

    // ----------------------------------------------------------------
    // Media Service Tests (Profile S)
    // ----------------------------------------------------------------

    @Test
    void testGetVideoSources() {
        List<VideoSource> sources = mediaService.getVideoSources();
        assertNotNull(sources);
        assertFalse(sources.isEmpty());
        VideoSource source = sources.get(0);
        assertNotNull(source.getToken());
        assertNotNull(source.getResolution());
        assertTrue(source.getFramerate() > 0);
    }

    @Test
    void testGetMediaProfiles() {
        List<Profile> profiles = mediaService.getProfiles(null);
        assertNotNull(profiles);
        assertFalse(profiles.isEmpty());
        Profile profile = profiles.get(0);
        assertNotNull(profile.getToken());
        assertNotNull(profile.getName());
        assertNotNull(profile.getVideoSourceConfiguration());
        assertNotNull(profile.getVideoEncoderConfiguration());
    }

    @Test
    void testGetMediaVideoEncoderConfigurations() {
        List<VideoEncoderConfiguration> configs = mediaService.getVideoEncoderConfigurations();
        assertNotNull(configs);
        assertFalse(configs.isEmpty());
        VideoEncoderConfiguration config = configs.get(0);
        assertNotNull(config.getToken());
        assertNotNull(config.getEncoding());
        assertTrue("H264".equalsIgnoreCase(config.getEncoding()),
                "Media service must have an H.264 encoder configuration");
    }

    @Test
    void testGetMediaVideoSourceConfigurations() {
        List<VideoSourceConfiguration> configs = mediaService.getVideoSourceConfigurations();
        assertNotNull(configs);
        assertFalse(configs.isEmpty());
    }

    // ----------------------------------------------------------------
    // Media2 Service Tests
    // ----------------------------------------------------------------

    @Test
    void testGetMedia2Profiles() {
        List<MediaProfile> profiles = media2Service.getProfiles(null, null);
        assertNotNull(profiles);
        assertFalse(profiles.isEmpty());
        // Profile T requires H.264 and/or H.265 profiles
        boolean hasH264 = profiles.stream().anyMatch(p ->
                p.getConfigurations() != null &&
                p.getConfigurations().getVideoEncoder() != null &&
                "H264".equalsIgnoreCase(p.getConfigurations().getVideoEncoder().getEncoding()));
        assertTrue(hasH264, "Profile T requires at least one H.264 profile");
    }

    @Test
    void testGetStreamUri() {
        MediaUri uri = media2Service.getStreamUri("profile_main_h264", "RTSP");
        assertNotNull(uri);
        assertNotNull(uri.getUri());
        assertTrue(uri.getUri().startsWith("rtsp://"),
                "Stream URI must be an RTSP URI");
    }

    @Test
    void testGetSnapshotUri() {
        MediaUri uri = media2Service.getSnapshotUri("profile_main_h264");
        assertNotNull(uri);
        assertNotNull(uri.getUri());
        assertTrue(uri.getUri().startsWith("http://"),
                "Snapshot URI must be an HTTP URI");
    }

    @Test
    void testGetVideoEncoderConfigurations() {
        List<VideoEncoderConfiguration> configs =
                media2Service.getVideoEncoderConfigurations(null, null);
        assertNotNull(configs);
        assertFalse(configs.isEmpty());
        // Profile T mandates H.264 or H.265
        long h264Count = configs.stream()
                .filter(c -> "H264".equalsIgnoreCase(c.getEncoding())).count();
        long h265Count = configs.stream()
                .filter(c -> "H265".equalsIgnoreCase(c.getEncoding())).count();
        assertTrue(h264Count + h265Count > 0,
                "Profile T requires at least one H.264 or H.265 encoder configuration");
    }

    @Test
    void testGetVideoSourceConfigurations() {
        List<VideoSourceConfiguration> configs =
                media2Service.getVideoSourceConfigurations(null, null);
        assertNotNull(configs);
        assertFalse(configs.isEmpty());
    }

    @Test
    void testGetMetadataConfigurations() {
        List<MetadataConfiguration> configs =
                media2Service.getMetadataConfigurations(null, null);
        assertNotNull(configs);
        assertFalse(configs.isEmpty());
        // Metadata analytics must be enabled for Profile T
        assertTrue(configs.get(0).isAnalytics(),
                "Profile T metadata configuration must have analytics enabled");
    }

    // ----------------------------------------------------------------
    // PTZ Service Tests
    // ----------------------------------------------------------------

    @Test
    void testGetPtzNodes() {
        List<PTZNode> nodes = ptzService.getNodes();
        assertNotNull(nodes);
        assertFalse(nodes.isEmpty());
        PTZNode node = nodes.get(0);
        assertNotNull(node.getToken());
        assertTrue(node.getMaximumNumberOfPresets() > 0);
    }

    @Test
    void testGetPtzConfigurations() {
        List<PTZConfiguration> configs = ptzService.getConfigurations();
        assertNotNull(configs);
        assertFalse(configs.isEmpty());
        PTZConfiguration config = configs.get(0);
        assertNotNull(config.getToken());
        assertNotNull(config.getNodeToken());
    }

    @Test
    void testPtzAbsoluteMoveAndGetStatus() {
        PTZVector position = new PTZVector(new Vector2D(0.5f, 0.3f), new Vector1D(0.2f));
        ptzService.absoluteMove("profile_main_h264", position, null);

        PTZStatus status = ptzService.getStatus("profile_main_h264");
        assertNotNull(status);
        assertNotNull(status.getPosition());
        assertEquals(0.5f, status.getPosition().getPanTilt().getX(), 0.001f);
        assertEquals(0.3f, status.getPosition().getPanTilt().getY(), 0.001f);
        assertEquals(0.2f, status.getPosition().getZoom().getX(), 0.001f);
        assertEquals("IDLE", status.getMoveStatus().getPanTilt());
    }

    @Test
    void testPtzRelativeMove() {
        ptzService.absoluteMove("profile", new PTZVector(new Vector2D(0.0f, 0.0f), new Vector1D(0.0f)), null);
        ptzService.relativeMove("profile",
                new PTZVector(new Vector2D(0.2f, 0.1f), new Vector1D(0.1f)), null);
        PTZStatus status = ptzService.getStatus("profile");
        assertEquals(0.2f, status.getPosition().getPanTilt().getX(), 0.001f);
        assertEquals(0.1f, status.getPosition().getPanTilt().getY(), 0.001f);
    }

    @Test
    void testPtzPresetWorkflow() {
        // Set to known position
        ptzService.absoluteMove("profile",
                new PTZVector(new Vector2D(0.7f, 0.4f), new Vector1D(0.5f)), null);

        // Save preset
        String token = ptzService.setPreset("profile", "TestPreset", null);
        assertNotNull(token);

        // Move away
        ptzService.absoluteMove("profile",
                new PTZVector(new Vector2D(0.0f, 0.0f), new Vector1D(0.0f)), null);

        // Goto preset
        ptzService.gotoPreset("profile", token, null);
        PTZStatus status = ptzService.getStatus("profile");
        assertEquals(0.7f, status.getPosition().getPanTilt().getX(), 0.001f);

        // Get presets
        List<PTZPreset> presets = ptzService.getPresets("profile");
        assertTrue(presets.stream().anyMatch(p -> token.equals(p.getToken())));

        // Remove preset
        ptzService.removePreset("profile", token);
        presets = ptzService.getPresets("profile");
        assertTrue(presets.stream().noneMatch(p -> token.equals(p.getToken())));
    }

    @Test
    void testPtzHomePosition() {
        ptzService.absoluteMove("profile",
                new PTZVector(new Vector2D(0.8f, 0.6f), new Vector1D(0.3f)), null);
        ptzService.gotoHomePosition("profile", null);
        PTZStatus status = ptzService.getStatus("profile");
        assertEquals(0.0f, status.getPosition().getPanTilt().getX(), 0.001f);
        assertEquals(0.0f, status.getPosition().getPanTilt().getY(), 0.001f);
        assertEquals(0.0f, status.getPosition().getZoom().getX(), 0.001f);
    }

    @Test
    void testPtzRelativeMoveClampsBounds() {
        ptzService.absoluteMove("profile",
                new PTZVector(new Vector2D(0.9f, 0.9f), new Vector1D(0.0f)), null);
        ptzService.relativeMove("profile",
                new PTZVector(new Vector2D(0.5f, 0.5f), new Vector1D(0.0f)), null);
        PTZStatus status = ptzService.getStatus("profile");
        assertEquals(1.0f, status.getPosition().getPanTilt().getX(), 0.001f);
        assertEquals(1.0f, status.getPosition().getPanTilt().getY(), 0.001f);
    }

    // ----------------------------------------------------------------
    // Event Service Tests
    // ----------------------------------------------------------------

    @Test
    void testCreatePullPointSubscription() {
        var response = eventService.createPullPointSubscription(null, "PT1H", null);
        assertNotNull(response);
        assertNotNull(response.getSubscriptionReference());
        assertNotNull(response.getSubscriptionReference().getAddress());
        assertNotNull(response.getCurrentTime());
        assertNotNull(response.getTerminationTime());
    }

    @Test
    void testSubscribeAndRenew() {
        var subResponse = eventService.subscribe("http://client/notify", null, "PT1H");
        assertNotNull(subResponse);
        assertNotNull(subResponse.getSubscriptionReference().getAddress());

        String renewed = eventService.renew("PT1H");
        assertNotNull(renewed);
    }

    @Test
    void testGetEventProperties() {
        String props = eventService.getEventProperties();
        assertNotNull(props);
        assertFalse(props.isBlank());
        assertTrue(props.contains("tns1:"));
    }

    // ----------------------------------------------------------------
    // Imaging Service Tests
    // ----------------------------------------------------------------

    @Test
    void testGetImagingSettings() {
        ImagingSettings settings = imagingService.getImagingSettings("vs_main");
        assertNotNull(settings);
        assertNotNull(settings.getBrightness());
        assertNotNull(settings.getContrast());
        assertNotNull(settings.getWhiteBalance());
        assertNotNull(settings.getExposure());
        assertNotNull(settings.getFocus());
    }

    @Test
    void testSetImagingSettings() {
        ImagingSettings settings = imagingService.getImagingSettings("vs_test");
        settings.setBrightness(75.0f);
        imagingService.setImagingSettings("vs_test", settings, false);

        ImagingSettings updated = imagingService.getImagingSettings("vs_test");
        assertEquals(75.0f, updated.getBrightness(), 0.001f);
    }

    // ----------------------------------------------------------------
    // Analytics Service Tests
    // ----------------------------------------------------------------

    @Test
    void testGetSupportedRules() {
        String supported = analyticsService.getSupportedRules("analytics_main");
        assertNotNull(supported);
        assertFalse(supported.isBlank());
        assertTrue(supported.contains("tt:"));
    }

    @Test
    void testCreateAndDeleteRules() {
        List<String> newRules = List.of(
                "type=tt:LineDetector name=TestLine",
                "type=tt:FieldDetector name=TestField"
        );
        analyticsService.createRules("analytics_main", newRules);

        List<String> rules = analyticsService.getRules("analytics_main");
        assertFalse(rules.isEmpty());

        // Get the token of first rule and delete
        String firstRuleToken = "rule_0";
        analyticsService.deleteRules("analytics_main", List.of(firstRuleToken));
    }

    @Test
    void testGetSupportedAnalyticsModules() {
        String modules = analyticsService.getSupportedAnalyticsModules("analytics_main");
        assertNotNull(modules);
        assertTrue(modules.contains("tt:ObjectTracker") ||
                   modules.contains("tt:ObjectClassifier"),
                "Should support standard ONVIF analytics module types");
    }
}
