package com.onvif.server.model.device;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SecurityCapabilities", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"tls12", "tls13", "onboardKeyGeneration", "accessPolicyConfig",
                      "x509Token", "samlToken", "kerberosToken", "usernameToken",
                      "httpDigest", "reLinkToken"})
public class SecurityCapabilities {

    @XmlElement(name = "TLS1.2", namespace = "http://www.onvif.org/ver10/schema")
    private boolean tls12;

    @XmlElement(name = "TLS1.3", namespace = "http://www.onvif.org/ver10/schema")
    private boolean tls13;

    @XmlElement(name = "OnboardKeyGeneration", namespace = "http://www.onvif.org/ver10/schema")
    private boolean onboardKeyGeneration;

    @XmlElement(name = "AccessPolicyConfig", namespace = "http://www.onvif.org/ver10/schema")
    private boolean accessPolicyConfig;

    @XmlElement(name = "X.509Token", namespace = "http://www.onvif.org/ver10/schema")
    private boolean x509Token;

    @XmlElement(name = "SAMLToken", namespace = "http://www.onvif.org/ver10/schema")
    private boolean samlToken;

    @XmlElement(name = "KerberosToken", namespace = "http://www.onvif.org/ver10/schema")
    private boolean kerberosToken;

    @XmlElement(name = "UsernameToken", namespace = "http://www.onvif.org/ver10/schema")
    private boolean usernameToken;

    @XmlElement(name = "HttpDigest", namespace = "http://www.onvif.org/ver10/schema")
    private boolean httpDigest;

    @XmlElement(name = "RELToken", namespace = "http://www.onvif.org/ver10/schema")
    private boolean reLinkToken;

    public SecurityCapabilities() {}

    public boolean isTls12() { return tls12; }
    public void setTls12(boolean v) { this.tls12 = v; }
    public boolean isTls13() { return tls13; }
    public void setTls13(boolean v) { this.tls13 = v; }
    public boolean isOnboardKeyGeneration() { return onboardKeyGeneration; }
    public void setOnboardKeyGeneration(boolean v) { this.onboardKeyGeneration = v; }
    public boolean isAccessPolicyConfig() { return accessPolicyConfig; }
    public void setAccessPolicyConfig(boolean v) { this.accessPolicyConfig = v; }
    public boolean isX509Token() { return x509Token; }
    public void setX509Token(boolean v) { this.x509Token = v; }
    public boolean isSamlToken() { return samlToken; }
    public void setSamlToken(boolean v) { this.samlToken = v; }
    public boolean isKerberosToken() { return kerberosToken; }
    public void setKerberosToken(boolean v) { this.kerberosToken = v; }
    public boolean isUsernameToken() { return usernameToken; }
    public void setUsernameToken(boolean v) { this.usernameToken = v; }
    public boolean isHttpDigest() { return httpDigest; }
    public void setHttpDigest(boolean v) { this.httpDigest = v; }
    public boolean isReLinkToken() { return reLinkToken; }
    public void setReLinkToken(boolean v) { this.reLinkToken = v; }
}
