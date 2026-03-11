package com.onvif.server.model.device;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IOCapabilities", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"inputConnectors", "relayOutputs"})
public class IOCapabilities {

    @XmlElement(name = "InputConnectors", namespace = "http://www.onvif.org/ver10/schema")
    private int inputConnectors;

    @XmlElement(name = "RelayOutputs", namespace = "http://www.onvif.org/ver10/schema")
    private int relayOutputs;

    public IOCapabilities() {}

    public int getInputConnectors() { return inputConnectors; }
    public void setInputConnectors(int inputConnectors) { this.inputConnectors = inputConnectors; }

    public int getRelayOutputs() { return relayOutputs; }
    public void setRelayOutputs(int relayOutputs) { this.relayOutputs = relayOutputs; }
}
