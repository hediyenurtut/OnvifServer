package com.onvif.server.config;

import com.onvif.server.endpoint.*;
import jakarta.xml.ws.Endpoint;
import jakarta.xml.ws.soap.SOAPBinding;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Apache CXF – SOAP 1.2 web servis endpoint kayıt konfigürasyonu.
 *
 * Her ONVIF Profile T servisi /onvif/* yolunda yayınlanır ve
 * SOAP 1.2 bağlaması (SOAP12HTTP_BINDING) kullanır.
 *
 * Hizmet URL'leri:
 *   Device Management : /onvif/device_service
 *   Media (Profile S) : /onvif/media_service
 *   Media2 (Profile T) : /onvif/media2_service
 *   PTZ               : /onvif/ptz_service
 *   Events            : /onvif/event_service
 *   Imaging           : /onvif/imaging_service
 *   Analytics         : /onvif/analytics_service
 */
@Configuration
public class CxfWebServiceConfig {

    @Bean
    public Endpoint deviceEndpoint(Bus bus, DeviceServiceEndpoint deviceService) {
        EndpointImpl ep = new EndpointImpl(bus, deviceService);
        ep.setBindingUri(SOAPBinding.SOAP12HTTP_BINDING);
        ep.publish("/onvif/device_service");
        return ep;
    }

    @Bean
    public Endpoint mediaEndpoint(Bus bus, MediaServiceEndpoint mediaService) {
        EndpointImpl ep = new EndpointImpl(bus, mediaService);
        ep.setBindingUri(SOAPBinding.SOAP12HTTP_BINDING);
        ep.publish("/onvif/media_service");
        return ep;
    }

    @Bean
    public Endpoint media2Endpoint(Bus bus, Media2ServiceEndpoint media2Service) {
        EndpointImpl ep = new EndpointImpl(bus, media2Service);
        ep.setBindingUri(SOAPBinding.SOAP12HTTP_BINDING);
        ep.publish("/onvif/media2_service");
        return ep;
    }

    @Bean
    public Endpoint ptzEndpoint(Bus bus, PtzServiceEndpoint ptzService) {
        EndpointImpl ep = new EndpointImpl(bus, ptzService);
        ep.setBindingUri(SOAPBinding.SOAP12HTTP_BINDING);
        ep.publish("/onvif/ptz_service");
        return ep;
    }

    @Bean
    public Endpoint eventEndpoint(Bus bus, EventServiceEndpoint eventService) {
        EndpointImpl ep = new EndpointImpl(bus, eventService);
        ep.setBindingUri(SOAPBinding.SOAP12HTTP_BINDING);
        ep.publish("/onvif/event_service");
        return ep;
    }

    @Bean
    public Endpoint imagingEndpoint(Bus bus, ImagingServiceEndpoint imagingService) {
        EndpointImpl ep = new EndpointImpl(bus, imagingService);
        ep.setBindingUri(SOAPBinding.SOAP12HTTP_BINDING);
        ep.publish("/onvif/imaging_service");
        return ep;
    }

    @Bean
    public Endpoint analyticsEndpoint(Bus bus, AnalyticsServiceEndpoint analyticsService) {
        EndpointImpl ep = new EndpointImpl(bus, analyticsService);
        ep.setBindingUri(SOAPBinding.SOAP12HTTP_BINDING);
        ep.publish("/onvif/analytics_service");
        return ep;
    }
}
