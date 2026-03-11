package com.onvif.server.model.ptz;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "PTZStatus", namespace = "http://www.onvif.org/ver10/schema")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PTZStatus", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"position", "moveStatus", "error", "utcTime"})
public class PTZStatus {

    @XmlElement(name = "Position", namespace = "http://www.onvif.org/ver10/schema")
    private PTZVector position;

    @XmlElement(name = "MoveStatus", namespace = "http://www.onvif.org/ver10/schema")
    private PTZMoveStatus moveStatus;

    @XmlElement(name = "Error", namespace = "http://www.onvif.org/ver10/schema")
    private String error;

    @XmlElement(name = "UtcTime", namespace = "http://www.onvif.org/ver10/schema")
    private String utcTime;

    public PTZStatus() {}

    public PTZVector getPosition() { return position; }
    public void setPosition(PTZVector position) { this.position = position; }

    public PTZMoveStatus getMoveStatus() { return moveStatus; }
    public void setMoveStatus(PTZMoveStatus moveStatus) { this.moveStatus = moveStatus; }

    public String getError() { return error; }
    public void setError(String error) { this.error = error; }

    public String getUtcTime() { return utcTime; }
    public void setUtcTime(String utcTime) { this.utcTime = utcTime; }
}
