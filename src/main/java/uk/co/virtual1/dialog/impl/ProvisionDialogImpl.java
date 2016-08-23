package uk.co.virtual1.dialog.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.virtual1.dialog.ProvisionDialog;
import uk.co.virtual1.factory.EadProvisionMessageFactory;
import uk.co.virtual1.salesforce.object.Case;

/**
 * @author Mikhail Tkachenko created on 17.05.2016
 */
@Service
public class ProvisionDialogImpl implements ProvisionDialog {
    @Autowired
    private EadProvisionMessageFactory eadProvisionMessageFactory;

    @Override
    public void provisionFromCase(Case sfCase) {
        String xml = eadProvisionMessageFactory.createMessage(sfCase);
        System.out.println(xml);
    }

}
