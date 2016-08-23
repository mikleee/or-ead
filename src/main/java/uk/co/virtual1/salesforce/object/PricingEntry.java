package uk.co.virtual1.salesforce.object;

import uk.co.virtual1.salesforce.cache.SalesforceParentId;

import java.math.BigDecimal;

public class PricingEntry extends BaseSalesforceObject {
    private static final long serialVersionUID = 1L;

    private String projectId;//Project_Number1__c
    private String caseId;//Case_Number__c
    private String accountId;//Account_Name__c
    private String chargeTypeId;//Charge_Type__c
	private String chargeTypeName; //Charge_Type__r.Name
    private BigDecimal amount;//Amount__c
    private String billingFrequency;//Billing_Frequency__c: One Off, Quarterly, Annually
    private String partnerPONumber; //Partner_PO_No__c
    private BigDecimal supplierCost; //Supplier_Cost__c
    private String supplierQuoteRef; //Supplier_Quote_Ref__c
    private BigDecimal BTWETHACost; //BTW_ETHA_Cost__c
    private BigDecimal BTWETHCCost; //BTW_ETHC_Cost__c
	private BigDecimal margin; //Margin__c
	private BigDecimal previousRentalAmount; //Previous_Rental_Amount__c
	@SalesforceParentId
    private String accessId;//Access_ID__c
	@SalesforceParentId
    private String assetId;//Asset_ID__c
	@SalesforceParentId
    private String componentId;//Component_ID__c
    private String isAVirtual1BDUKVoucherApplicable; // Is_a_Virtual1_BDUK_voucher_applicable__c (picklist)
	private String billingStartDate; //Billing_Start_Date__c
	private String billingEndDate; //Billing_End_Date__c
    private String bdukVoucherReference; // BDUK_Voucher_Reference__c

	public PricingEntry() {
	}

    public PricingEntry(String recordType) {
    	this.setRecordType(recordType);
    }
    
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccessId() {
		return accessId;
	}

	public void setAccessId(String accessId) {
		this.accessId = accessId;
	}

	public String getChargeTypeId() {
		return chargeTypeId;
	}

	public void setChargeTypeId(String chargeTypeId) {
		this.chargeTypeId = chargeTypeId;
	}

	public String getChargeTypeName() {
		return chargeTypeName;
	}

	public void setChargeTypeName(String chargeTypeName) {
		this.chargeTypeName = chargeTypeName;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getBillingFrequency() {
		return billingFrequency;
	}

	public void setBillingFrequency(String billingFrequency) {
		this.billingFrequency = billingFrequency;
	}

	public String getPartnerPONumber() {
		return partnerPONumber;
	}

	public void setPartnerPONumber(String partnerPONumber) {
		this.partnerPONumber = partnerPONumber;
	}

	public BigDecimal getSupplierCost() {
		return supplierCost;
	}

	public void setSupplierCost(BigDecimal supplierCost) {
		this.supplierCost = supplierCost;
	}

	public String getSupplierQuoteRef() {
		return supplierQuoteRef;
	}

	public void setSupplierQuoteRef(String supplierQuoteRef) {
		this.supplierQuoteRef = supplierQuoteRef;
	}

	public BigDecimal getBTWETHACost() {
		return BTWETHACost;
	}

	public void setBTWETHACost(BigDecimal bTWETHACost) {
		BTWETHACost = bTWETHACost;
	}

	public BigDecimal getBTWETHCCost() {
		return BTWETHCCost;
	}

	public void setBTWETHCCost(BigDecimal bTWETHCCost) {
		BTWETHCCost = bTWETHCCost;
	}

	public BigDecimal getMargin() {
		return margin;
	}

	public void setMargin(BigDecimal margin) {
		this.margin = margin;
	}

	public BigDecimal getPreviousRentalAmount() {
		return previousRentalAmount;
	}

	public void setPreviousRentalAmount(BigDecimal previousRentalAmount) {
		this.previousRentalAmount = previousRentalAmount;
	}

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public String getComponentId() {
		return componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

    public String getIsAVirtual1BDUKVoucherApplicable() {
        return isAVirtual1BDUKVoucherApplicable;
    }

    public void setIsAVirtual1BDUKVoucherApplicable( String isAVirtual1BDUKVoucherApplicable) {
        this.isAVirtual1BDUKVoucherApplicable = isAVirtual1BDUKVoucherApplicable;
    }

    public String getBdukVoucherReference() {
        return bdukVoucherReference;
    }

    public void setBdukVoucherReference(String bdukVoucherReference) {
        this.bdukVoucherReference = bdukVoucherReference;
    }

    public String getBillingStartDate() {
		return billingStartDate;
	}

	public void setBillingStartDate(String billingStartDate) {
		this.billingStartDate = billingStartDate;
	}

	public String getBillingEndDate() {
		return billingEndDate;
	}

	public void setBillingEndDate(String billingEndDate) {
		this.billingEndDate = billingEndDate;
	}

}
