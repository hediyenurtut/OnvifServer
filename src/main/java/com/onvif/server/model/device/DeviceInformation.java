package com.onvif.server.model.device;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeviceInformation", namespace = "http://www.onvif.org/ver10/device/wsdl",
         propOrder = {"manufacturer", "model", "firmwareVersion", "serialNumber", "hardwareId"})
public class DeviceInformation {

    @XmlElement(name = "Manufacturer", namespace = "http://www.onvif.org/ver10/device/wsdl")
    private String manufacturer;

    @XmlElement(name = "Model", namespace = "http://www.onvif.org/ver10/device/wsdl")
    private String model;

    @XmlElement(name = "FirmwareVersion", namespace = "http://www.onvif.org/ver10/device/wsdl")
    private String firmwareVersion;

    @XmlElement(name = "SerialNumber", namespace = "http://www.onvif.org/ver10/device/wsdl")
    private String serialNumber;

    @XmlElement(name = "HardwareId", namespace = "http://www.onvif.org/ver10/device/wsdl")
    private String hardwareId;

    public DeviceInformation() {}

    public DeviceInformation(String manufacturer, String model, String firmwareVersion,
                             String serialNumber, String hardwareId) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.firmwareVersion = firmwareVersion;
        this.serialNumber = serialNumber;
        this.hardwareId = hardwareId;
    }

    public String getManufacturer() { return manufacturer; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getFirmwareVersion() { return firmwareVersion; }
    public void setFirmwareVersion(String firmwareVersion) { this.firmwareVersion = firmwareVersion; }

    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }

    public String getHardwareId() { return hardwareId; }
    public void setHardwareId(String hardwareId) { this.hardwareId = hardwareId; }
}
