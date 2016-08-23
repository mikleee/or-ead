/**
 * 
 */
package uk.co.virtual1.salesforce.object;

import java.util.Date;

/**
 * @author nsaputro
 *
 */
public class Opportunity extends BaseSalesforceObject {

	private static final long serialVersionUID = 1L;

	private String opportunityNumber;   // Create a new opportunity,   Opportunity_Number__c

	private String opportunityOwner;    // Default the opportunity to the Account Owner on Salesforce, Owner

	private String accountId;         // Push through the Account Name using the salesforce customer ID from 1portal, Account


	private String opportunityName;     // CONCATENATE the End Customer Name - Postcode - Primary Access Bandwidth.
										// Constrain to first 120 characters.
										// Eg: "Gary Ltd - RG12 8UR - 100Mb"

	private String opportunityType;     // Default to "New Provide" on Sales Force, Type

	private String solutionType;        // Default to "Internet Access", Solution_Type__c

	private String opportunitySource;    // Default to "1portal", Opportunity_Source__c

	private String serviceTerm;          // (months),  Contract Term from the Primary Access Circuit, Service_Term_months__c

	private String numberOfSites;        // Default to "1", No_of_Sites__c

	private Boolean bdukOpportunity;      // If the Primary Access product name contains the Value "BDUK"  seto to true, otherwise leave blank, BDUK_Opportunity__c

	private Boolean pmRequired;           // Default to Flase, PM_Required__c

	private String numberPmDaysQuoted;   // Default to Blank, No_PM_days_Quoted__c

	private String numberPmAuthorised;   // Default to Blank, No_PM_Authorised__c

	private String relatedProject;       // SalesforceID of the Project created for this order, Related_Project__c

	private String Stage;                // Default to "Closed Won", StageName

	private String forecastCategory;     // Default to "Closed", ForecastCategoryName

	private Integer probability;          // (%),  Default to 100%, Probability

	private Date closeDate;            // Push through the Contract Signed Date, CloseDate

	private Double oneOfCharges;         // Total NRC, One_Off__c

	private Double annualRental;         // Total MRC x12, Annual_Rental__c

	private Double oneOffCost;         // OneOffCost__c

	private Double annualSupplierCost;   // AnnualSupplierCost__c

	private String x1PortalQuoteID;      // X1Portal_Quote_ID__c link to Quote Id


	//Carrier Forecast Information

	private Integer talkTalkEthernetForecast; 	  // The count of the products within the quote where the salesforce carrier forecast attribute is TalkTalk Ethernet, 	TalkTalk_Ethernet__c
	private Integer talkTalkEFMForecast;           // The count of the products within the quote where the salesforce carrier forecast attribute is TalkTalk EFM,     	TalkTalk_EFM__c
	private Integer virginEthernetForecast;        // The count of the products within the quote where the salesforce carrier forecast attribute is Virgin Ethernet, 	Virgin_Ethernet__c
	private Integer bTWEthernetForecast;           // The count of the products within the quote where the salesforce carrier forecast attribute is BTW Ethernet,    	BTW_Ethernet__c
	private Integer bTWEFMForecast;                // The count of the products within the quote where the salesforce carrier forecast attribute is BTW EFM,         	BTW_EFM__c
	private Integer bTWFTTCForecast;               // The count of the products within the quote where the salesforce carrier forecast attribute is BTW FTTC,        	BTW_FTTC__c
	private Integer coltEthernetForecast;		  // The count of the products within the quote where the salesforce carrier forecast attribute is Colt Ethernet,    	Colt_Ethernet__c
	private Integer vodafoneEthernetForecast;      // The count of the products within the quote where the salesforce carrier forecast attribute is Vodafone Ethernet, 	Vodafone_Ethernet__c
	private Integer gS2RacksForecast;			  // The count of the products within the quote where the salesforce carrier forecast attribute is GS2 Racks,       	GS2_Racks__c
	private Integer sSEEthernetForecast;			  // The count of the products within the quote where the salesforce carrier forecast attribute is SSE Ethernet,     	SSE_Ethernet__c
	private Integer euNetworksEthernetForecast;    // The count of the products within the quote where the salesforce carrier forecast attribute is EU Networks Ethernet,EU_Networks_Ethernet__c
	private Integer virtual1FibreForecast;         // The count of the products within the quote where the salesforce carrier forecast attribute is Virtual1 Fibre,   	Virtual1_Fibre__c
	private Integer virtual1FTTCForecast;          // The count of the products within the quote where the salesforce carrier forecast attribute is Virtual1 FTTC,   	Virtual1_FTTC__c
	private Integer otherCarrierForecast;          // The count of the products within the quote where the salesforce carrier forecast attribute is Other Carrier,   	Other_Carrier__c
	private Integer lhrRacksForecast;              // The count of the products within the quote where the salesforce carrier forecast attribute is LHR Racks,       	LHR_Racks__c
	private Integer u1OfFirewallHostingForecast;   // The count of the products within the quote where the salesforce carrier forecast attribute is 1u of Firewall Hosting,X1u_of_Firewall_Hosting__c


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getOpportunityNumber() {
		return opportunityNumber;
	}

