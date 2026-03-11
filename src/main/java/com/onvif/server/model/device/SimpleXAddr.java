package com.onvif.server.model.device;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SimpleXAddr", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"xAddr"})
public class SimpleXAddr {

    @XmlElement(name = "XAddr", namespace = "http://www.onvif.org/ver10/schema")
    private String xAddr;

    public SimpleXAddr() {}

    public SimpleXAddr(String xAddr) {
        this.xAddr = xAddr;
    }

    public String getXAddr() { return xAddr; }
    public void setXAddr(String xAddr) { this.xAddr = xAddr; }
}
