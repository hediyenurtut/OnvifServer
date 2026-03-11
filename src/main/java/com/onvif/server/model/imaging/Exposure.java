package com.onvif.server.model.imaging;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Exposure20", namespace = "http://www.onvif.org/ver20/imaging/wsdl",
         propOrder = {"mode", "priority", "minExposureTime", "maxExposureTime",
                      "minGain", "maxGain", "minIris", "maxIris",
                      "exposureTime", "gain", "iris"})
public class Exposure {

    @XmlElement(name = "Mode", namespace = "http://www.onvif.org/ver20/imaging/wsdl")
    private String mode; // AUTO | MANUAL

    @XmlElement(name = "Priority", namespace = "http://www.onvif.org/ver20/imaging/wsdl")
    private String priority; // LowNoise | FrameRate

    @XmlElement(name = "MinExposureTime", namespace = "http://www.onvif.org/ver20/imaging/wsdl")
    private Float minExposureTime;

    @XmlElement(name = "MaxExposureTime", namespace = "http://www.onvif.org/ver20/imaging/wsdl")
    private Float maxExposureTime;

    @XmlElement(name = "MinGain", namespace = "http://www.onvif.org/ver20/imaging/wsdl")
    private Float minGain;

    @XmlElement(name = "MaxGain", namespace = "http://www.onvif.org/ver20/imaging/wsdl")
    private Float maxGain;

    @XmlElement(name = "MinIris", namespace = "http://www.onvif.org/ver20/imaging/wsdl")
    private Float minIris;

    @XmlElement(name = "MaxIris", namespace = "http://www.onvif.org/ver20/imaging/wsdl")
    private Float maxIris;

    @XmlElement(name = "ExposureTime", namespace = "http://www.onvif.org/ver20/imaging/wsdl")
    private Float exposureTime;

    @XmlElement(name = "Gain", namespace = "http://www.onvif.org/ver20/imaging/wsdl")
    private Float gain;

    @XmlElement(name = "Iris", namespace = "http://www.onvif.org/ver20/imaging/wsdl")
    private Float iris;

    public Exposure() {}

    public String getMode() { return mode; }
    public void setMode(String mode) { this.mode = mode; }
    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
    public Float getMinExposureTime() { return minExposureTime; }
    public void setMinExposureTime(Float v) { this.minExposureTime = v; }
    public Float getMaxExposureTime() { return maxExposureTime; }
    public void setMaxExposureTime(Float v) { this.maxExposureTime = v; }
    public Float getMinGain() { return minGain; }
    public void setMinGain(Float v) { this.minGain = v; }
    public Float getMaxGain() { return maxGain; }
    public void setMaxGain(Float v) { this.maxGain = v; }
    public Float getMinIris() { return minIris; }
    public void setMinIris(Float v) { this.minIris = v; }
    public Float getMaxIris() { return maxIris; }
    public void setMaxIris(Float v) { this.maxIris = v; }
    public Float getExposureTime() { return exposureTime; }
    public void setExposureTime(Float v) { this.exposureTime = v; }
    public Float getGain() { return gain; }
    public void setGain(Float v) { this.gain = v; }
    public Float getIris() { return iris; }
    public void setIris(Float v) { this.iris = v; }
}
