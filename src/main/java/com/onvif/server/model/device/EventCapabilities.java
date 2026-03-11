package com.onvif.server.model.device;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EventCapabilities", namespace = "http://www.onvif.org/ver10/schema",
         propOrder = {"xAddr", "wsSubscriptionPolicySupport", "wsPullPointSupport",
                      "wsPausableSubscriptionManagerInterfaceSupport"})
public class EventCapabilities {

    @XmlElement(name = "XAddr", namespace = "http://www.onvif.org/ver10/schema")
    private String xAddr;

    @XmlElement(name = "WSSubscriptionPolicySupport", namespace = "http://www.onvif.org/ver10/schema")
    private boolean wsSubscriptionPolicySupport;

    @XmlElement(name = "WSPullPointSupport", namespace = "http://www.onvif.org/ver10/schema")
    private boolean wsPullPointSupport;

    @XmlElement(name = "WSPausableSubscriptionManagerInterfaceSupport",
                namespace = "http://www.onvif.org/ver10/schema")
    private boolean wsPausableSubscriptionManagerInterfaceSupport;

    public EventCapabilities() {}

    public EventCapabilities(String xAddr, boolean pullPointSupport) {
        this.xAddr = xAddr;
        this.wsSubscriptionPolicySupport = true;
        this.wsPullPointSupport = pullPointSupport;
        this.wsPausableSubscriptionManagerInterfaceSupport = false;
    }

    public String getXAddr() { return xAddr; }
    public void setXAddr(String xAddr) { this.xAddr = xAddr; }

    public boolean isWsSubscriptionPolicySupport() { return wsSubscriptionPolicySupport; }
    public void setWsSubscriptionPolicySupport(boolean v) { this.wsSubscriptionPolicySupport = v; }

    public boolean isWsPullPointSupport() { return wsPullPointSupport; }
    public void setWsPullPointSupport(boolean v) { this.wsPullPointSupport = v; }

    public boolean isWsPausableSubscriptionManagerInterfaceSupport() {
        return wsPausableSubscriptionManagerInterfaceSupport;
    }
    public void setWsPausableSubscriptionManagerInterfaceSupport(boolean v) {
        this.wsPausableSubscriptionManagerInterfaceSupport = v;
    }
}