	public void setOpportunityNumber(String opportunityNumber) {
		this.opportunityNumber = opportunityNumber;
	}

	public String getOpportunityOwner() {
		return opportunityOwner;
	}

	public void setOpportunityOwner(String opportunityOwner) {
		this.opportunityOwner = opportunityOwner;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getOpportunityName() {
		return opportunityName;
	}

	public void setOpportunityName(String opportunityName) {
		this.opportunityName = opportunityName;
	}

	public String getOpportunityType() {
		return opportunityType;
	}

	public void setOpportunityType(String opportunityType) {
		this.opportunityType = opportunityType;
	}

	public String getSolutionType() {
		return solutionType;
	}

	public void setSolutionType(String solutionType) {
		this.solutionType = solutionType;
	}

	public String getOpportunitySource() {
		return opportunitySource;
	}

	public void setOpportunitySource(String opportunitySource) {
		this.opportunitySource = opportunitySource;
	}

	public String getServiceTerm() {
		return serviceTerm;
	}

	public void setServiceTerm(String serviceTerm) {
		this.serviceTerm = serviceTerm;
	}

	public String getNumberOfSites() {
		return numberOfSites;
	}

	public void setNumberOfSites(String numberOfSites) {
		this.numberOfSites = numberOfSites;
	}

	public Boolean isBdukOpportunity() {
		return bdukOpportunity;
	}

	public void setBdukOpportunity(Boolean bdukOpportunity) {
		this.bdukOpportunity = bdukOpportunity;
	}

	public Boolean isPmRequired() {
		return pmRequired;
	}

	public void setPmRequired(Boolean pmRequired) {
		this.pmRequired = pmRequired;
	}

	public String getNumberPmDaysQuoted() {
		return numberPmDaysQuoted;
	}

	public void setNumberPmDaysQuoted(String numberPmDaysQuoted) {
		this.numberPmDaysQuoted = numberPmDaysQuoted;
	}

	public String getNumberPmAuthorised() {
		return numberPmAuthorised;
	}

	public void setNumberPmAuthorised(String numberPmAuthorised) {
		this.numberPmAuthorised = numberPmAuthorised;
	}

	public String getRelatedProject() {
		return relatedProject;
	}

	public void setRelatedProject(String relatedProject) {
		this.relatedProject = relatedProject;
	}

	public String getStage() {
		return Stage;
	}

	public void setStage(String stage) {
		Stage = stage;
	}

	public String getForecastCategory() {
		return forecastCategory;
	}

	public void setForecastCategory(String forecastCategory) {
		this.forecastCategory = forecastCategory;
	}

	public Integer getProbability() {
		return probability;
	}

	public void setProbability(Integer probability) {
		this.probability = probability;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public Double getOneOfCharges() {
		return oneOfCharges;
	}

	public void setOneOfCharges(Double oneOfCharges) {
		this.oneOfCharges = oneOfCharges;
	}

	public Double getAnnualRental() {
		return annualRental;
	}

	public void setAnnualRental(Double annualRental) {
		this.annualRental = annualRental;
	}

	public Double getOneOffCost() {
		return oneOffCost;
	}

	public void setOneOffCost(Double oneOffCost) {
		this.oneOffCost = oneOffCost;
	}

	public Double getAnnualSupplierCost() {
		return annualSupplierCost;
	}

	public void setAnnualSupplierCost(Double annualSupplierCost) {
		this.annualSupplierCost = annualSupplierCost;
	}

	public Integer getTalkTalkEthernetForecast() {
		return talkTalkEthernetForecast;
	}

	public void setTalkTalkEthernetForecast(Integer talkTalkEthernetForecast) {
		this.talkTalkEthernetForecast = talkTalkEthernetForecast;
	}

	public Integer getTalkTalkEFMForecast() {
		return talkTalkEFMForecast;
	}

	public void setTalkTalkEFMForecast(Integer talkTalkEFMForecast) {
		this.talkTalkEFMForecast = talkTalkEFMForecast;
	}

	public Integer getVirginEthernetForecast() {
		return virginEthernetForecast;
	}

	public void setVirginEthernetForecast(Integer virginEthernetForecast) {
		this.virginEthernetForecast = virginEthernetForecast;
	}

	public Integer getbTWEthernetForecast() {
		return bTWEthernetForecast;
	}

	public void setbTWEthernetForecast(Integer bTWEthernetForecast) {
		this.bTWEthernetForecast = bTWEthernetForecast;
	}

	public Integer getbTWEFMForecast() {
		return bTWEFMForecast;
	}

	public void setbTWEFMForecast(Integer bTWEFMForecast) {
		this.bTWEFMForecast = bTWEFMForecast;
	}

	public Integer getbTWFTTCForecast() {
		return bTWFTTCForecast;
	}

	public void setbTWFTTCForecast(Integer bTWFTTCForecast) {
		this.bTWFTTCForecast = bTWFTTCForecast;
	}

	public Integer getColtEthernetForecast() {
		return coltEthernetForecast;
	}

	public void setColtEthernetForecast(Integer coltEthernetForecast) {
		this.coltEthernetForecast = coltEthernetForecast;
	}

	public Integer getVodafoneEthernetForecast() {
		return vodafoneEthernetForecast;
	}

	public void setVodafoneEthernetForecast(Integer vodafoneEthernetForecast) {
		this.vodafoneEthernetForecast = vodafoneEthernetForecast;
	}

	public Integer getgS2RacksForecast() {
		return gS2RacksForecast;
	}

	public void setgS2RacksForecast(Integer gS2RacksForecast) {
		this.gS2RacksForecast = gS2RacksForecast;
	}

	public Integer getsSEEthernetForecast() {
		return sSEEthernetForecast;
	}

	public void setsSEEthernetForecast(Integer sSEEthernetForecast) {
		this.sSEEthernetForecast = sSEEthernetForecast;
	}

	public Integer getEuNetworksEthernetForecast() {
		return euNetworksEthernetForecast;
	}

	public void setEuNetworksEthernetForecast(Integer euNetworksEthernetForecast) {
		this.euNetworksEthernetForecast = euNetworksEthernetForecast;
	}

	public Integer getVirtual1FibreForecast() {
		return virtual1FibreForecast;
	}

	public void setVirtual1FibreForecast(Integer virtual1FibreForecast) {
		this.virtual1FibreForecast = virtual1FibreForecast;
	}

	public Integer getVirtual1FTTCForecast() {
		return virtual1FTTCForecast;
	}

	public void setVirtual1FTTCForecast(Integer virtual1FTTCForecast) {
		this.virtual1FTTCForecast = virtual1FTTCForecast;
	}

	public Integer getOtherCarrierForecast() {
		return otherCarrierForecast;
	}

	public void setOtherCarrierForecast(Integer otherCarrierForecast) {
		this.otherCarrierForecast = otherCarrierForecast;
	}

	public Integer getLhrRacksForecast() {
		return lhrRacksForecast;
	}

	public void setLhrRacksForecast(Integer lhrRacksForecast) {
		this.lhrRacksForecast = lhrRacksForecast;
	}

	public Integer getU1OfFirewallHostingForecast() {
		return u1OfFirewallHostingForecast;
	}

	public void setU1OfFirewallHostingForecast(Integer u1OfFirewallHostingForecast) {
		this.u1OfFirewallHostingForecast = u1OfFirewallHostingForecast;
	}

	public String getX1PortalQuoteID() {
		return x1PortalQuoteID;
	}

	public void setX1PortalQuoteID(String x1PortalQuoteID) {
		this.x1PortalQuoteID = x1PortalQuoteID;
	}
}
