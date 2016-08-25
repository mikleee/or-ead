package uk.co.virtual1.salesforce.object;

import org.apache.commons.lang.StringUtils;

public class Contact extends BaseSalesforceObject {
    private static final long serialVersionUID = 1L;

    private String accountId;
    private String telephone;
    private String email;
    private String firstName;
    private String lastName;
    private String jobTitle; //Title
    private String roles;

	private Boolean x1PortalUSer = true; //X1Portal_User__c
	private Boolean hasOptedOutOfEmail = false; //HasOptedOutOfEmail
	private Boolean doNotCall = false; //DoNotCall
	private String department = StringUtils.EMPTY; //Department

    /**
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Boolean getX1PortalUSer() {
		return x1PortalUSer;
	}

	public void setX1PortalUSer(Boolean x1PortalUSer) {
		this.x1PortalUSer = x1PortalUSer;
	}

	public Boolean getHasOptedOutOfEmail() {
		return hasOptedOutOfEmail;
	}

	public void setHasOptedOutOfEmail(Boolean hasOptedOutOfEmail) {
		this.hasOptedOutOfEmail = hasOptedOutOfEmail;
	}

	public Boolean getDoNotCall() {
		return doNotCall;
	}

	public void setDoNotCall(Boolean doNotCall) {
		this.doNotCall = doNotCall;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	
}
