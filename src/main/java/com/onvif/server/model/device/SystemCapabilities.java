package com.onvif.server.model.device;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SystemCapabilities", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"discoveryResolve", "discoveryBye", "remoteDiscovery", "systemBackup",
                      "systemLogging", "firmwareUpgrade", "httpFirmwareUpgrade",
                      "httpSystemBackup", "httpSystemLogging", "httpSupportInformation"})
public class SystemCapabilities {

    @XmlElement(name = "DiscoveryResolve", namespace = "http://www.onvif.org/ver10/schema")
    private boolean discoveryResolve;

    @XmlElement(name = "DiscoveryBye", namespace = "http://www.onvif.org/ver10/schema")
    private boolean discoveryBye;

    @XmlElement(name = "RemoteDiscovery", namespace = "http://www.onvif.org/ver10/schema")
    private boolean remoteDiscovery;

    @XmlElement(name = "SystemBackup", namespace = "http://www.onvif.org/ver10/schema")
    private boolean systemBackup;

    @XmlElement(name = "SystemLogging", namespace = "http://www.onvif.org/ver10/schema")
    private boolean systemLogging;

    @XmlElement(name = "FirmwareUpgrade", namespace = "http://www.onvif.org/ver10/schema")
    private boolean firmwareUpgrade;

    @XmlElement(name = "HttpFirmwareUpgrade", namespace = "http://www.onvif.org/ver10/schema")
    private boolean httpFirmwareUpgrade;

    @XmlElement(name = "HttpSystemBackup", namespace = "http://www.onvif.org/ver10/schema")
    private boolean httpSystemBackup;

    @XmlElement(name = "HttpSystemLogging", namespace = "http://www.onvif.org/ver10/schema")
    private boolean httpSystemLogging;

    @XmlElement(name = "HttpSupportInformation", namespace = "http://www.onvif.org/ver10/schema")
    private boolean httpSupportInformation;

    public SystemCapabilities() {}

    public boolean isDiscoveryResolve() { return discoveryResolve; }
    public void setDiscoveryResolve(boolean v) { this.discoveryResolve = v; }
    public boolean isDiscoveryBye() { return discoveryBye; }
    public void setDiscoveryBye(boolean v) { this.discoveryBye = v; }
    public boolean isRemoteDiscovery() { return remoteDiscovery; }
    public void setRemoteDiscovery(boolean v) { this.remoteDiscovery = v; }
    public boolean isSystemBackup() { return systemBackup; }
    public void setSystemBackup(boolean v) { this.systemBackup = v; }
    public boolean isSystemLogging() { return systemLogging; }
    public void setSystemLogging(boolean v) { this.systemLogging = v; }
    public boolean isFirmwareUpgrade() { return firmwareUpgrade; }
    public void setFirmwareUpgrade(boolean v) { this.firmwareUpgrade = v; }
    public boolean isHttpFirmwareUpgrade() { return httpFirmwareUpgrade; }
    public void setHttpFirmwareUpgrade(boolean v) { this.httpFirmwareUpgrade = v; }
    public boolean isHttpSystemBackup() { return httpSystemBackup; }
    public void setHttpSystemBackup(boolean v) { this.httpSystemBackup = v; }
    public boolean isHttpSystemLogging() { return httpSystemLogging; }
    public void setHttpSystemLogging(boolean v) { this.httpSystemLogging = v; }
    public boolean isHttpSupportInformation() { return httpSupportInformation; }
    public void setHttpSupportInformation(boolean v) { this.httpSupportInformation = v; }
}
