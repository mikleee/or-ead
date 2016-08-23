package uk.co.virtual1.dialog;

import uk.co.virtual1.salesforce.object.Case;

/**
 * @author Mikhail Tkachenko created on 17.05.2016
 */
public interface ProvisionDialog {

    void provisionFromCase(Case sfCase);

}
