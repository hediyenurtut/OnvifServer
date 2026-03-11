package com.onvif.server.model.media;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IPAddress", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"type", "ipv4Address", "ipv6Address"})
public class IPAddress {

    @XmlElement(name = "Type", namespace = "http://www.onvif.org/ver10/schema")
    private String type;

    @XmlElement(name = "IPv4Address", namespace = "http://www.onvif.org/ver10/schema")
    private String ipv4Address;

    @XmlElement(name = "IPv6Address", namespace = "http://www.onvif.org/ver10/schema")
    private String ipv6Address;

    public IPAddress() {}

    public IPAddress(String type, String ipv4Address) {
        this.type = type;
        this.ipv4Address = ipv4Address;
    }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getIpv4Address() { return ipv4Address; }
    public void setIpv4Address(String ipv4Address) { this.ipv4Address = ipv4Address; }

    public String getIpv6Address() { return ipv6Address; }
    public void setIpv6Address(String ipv6Address) { this.ipv6Address = ipv6Address; }
}
