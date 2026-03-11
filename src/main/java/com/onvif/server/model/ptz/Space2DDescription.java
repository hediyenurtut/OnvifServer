package com.onvif.server.model.ptz;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Space2DDescription", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"uri", "xRange", "yRange"})
public class Space2DDescription {

    @XmlElement(name = "URI", namespace = "http://www.onvif.org/ver10/schema")
    private String uri;

    @XmlElement(name = "XRange", namespace = "http://www.onvif.org/ver10/schema")
    private FloatRange xRange;

    @XmlElement(name = "YRange", namespace = "http://www.onvif.org/ver10/schema")
    private FloatRange yRange;

    public Space2DDescription() {}

    public Space2DDescription(String uri, float xMin, float xMax, float yMin, float yMax) {
        this.uri = uri;
        this.xRange = new FloatRange(xMin, xMax);
        this.yRange = new FloatRange(yMin, yMax);
    }

    public String getUri() { return uri; }
    public void setUri(String uri) { this.uri = uri; }

    public FloatRange getXRange() { return xRange; }
    public void setXRange(FloatRange xRange) { this.xRange = xRange; }

    public FloatRange getYRange() { return yRange; }
    public void setYRange(FloatRange yRange) { this.yRange = yRange; }
}
