package uk.co.virtual1.salesforce.object;

import uk.co.virtual1.salesforce.cache.SalesforceRelation;

import java.util.ArrayList;
import java.util.List;

public class VPN extends BaseSalesforceObject {
    private static final long serialVersionUID = 1L;
    public static final String RECORD_TYPE_VPN = "VPN";
    public static final String TYPE = "L2 CCC";

    private String vpnType;
    private String networkDiagram;
    private String routeDistinguisher;
    private String vpnDescription;

    @SalesforceRelation
    private EndCustomer endCustomer = new EndCustomer();

    private List<VPNItem> radiuses = new ArrayList<>();
    private List<VPNItem> vlans = new ArrayList<>();
    private List<VPNItem> innerVlans = new ArrayList<>();

    public String getEndCustomerId() {
        return endCustomer.getId();
    }

    public void setEndCustomerId(String id) {
        endCustomer.setId(id);
    }

    public String getEndCustomerName() {
        return endCustomer.getName();
    }

    public String getVpnType() {
        return vpnType;
    }

    public void setVpnType(String vpnType) {
        this.vpnType = vpnType;
    }

    public List<VPNItem> getRadiuses() {
        return radiuses;
    }

    public void setRadiuses(List<VPNItem> radiuses) {
        this.radiuses = radiuses;
    }

    public List<VPNItem> getVlans() {
        return vlans;
    }

    public void setVlans(List<VPNItem> vlans) {
        this.vlans = vlans;
    }

    public List<VPNItem> getInnerVlans() {
        return innerVlans;
    }

    public void setInnerVlans(List<VPNItem> innerVlans) {
        this.innerVlans = innerVlans;
    }

    public String getNetworkDiagram() {
        return networkDiagram;
    }

    public void setNetworkDiagram(String networkDiagram) {
        this.networkDiagram = networkDiagram;
    }

    public String getRouteDistinguisher() {
        return routeDistinguisher;
    }

    public void setRouteDistinguisher(String routeDistinguisher) {
        this.routeDistinguisher = routeDistinguisher;
    }

    public String getVpnDescription() {
        return vpnDescription;
    }

    public void setVpnDescription(String vpnDescription) {
        this.vpnDescription = vpnDescription;
    }

    public EndCustomer getEndCustomer() {
        return endCustomer;
    }

    public void setEndCustomer(EndCustomer endCustomer) {
        this.endCustomer = endCustomer;
    }
}
