package uk.co.virtual1.salesforce;

import com.sforce.soap.partner.fault.ApiFault;

public interface SalesforceExceptionCallback {

	void onError(ApiFault apiFault);
	
}
