package uk.co.virtual1.model.xml.out;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Mikhail Tkachenko created on 22.08.16 13:34
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class EADProvision extends AbstractProvision {
    @XmlAttribute(name = "A13AmpDbleScktWithin1Mtr")
    private String a13AmpDbleScktWithin1Mtr;
    @XmlAttribute(name = "Housing")
    private String housing;
    @XmlAttribute(name = "SiteType")
    private String siteType;
    @XmlAttribute(name = "Telephone3m")
    private String telephone3m;
    @XmlAttribute(name = "ThirdPartyAccess")
    private String thirdPartyAccess;
    @XmlAttribute(name = "CctNoOfFibreService")
    private String cctNoOfFibreService;
    @XmlAttribute(name = "CircuitInterfaceType")
    private String circuitInterfaceType;
    @XmlAttribute(name = "ExistingFibreServiceatSite")

    private String existingFibreServiceatSite;
    @XmlAttribute(name = "FibreServiceSameLocation")
    private String fibreServiceSameLocation;
    @XmlAttribute(name = "FloorLoc")
    private String floorLoc;
    @XmlAttribute(name = "RackLoc")
    private String rackLoc;
    @XmlAttribute(name = "RoomLoc")
    private String roomLoc;
    @XmlAttribute(name = "SlotLoc")
    private String slotLoc;
    @XmlAttribute(name = "SuiteLoc")
    private String suiteLoc;
    @XmlAttribute(name = "CommsRoomReady")
    private String commsRoomReady;
    @XmlAttribute(name = "LandlordConsentGranted")
    private String landlordConsentGranted;
    @XmlAttribute(name = "LandLordConsent")
    private String landLordConsent;
    @XmlAttribute(name = "NTEChassisOptionReqdAEnd")
    private String nteChassisOptionReqdAEnd;
    @XmlAttribute(name = "PowerSupplyRequired")
    private String powerSupplyRequired;


    public String getA13AmpDbleScktWithin1Mtr() {
        return a13AmpDbleScktWithin1Mtr;
    }

    public void setA13AmpDbleScktWithin1Mtr(String a13AmpDbleScktWithin1Mtr) {
        this.a13AmpDbleScktWithin1Mtr = a13AmpDbleScktWithin1Mtr;
    }

    public String getHousing() {
        return housing;
    }

    public void setHousing(String housing) {
        this.housing = housing;
    }

    public String getCctNoOfFibreService() {
        return cctNoOfFibreService;
    }

    public void setCctNoOfFibreService(String cctNoOfFibreService) {
        this.cctNoOfFibreService = cctNoOfFibreService;
    }

    public String getCircuitInterfaceType() {
        return circuitInterfaceType;
    }

    public void setCircuitInterfaceType(String circuitInterfaceType) {
        this.circuitInterfaceType = circuitInterfaceType;
    }

    public String getCommsRoomReady() {
        return commsRoomReady;
    }

    public void setCommsRoomReady(String commsRoomReady) {
        this.commsRoomReady = commsRoomReady;
    }

    public String getExistingFibreServiceatSite() {
        return existingFibreServiceatSite;
    }

    public void setExistingFibreServiceatSite(String existingFibreServiceatSite) {
        this.existingFibreServiceatSite = existingFibreServiceatSite;
    }

    public String getFibreServiceSameLocation() {
        return fibreServiceSameLocation;
    }

    public void setFibreServiceSameLocation(String fibreServiceSameLocation) {
        this.fibreServiceSameLocation = fibreServiceSameLocation;
    }

    public String getFloorLoc() {
        return floorLoc;
    }

    public void setFloorLoc(String floorLoc) {
        this.floorLoc = floorLoc;
    }

    public String getLandLordConsent() {
        return landLordConsent;
    }

    public void setLandLordConsent(String landLordConsent) {
        this.landLordConsent = landLordConsent;
    }

    public String getLandlordConsentGranted() {
        return landlordConsentGranted;
    }

    public void setLandlordConsentGranted(String landlordConsentGranted) {
        this.landlordConsentGranted = landlordConsentGranted;
    }

    public String getNteChassisOptionReqdAEnd() {
        return nteChassisOptionReqdAEnd;
    }

    public void setNteChassisOptionReqdAEnd(String nteChassisOptionReqdAEnd) {
        this.nteChassisOptionReqdAEnd = nteChassisOptionReqdAEnd;
    }

    public String getRackLoc() {
        return rackLoc;
    }

    public void setRackLoc(String rackLoc) {
        this.rackLoc = rackLoc;
    }

    public String getRoomLoc() {
        return roomLoc;
    }

    public void setRoomLoc(String roomLoc) {
        this.roomLoc = roomLoc;
    }

    public String getSiteType() {
        return siteType;
    }

    public void setSiteType(String siteType) {
        this.siteType = siteType;
    }

    public String getSlotLoc() {
        return slotLoc;
    }

    public void setSlotLoc(String slotLoc) {
        this.slotLoc = slotLoc;
    }

    public String getSuiteLoc() {
        return suiteLoc;
    }

    public void setSuiteLoc(String suiteLoc) {
        this.suiteLoc = suiteLoc;
    }

    public String getTelephone3m() {
        return telephone3m;
    }

    public void setTelephone3m(String telephone3m) {
        this.telephone3m = telephone3m;
    }

    public String getThirdPartyAccess() {
        return thirdPartyAccess;
    }

    public void setThirdPartyAccess(String thirdPartyAccess) {
        this.thirdPartyAccess = thirdPartyAccess;
    }

    public String getPowerSupplyRequired() {
        return powerSupplyRequired;
    }

    public void setPowerSupplyRequired(String powerSupplyRequired) {
        this.powerSupplyRequired = powerSupplyRequired;
    }
}
