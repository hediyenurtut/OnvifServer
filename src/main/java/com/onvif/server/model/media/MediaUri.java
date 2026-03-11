package com.onvif.server.model.media;

import jakarta.xml.bind.annotation.*;

/**
 * ONVIF MediaUri – returned by GetStreamUri and GetSnapshotUri operations.
 */
@XmlRootElement(name = "MediaUri", namespace = "http://www.onvif.org/ver10/schema")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MediaUri", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"uri", "invalidAfterConnect", "invalidAfterReboot", "timeout"})
public class MediaUri {

    @XmlElement(name = "Uri", namespace = "http://www.onvif.org/ver10/schema")
    private String uri;

    @XmlElement(name = "InvalidAfterConnect", namespace = "http://www.onvif.org/ver10/schema")
    private boolean invalidAfterConnect;

    @XmlElement(name = "InvalidAfterReboot", namespace = "http://www.onvif.org/ver10/schema")
    private boolean invalidAfterReboot;

    @XmlElement(name = "Timeout", namespace = "http://www.onvif.org/ver10/schema")
    private String timeout;

    public MediaUri() {}

    public MediaUri(String uri, boolean invalidAfterConnect, boolean invalidAfterReboot, String timeout) {
        this.uri = uri;
        this.invalidAfterConnect = invalidAfterConnect;
        this.invalidAfterReboot = invalidAfterReboot;
        this.timeout = timeout;
    }

    public String getUri() { return uri; }
    public void setUri(String uri) { this.uri = uri; }

    public boolean isInvalidAfterConnect() { return invalidAfterConnect; }
    public void setInvalidAfterConnect(boolean invalidAfterConnect) { this.invalidAfterConnect = invalidAfterConnect; }

    public boolean isInvalidAfterReboot() { return invalidAfterReboot; }
    public void setInvalidAfterReboot(boolean invalidAfterReboot) { this.invalidAfterReboot = invalidAfterReboot; }

    public String getTimeout() { return timeout; }
    public void setTimeout(String timeout) { this.timeout = timeout; }
}
