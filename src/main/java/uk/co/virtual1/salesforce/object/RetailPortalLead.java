/**
 * 
 */
package uk.co.virtual1.salesforce.object;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nsaputro
 * SF object name: Retail_Portal_Leads__c
 */
public class RetailPortalLead extends BaseSalesforceObject {

	private static final long serialVersionUID = 1L;
	
	private String assignedPartner;// Assigned_Partner__c
	
	private String companyName;//Company_Name__c
	
	private String emailAddress;//Email_Address__c
	
	private String leadName; //Name__c
	
	private String position; //Position__c
	
	private String quoteType; //Quote_Type__c: Internet, MPLS
	
	private String telephoneNumber; //Telephone_Number__c
	
	private List<Quote> quotes = new ArrayList<Quote>();

	public String getAssignedPartner() {
		return assignedPartner;
	}

	public void setAssignedPartner(String assignedPartner) {
		this.assignedPartner = assignedPartner;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getLeadName() {
		return leadName;
	}

	public void setLeadName(String leadName) {
		this.leadName = leadName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getQuoteType() {
		return quoteType;
	}

	public void setQuoteType(String quoteType) {
		this.quoteType = quoteType;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	
	public List<Quote> getQuotes() {
		return quotes;
	}
}
