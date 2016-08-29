package uk.co.virtual1.dialog;

import uk.co.virtual1.exception.ProvisioningException;
import uk.co.virtual1.salesforcebox.object.Case;

/**
 * @author Mikhail Tkachenko created on 17.05.2016
 */
public interface ProvisionDialog {

    String provisionFromCase(Case sfCase) throws ProvisioningException;

}
