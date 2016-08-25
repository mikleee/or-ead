/**
 * 
 */
package uk.co.virtual1.salesforce.object;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;

/**
 * @author nsaputro
 *
 */
public class Quote extends BaseSalesforceObject {
	
	private static final long serialVersionUID = 1L;

	private String carrierProvider; //Carrier_Provider__c
	
	private String postCode; //Post_code__c
	
	private String product; //Product__c
	
	private BigDecimal quotePrice; //Quote_Price__c

	public String getCarrierProvider() {
		return carrierProvider;
	}

	public void setCarrierProvider(String carrierProvider) {
		this.carrierProvider = carrierProvider;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = StringUtils.upperCase(postCode);
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public BigDecimal getQuotePrice() {
		return quotePrice;
	}

	public void setQuotePrice(BigDecimal quotePrice) {
		this.quotePrice = quotePrice;
	}
	
}
