package com.onvif.server.model.device;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NetworkCapabilities", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"ipFilter", "zeroConfiguration", "ipVersion6", "dynDns"})
public class NetworkCapabilities {

    @XmlElement(name = "IPFilter", namespace = "http://www.onvif.org/ver10/schema")
    private boolean ipFilter;

    @XmlElement(name = "ZeroConfiguration", namespace = "http://www.onvif.org/ver10/schema")
    private boolean zeroConfiguration;

    @XmlElement(name = "IPVersion6", namespace = "http://www.onvif.org/ver10/schema")
    private boolean ipVersion6;

    @XmlElement(name = "DynDNS", namespace = "http://www.onvif.org/ver10/schema")
    private boolean dynDns;

    public NetworkCapabilities() {}

    public boolean isIpFilter() { return ipFilter; }
    public void setIpFilter(boolean ipFilter) { this.ipFilter = ipFilter; }

    public boolean isZeroConfiguration() { return zeroConfiguration; }
    public void setZeroConfiguration(boolean zeroConfiguration) { this.zeroConfiguration = zeroConfiguration; }

    public boolean isIpVersion6() { return ipVersion6; }
    public void setIpVersion6(boolean ipVersion6) { this.ipVersion6 = ipVersion6; }

    public boolean isDynDns() { return dynDns; }
    public void setDynDns(boolean dynDns) { this.dynDns = dynDns; }
}
