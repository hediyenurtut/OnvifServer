package com.onvif.server.model.ptz;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Space1DDescription", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"uri", "xRange"})
public class Space1DDescription {

    @XmlElement(name = "URI", namespace = "http://www.onvif.org/ver10/schema")
    private String uri;

    @XmlElement(name = "XRange", namespace = "http://www.onvif.org/ver10/schema")
    private FloatRange xRange;

    public Space1DDescription() {}

    public Space1DDescription(String uri, float xMin, float xMax) {
        this.uri = uri;
        this.xRange = new FloatRange(xMin, xMax);
    }

    public String getUri() { return uri; }
    public void setUri(String uri) { this.uri = uri; }

    public FloatRange getXRange() { return xRange; }
    public void setXRange(FloatRange xRange) { this.xRange = xRange; }
}
