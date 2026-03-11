package com.onvif.server.model.media;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MulticastConfiguration", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"address", "port", "ttl", "autoStart"})
public class MulticastConfiguration {

    @XmlElement(name = "Address", namespace = "http://www.onvif.org/ver10/schema")
    private IPAddress address;

    @XmlElement(name = "Port", namespace = "http://www.onvif.org/ver10/schema")
    private int port;

    @XmlElement(name = "TTL", namespace = "http://www.onvif.org/ver10/schema")
    private int ttl;

    @XmlElement(name = "AutoStart", namespace = "http://www.onvif.org/ver10/schema")
    private boolean autoStart;

    public MulticastConfiguration() {}

    public IPAddress getAddress() { return address; }
    public void setAddress(IPAddress address) { this.address = address; }

    public int getPort() { return port; }
    public void setPort(int port) { this.port = port; }

    public int getTtl() { return ttl; }
    public void setTtl(int ttl) { this.ttl = ttl; }

    public boolean isAutoStart() { return autoStart; }
    public void setAutoStart(boolean autoStart) { this.autoStart = autoStart; }
}
