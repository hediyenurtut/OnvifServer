package com.onvif.server.model.imaging;

import jakarta.xml.bind.annotation.*;

/**
 * ONVIF Imaging Settings – brightness, contrast, saturation, sharpness, etc.
 */
@XmlRootElement(name = "ImagingSettings", namespace = "http://www.onvif.org/ver20/imaging/wsdl")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ImagingSettings20", namespace = "http://www.onvif.org/ver20/imaging/wsdl",
         propOrder = {"brightness", "colorSaturation", "contrast", "sharpness",
                      "wideDynamicRange", "whiteBalance", "exposure", "focus"})
public class ImagingSettings {

    @XmlElement(name = "Brightness", namespace = "http://www.onvif.org/ver20/imaging/wsdl")
    private Float brightness;

    @XmlElement(name = "ColorSaturation", namespace = "http://www.onvif.org/ver20/imaging/wsdl")
    private Float colorSaturation;

    @XmlElement(name = "Contrast", namespace = "http://www.onvif.org/ver20/imaging/wsdl")
    private Float contrast;

    @XmlElement(name = "Sharpness", namespace = "http://www.onvif.org/ver20/imaging/wsdl")
    private Float sharpness;

    @XmlElement(name = "WideDynamicRange",
                namespace = "http://www.onvif.org/ver20/imaging/wsdl")
    private WideDynamicRange wideDynamicRange;

    @XmlElement(name = "WhiteBalance", namespace = "http://www.onvif.org/ver20/imaging/wsdl")
    private WhiteBalance whiteBalance;

    @XmlElement(name = "Exposure", namespace = "http://www.onvif.org/ver20/imaging/wsdl")
    private Exposure exposure;

    @XmlElement(name = "Focus", namespace = "http://www.onvif.org/ver20/imaging/wsdl")
    private FocusConfiguration focus;

    public ImagingSettings() {}

    public Float getBrightness() { return brightness; }
    public void setBrightness(Float brightness) { this.brightness = brightness; }

    public Float getColorSaturation() { return colorSaturation; }
    public void setColorSaturation(Float colorSaturation) { this.colorSaturation = colorSaturation; }

    public Float getContrast() { return contrast; }
    public void setContrast(Float contrast) { this.contrast = contrast; }

    public Float getSharpness() { return sharpness; }
    public void setSharpness(Float sharpness) { this.sharpness = sharpness; }

    public WideDynamicRange getWideDynamicRange() { return wideDynamicRange; }
    public void setWideDynamicRange(WideDynamicRange wideDynamicRange) { this.wideDynamicRange = wideDynamicRange; }

    public WhiteBalance getWhiteBalance() { return whiteBalance; }
    public void setWhiteBalance(WhiteBalance whiteBalance) { this.whiteBalance = whiteBalance; }

    public Exposure getExposure() { return exposure; }
    public void setExposure(Exposure exposure) { this.exposure = exposure; }

    public FocusConfiguration getFocus() { return focus; }
    public void setFocus(FocusConfiguration focus) { this.focus = focus; }
}
