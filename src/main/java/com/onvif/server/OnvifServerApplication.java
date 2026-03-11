package com.onvif.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ONVIF Profile T - SOAP 1.2 Server
 * Spring Boot uygulama giriş noktası
 */
@SpringBootApplication
public class OnvifServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnvifServerApplication.class, args);
    }
}
