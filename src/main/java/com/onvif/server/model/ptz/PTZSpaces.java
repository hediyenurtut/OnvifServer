package com.onvif.server.model.ptz;

import jakarta.xml.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PTZSpaces", namespace = "http://www.onvif.org/ver10/schema")
public class PTZSpaces {

    @XmlElement(name = "AbsolutePanTiltPositionSpace", namespace = "http://www.onvif.org/ver10/schema")
    private List<Space2DDescription> absolutePanTiltPositionSpace = new ArrayList<>();

    @XmlElement(name = "AbsoluteZoomPositionSpace", namespace = "http://www.onvif.org/ver10/schema")
    private List<Space1DDescription> absoluteZoomPositionSpace = new ArrayList<>();

    @XmlElement(name = "RelativePanTiltTranslationSpace", namespace = "http://www.onvif.org/ver10/schema")
    private List<Space2DDescription> relativePanTiltTranslationSpace = new ArrayList<>();

    @XmlElement(name = "RelativeZoomTranslationSpace", namespace = "http://www.onvif.org/ver10/schema")
    private List<Space1DDescription> relativeZoomTranslationSpace = new ArrayList<>();

    @XmlElement(name = "ContinuousPanTiltVelocitySpace", namespace = "http://www.onvif.org/ver10/schema")
    private List<Space2DDescription> continuousPanTiltVelocitySpace = new ArrayList<>();

    @XmlElement(name = "ContinuousZoomVelocitySpace", namespace = "http://www.onvif.org/ver10/schema")
    private List<Space1DDescription> continuousZoomVelocitySpace = new ArrayList<>();

    public List<Space2DDescription> getAbsolutePanTiltPositionSpace() { return absolutePanTiltPositionSpace; }
    public void setAbsolutePanTiltPositionSpace(List<Space2DDescription> s) { this.absolutePanTiltPositionSpace = s; }

    public List<Space1DDescription> getAbsoluteZoomPositionSpace() { return absoluteZoomPositionSpace; }
    public void setAbsoluteZoomPositionSpace(List<Space1DDescription> s) { this.absoluteZoomPositionSpace = s; }

    public List<Space2DDescription> getRelativePanTiltTranslationSpace() { return relativePanTiltTranslationSpace; }
    public void setRelativePanTiltTranslationSpace(List<Space2DDescription> s) { this.relativePanTiltTranslationSpace = s; }

    public List<Space1DDescription> getRelativeZoomTranslationSpace() { return relativeZoomTranslationSpace; }
    public void setRelativeZoomTranslationSpace(List<Space1DDescription> s) { this.relativeZoomTranslationSpace = s; }

    public List<Space2DDescription> getContinuousPanTiltVelocitySpace() { return continuousPanTiltVelocitySpace; }
    public void setContinuousPanTiltVelocitySpace(List<Space2DDescription> s) { this.continuousPanTiltVelocitySpace = s; }

    public List<Space1DDescription> getContinuousZoomVelocitySpace() { return continuousZoomVelocitySpace; }
    public void setContinuousZoomVelocitySpace(List<Space1DDescription> s) { this.continuousZoomVelocitySpace = s; }
}
