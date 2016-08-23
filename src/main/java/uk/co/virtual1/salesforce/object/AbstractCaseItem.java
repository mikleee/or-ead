package uk.co.virtual1.salesforce.object;

import uk.co.virtual1.salesforce.cache.SalesforceCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mikhail Tkachenko created on 03.08.16 15:56
 */
abstract class AbstractCaseItem extends BaseSalesforceObject {

    @SalesforceCollection(type = PricingEntry.class)
    private List<PricingEntry> pricingEntryList = new ArrayList<>();

    protected abstract Site getSite();

    public String getSiteId() {
        return getSite() == null ? null : getSite().getId();
    }

    public String getSiteName() {
        return getSite() == null ? null : getSite().getName();
    }

    public EndCustomer getEndCustomer() {
        return getSite() == null ? null : getSite().getEndCustomer();
    }

    public String getEndCustomerName() {
        EndCustomer endCustomer = getEndCustomer();
        return endCustomer == null ? null : endCustomer.getName();
    }

    public String getEndCustomerId() {
        EndCustomer endCustomer = getEndCustomer();
        return endCustomer == null ? null : endCustomer.getId();
    }


    public String getAccountId() {
        EndCustomer endCustomer = getEndCustomer();
        return endCustomer == null ? null : endCustomer.getAccountId();
    }

    public List<PricingEntry> getPricingEntryList() {
        return pricingEntryList;
    }

    public void setPricingEntryList(List<PricingEntry> pricingEntryList) {
        this.pricingEntryList = pricingEntryList;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return true;
        AbstractCaseItem that = (AbstractCaseItem) o;
        return getPricingEntryList() != null ? getPricingEntryList().equals(that.getPricingEntryList()) : that.getPricingEntryList() == null;
    }
}
