package com.onvif.server.model.device;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeviceCapabilityInfo", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"xAddr", "network", "system", "io", "security"})
public class DeviceCapabilityInfo {

    @XmlElement(name = "XAddr", namespace = "http://www.onvif.org/ver10/schema")
    private String xAddr;

    @XmlElement(name = "Network", namespace = "http://www.onvif.org/ver10/schema")
    private NetworkCapabilities network;

    @XmlElement(name = "System", namespace = "http://www.onvif.org/ver10/schema")
    private SystemCapabilities system;

    @XmlElement(name = "IO", namespace = "http://www.onvif.org/ver10/schema")
    private IOCapabilities io;

    @XmlElement(name = "Security", namespace = "http://www.onvif.org/ver10/schema")
    private SecurityCapabilities security;

    public DeviceCapabilityInfo() {}

    public String getXAddr() { return xAddr; }
    public void setXAddr(String xAddr) { this.xAddr = xAddr; }

    public NetworkCapabilities getNetwork() { return network; }
    public void setNetwork(NetworkCapabilities network) { this.network = network; }

    public SystemCapabilities getSystem() { return system; }
    public void setSystem(SystemCapabilities system) { this.system = system; }

    public IOCapabilities getIo() { return io; }
    public void setIo(IOCapabilities io) { this.io = io; }

    public SecurityCapabilities getSecurity() { return security; }
    public void setSecurity(SecurityCapabilities security) { this.security = security; }
}
