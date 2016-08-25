package uk.co.virtual1.factory;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import uk.co.virtual1.exception.ProvisioningException;
import uk.co.virtual1.model.xml.out.BritishAddress;
import uk.co.virtual1.model.xml.out.Constants;
import uk.co.virtual1.model.xml.out.DetailedContact;
import uk.co.virtual1.model.xml.out.Location;
import uk.co.virtual1.model.xml.out.Site;
import uk.co.virtual1.salesforce.object.Case;
import uk.co.virtual1.service.ApplicationEnvironment;
import uk.co.virtual1.service.SoapSerializer;

import static uk.co.virtual1.service.EnvironmentKey.*;

/**
 * @author Mikhail Tkachenko created on 23.08.16 15:24
 */
abstract class MessageFactory {

    @Autowired
    MessageFactoryUtils factoryUtils;
    @Autowired
    private SoapSerializer serializer;
    @Autowired
    ApplicationEnvironment environment;


    public String createMessage(Case sfCase) throws ProvisioningException {
        Object object = createObject(sfCase);
        return serializer.serialize(object);
    }

    abstract Object createObject(Case sfCase) throws ProvisioningException;

    Site createSite(uk.co.virtual1.salesforce.object.Site site) {
        Site result = new Site();

        result.setCompanyName(site.getEndCustomer().getName());
        result.setOnSiteTelephone(site.getPhone());

        BritishAddress britishAddress = result.getAddress().getBritishAddress();
        britishAddress.setPremisesName(site.getBuildingName());
        britishAddress.setThoroughfareNumber(site.getStreetNumber());
        britishAddress.setThoroughfareName(site.getStreetName());
        britishAddress.setPostTown(site.getTownCity());
        britishAddress.setPostCode(site.getPostCode());

        Location location = result.getLocation();
        location.setFloor(1);
        location.setRoom("todo");
        location.setPosition("todo");

        return result;
    }

    // TODO: 25.08.16
    Site createSiteAEnd(uk.co.virtual1.salesforce.object.Site site) {
        Site result = new Site();
        result.setEnd(Constants.END_A);
        result.setCompanyName("value need to be clarified");

        BritishAddress britishAddress = result.getAddress().getBritishAddress();
        britishAddress.setPremisesName(site.getBuildingName());
        britishAddress.setThoroughfareNumber("value need to be clarified");
        britishAddress.setThoroughfareName("value need to be clarified");
        britishAddress.setPostTown("value need to be clarified");
        britishAddress.setPostCode("value need to be clarified");
        return result;
    }

    DetailedContact virtual1DetailedContact() {
        DetailedContact detailedContact = new DetailedContact();
        detailedContact.setContactName(environment.get(VIRTUAL1_CONTACT_NAME));
        detailedContact.setTelephone(environment.get(VIRTUAL1_CONTACT_PHONE));
        detailedContact.setEmail(environment.get(VIRTUAL1_CONTACT_EMAIL));
        detailedContact.setTitle(environment.get(VIRTUAL1_CONTACT_TITLE));
        detailedContact.setFirstName(environment.get(VIRTUAL1_CONTACT_FIRST_NAME));
        detailedContact.setInitials(StringUtils.EMPTY);
        return detailedContact;
    }

    // TODO: 24.08.2016
    DetailedContact createDetailedContact(uk.co.virtual1.salesforce.object.Site site) {
        DetailedContact detailedContact = new DetailedContact();
        detailedContact.setContactName(site.getEndCustomer().getName());
        detailedContact.setTelephone("todo");
        detailedContact.setEmail("todo");
        detailedContact.setTitle("todo");
        detailedContact.setFirstName("todo");
        detailedContact.setInitials(StringUtils.EMPTY);
        return detailedContact;
    }


}
