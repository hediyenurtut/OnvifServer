package com.onvif.server.model.event;

import jakarta.xml.bind.annotation.*;

/**
 * ONVIF Event Service subscription endpoint reference.
 * Holds the address of the subscription manager or pull-point.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EndpointReference",
         namespace = "http://www.onvif.org/ver10/events/wsdl",
         propOrder = {"address"})
public class EndpointReference {

    @XmlElement(name = "Address", namespace = "http://www.onvif.org/ver10/events/wsdl")
    private String address;

    public EndpointReference() {}

    public EndpointReference(String address) {
        this.address = address;
    }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
