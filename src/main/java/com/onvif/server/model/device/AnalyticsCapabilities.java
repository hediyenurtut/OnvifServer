package com.onvif.server.model.device;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AnalyticsCapabilities", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"xAddr", "ruleSupport", "analyticsModuleSupport"})
public class AnalyticsCapabilities {

    @XmlElement(name = "XAddr", namespace = "http://www.onvif.org/ver10/schema")
    private String xAddr;

    @XmlElement(name = "RuleSupport", namespace = "http://www.onvif.org/ver10/schema")
    private boolean ruleSupport;

    @XmlElement(name = "AnalyticsModuleSupport", namespace = "http://www.onvif.org/ver10/schema")
    private boolean analyticsModuleSupport;

    public AnalyticsCapabilities() {}

    public AnalyticsCapabilities(String xAddr, boolean ruleSupport, boolean analyticsModuleSupport) {
        this.xAddr = xAddr;
        this.ruleSupport = ruleSupport;
        this.analyticsModuleSupport = analyticsModuleSupport;
    }

    public String getXAddr() { return xAddr; }
    public void setXAddr(String xAddr) { this.xAddr = xAddr; }

    public boolean isRuleSupport() { return ruleSupport; }
    public void setRuleSupport(boolean ruleSupport) { this.ruleSupport = ruleSupport; }

    public boolean isAnalyticsModuleSupport() { return analyticsModuleSupport; }
    public void setAnalyticsModuleSupport(boolean analyticsModuleSupport) { this.analyticsModuleSupport = analyticsModuleSupport; }
}
