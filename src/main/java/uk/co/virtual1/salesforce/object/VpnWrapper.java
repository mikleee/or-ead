package uk.co.virtual1.salesforce.object;


import java.util.ArrayList;
import java.util.List;

public class VpnWrapper implements Cloneable {
    private String customer;
    private String vpnName;
    private String vpnType;
    private String vlanNumber;
    private String innerVlanNumber;
    private String radiusNumber;
    private String cir;
    private String accessId;
    private String siteName;
    private String accessType;
    private String relation;
    private String accessStatus;


    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getVpnName() {
        return vpnName;
    }

    public void setVpnName(String vpnName) {
        this.vpnName = vpnName;
    }

    public String getVpnType() {
        return vpnType;
    }

    public void setVpnType(String vpnType) {
        this.vpnType = vpnType;
    }

    public String getVlanNumber() {
        return vlanNumber;
    }

    public void setVlanNumber(String vlanNumber) {
        this.vlanNumber = vlanNumber;
    }

    public String getInnerVlanNumber() {
        return innerVlanNumber;
    }

    public void setInnerVlanNumber(String innerVlanNumber) {
        this.innerVlanNumber = innerVlanNumber;
    }

    public String getRadiusNumber() {
        return radiusNumber;
    }

    public void setRadiusNumber(String radiusNumber) {
        this.radiusNumber = radiusNumber;
    }

    public String getCir() {
        return cir;
    }

    public void setCir(String cir) {
        this.cir = cir;
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getAccessStatus() {
        return accessStatus;
    }

    public void setAccessStatus(String accessStatus) {
        this.accessStatus = accessStatus;
    }

    public static List<VpnWrapper> createVpnWrapper(List<VPN> vpns) throws CloneNotSupportedException {
        List<VpnWrapper> vpnWrappers = new ArrayList<VpnWrapper>();

        for (VPN vpn : vpns) {
            VpnWrapper wrapper = new VpnWrapper();
            wrapper.setCustomer(vpn.getEndCustomerName());
            wrapper.setVpnName(vpn.getName());
            wrapper.setVpnType(vpn.getVpnType());

            for (VPNItem vpnInner : vpn.getInnerVlans()) {
                VpnWrapper wr = (VpnWrapper) wrapper.clone();
                wr.setInnerVlanNumber(vpnInner.getName());
                wr.setCir(vpnInner.getCIR());
                wr.setAccessId(vpnInner.getAccessName());
                wr.setSiteName(vpnInner.getSiteName());
                wr.setAccessType(vpnInner.getAccessType());
                wr.setRelation(vpnInner.getRelation());
                wr.setAccessStatus(vpnInner.getAccessStatus());
                vpnWrappers.add(wr);
            }
            for (VPNItem radius : vpn.getRadiuses()) {
                VpnWrapper wr = (VpnWrapper) wrapper.clone();
                wr.setRadiusNumber(radius.getName());
                wr.setCir(radius.getCIR());
                wr.setAccessId(radius.getAccessName());
                wr.setSiteName(radius.getSiteName());
                wr.setAccessType(radius.getAccessType());
                wr.setRelation(radius.getRelation());
                wr.setAccessStatus(radius.getAccessStatus());
                vpnWrappers.add(wr);
            }
            for (VPNItem vlan : vpn.getVlans()) {
                VpnWrapper wr = (VpnWrapper) wrapper.clone();
                wr.setVlanNumber(vlan.getName());
                wr.setCir(vlan.getCIR());
                wr.setAccessId(vlan.getAccessName());
                wr.setSiteName(vlan.getSiteName());
                wr.setAccessType(vlan.getAccessType());
                wr.setRelation(vlan.getRelation());
                wr.setAccessStatus(vlan.getAccessStatus());
                vpnWrappers.add(wr);
            }
        }
        return vpnWrappers;
    }
}
