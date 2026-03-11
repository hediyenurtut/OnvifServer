package com.onvif.server.endpoint;

import com.onvif.server.model.device.*;
import jakarta.jws.*;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.*;
import jakarta.xml.ws.BindingType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * ONVIF Device Management Service – Profile T zorunlu servisi.
 * SOAP 1.2 / Document-Literal-Wrapped bağlaması kullanır.
 *
 * Endpoint: /onvif/device_service
 * Namespace: http://www.onvif.org/ver10/device/wsdl
 */
@Component
@WebService(
    serviceName     = "DeviceService",
    portName        = "DevicePort",
    targetNamespace = "http://www.onvif.org/ver10/device/wsdl"
)
@SOAPBinding(
    style          = Style.DOCUMENT,
    use            = Use.LITERAL,
    parameterStyle = ParameterStyle.WRAPPED
)
@BindingType(jakarta.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class DeviceServiceEndpoint {

    @Value("${server.port:8080}")
    private int serverPort;

    @Value("${onvif.device.manufacturer:OnvifServer}")
    private String manufacturer;

    @Value("${onvif.device.model:ProfileT-1}")
    private String model;

    @Value("${onvif.device.firmware-version:1.0.0}")
    private String firmwareVersion;

    @Value("${onvif.device.serial-number:SN-000001}")
    private String serialNumber;

    @Value("${onvif.device.hardware-id:HW-1}")
    private String hardwareId;

    // ----------------------------------------------------------------
    // GetServiceCapabilities
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetServiceCapabilities")
    @WebResult(name = "Capabilities",
               targetNamespace = "http://www.onvif.org/ver10/device/wsdl")
    public String getServiceCapabilities() {
        return "Network IPFilter=false ZeroConfiguration=true IPVersion6=false " +
               "DynDNS=false Dot11Configuration=false HostnameFromDHCP=true " +
               "MaxSearchResults=100 DHCPv6=false;Security " +
               "TLS1.0=false TLS1.1=false TLS1.2=true OnboardKeyGeneration=false " +
               "AccessPolicyConfig=false DefaultAccessPolicy=false " +
               "Dot1X=false RemoteUserHandling=false X.509Token=false " +
               "SAMLToken=false KerberosToken=false UsernameToken=true " +
               "HttpDigest=false RELToken=false SupportedEAPMethods=0 " +
               "MaxUsers=10 MaxUserNameLength=64 MaxPasswordLength=64;System " +
               "DiscoveryResolve=true DiscoveryBye=true RemoteDiscovery=true " +
               "SystemBackup=false SystemLogging=false FirmwareUpgrade=false";
    }

    // ----------------------------------------------------------------
    // GetDeviceInformation
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetDeviceInformation")
    @WebResult(name = "GetDeviceInformationResponse",
               targetNamespace = "http://www.onvif.org/ver10/device/wsdl")
    public DeviceInformation getDeviceInformation() {
        return new DeviceInformation(manufacturer, model, firmwareVersion,
                                     serialNumber, hardwareId);
    }

    // ----------------------------------------------------------------
    // GetSystemDateAndTime
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetSystemDateAndTime")
    @WebResult(name = "SystemDateAndTime",
               targetNamespace = "http://www.onvif.org/ver10/device/wsdl")
    public SystemDateAndTime getSystemDateAndTime() {
        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        SystemDateAndTime sdt = new SystemDateAndTime();
        sdt.setDateTimeType("NTP");
        sdt.setDaylightSavings(false);
        sdt.setTimeZone(new TimeZoneType("UTC"));

        Time t = new Time(now.getHour(), now.getMinute(), now.getSecond());
        Date d = new Date(now.getYear(), now.getMonthValue(), now.getDayOfMonth());
        DateTime utc = new DateTime(t, d);
        sdt.setUtcDateTime(utc);
        sdt.setLocalDateTime(utc);
        return sdt;
    }

    // ----------------------------------------------------------------
    // GetCapabilities
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetCapabilities")
    @WebResult(name = "Capabilities",
               targetNamespace = "http://www.onvif.org/ver10/device/wsdl")
    public DeviceCapabilities getCapabilities(
            @WebParam(name = "Category",
                      targetNamespace = "http://www.onvif.org/ver10/device/wsdl")
            String category) {

        String base = "http://localhost:" + serverPort + "/onvif";

        DeviceCapabilities caps = new DeviceCapabilities();

        // Analytics
        AnalyticsCapabilities analytics = new AnalyticsCapabilities(
                base + "/analytics_service", true, true);
        caps.setAnalytics(analytics);

        // Device
        DeviceCapabilityInfo deviceInfo = new DeviceCapabilityInfo();
        deviceInfo.setXAddr(base + "/device_service");
        NetworkCapabilities net = new NetworkCapabilities();
        net.setZeroConfiguration(true);
        deviceInfo.setNetwork(net);
        SystemCapabilities sys = new SystemCapabilities();
        sys.setDiscoveryResolve(true);
        sys.setDiscoveryBye(true);
        sys.setRemoteDiscovery(true);
        deviceInfo.setSystem(sys);
        IOCapabilities io = new IOCapabilities();
        deviceInfo.setIo(io);
        SecurityCapabilities sec = new SecurityCapabilities();
        sec.setUsernameToken(true);
        sec.setTls12(true);
        deviceInfo.setSecurity(sec);
        caps.setDevice(deviceInfo);

        // Events
        caps.setEvents(new EventCapabilities(base + "/event_service", true));

        // Imaging
        caps.setImaging(new SimpleXAddr(base + "/imaging_service"));

        // Media (Media2 for Profile T)
        caps.setMedia(new MediaCapabilities(base + "/media2_service"));

        // PTZ
        caps.setPtz(new SimpleXAddr(base + "/ptz_service"));

        return caps;
    }

    // ----------------------------------------------------------------
    // GetHostname
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetHostname")
    @WebResult(name = "HostnameInformation",
               targetNamespace = "http://www.onvif.org/ver10/device/wsdl")
    public String getHostname() {
        try {
            return java.net.InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            return "onvif-server";
        }
    }

    // ----------------------------------------------------------------
    // GetNetworkInterfaces
    // ----------------------------------------------------------------

    @WebMethod(operationName = "GetNetworkInterfaces")
    @WebResult(name = "NetworkInterfaces",
               targetNamespace = "http://www.onvif.org/ver10/device/wsdl")
    public String getNetworkInterfaces() {
        return "token=eth0 Enabled=true Name=eth0 " +
               "IPv4.Enabled=true IPv4.DHCP=true IPv4.Address=192.168.1.100/24 " +
               "MTU=1500 HwAddress=00:00:00:00:00:01 LinkSpeed=1000";
    }

    // ----------------------------------------------------------------
    // SystemReboot
    // ----------------------------------------------------------------

    @WebMethod(operationName = "SystemReboot")
    @WebResult(name = "Message",
               targetNamespace = "http://www.onvif.org/ver10/device/wsdl")
    public String systemReboot() {
        System.out.println("[Device] SystemReboot requested");
        return "Rebooting";
    }
}
