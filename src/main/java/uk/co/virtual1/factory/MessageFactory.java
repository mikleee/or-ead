package uk.co.virtual1.factory;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import uk.co.virtual1.model.xml.out.BritishAddress;
import uk.co.virtual1.model.xml.out.DetailedContact;
import uk.co.virtual1.model.xml.out.Location;
import uk.co.virtual1.model.xml.out.Site;
import uk.co.virtual1.salesforce.object.Case;
import uk.co.virtual1.service.SoapSerializer;

/**
 * @author Mikhail Tkachenko created on 23.08.16 15:24
 */
abstract class MessageFactory {
    final static String VIRTUAL1_CONTACT_NAME = "Provisioning Team";
    final static String VIRTUAL1_CONTACT_PHONE = "03448840800";
    final static String VIRTUAL1_CONTACT_EMAIL = "provisioning@virtual1.com";
    final static String VIRTUAL1_CONTACT_TITLE = "Mr";
    final static String VIRTUAL1_CONTACT_FIRST_NAME = "Provisioning";

    @Autowired
    MessageFactoryUtils factoryUtils;
    @Autowired
    private SoapSerializer serializer;


    public String createMessage(Case sfCase) {
        Object object = createObject(sfCase);
        return serializer.serialize(object);
    }

    abstract Object createObject(Case sfCase);

    Site createSite(uk.co.virtual1.salesforce.object.Site site) {
        Site result = new Site();

        result.setCompanyName(site.getEndCustomer().getName()); // TODO: 23.08.16
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

    DetailedContact virtual1DetailedContact() {
        DetailedContact detailedContact = new DetailedContact();
        detailedContact.setContactName(VIRTUAL1_CONTACT_NAME);
        detailedContact.setTelephone(VIRTUAL1_CONTACT_PHONE);
        detailedContact.setEmail(VIRTUAL1_CONTACT_EMAIL);
        detailedContact.setTitle(VIRTUAL1_CONTACT_TITLE);
        detailedContact.setFirstName(VIRTUAL1_CONTACT_FIRST_NAME);
        detailedContact.setInitials(StringUtils.EMPTY);
        return detailedContact;
    }

    DetailedContact createDetailedContact(uk.co.virtual1.salesforce.object.Site site) {
        DetailedContact detailedContact = new DetailedContact();
        detailedContact.setContactName(site.getEndCustomer().getName());
        detailedContact.setTelephone(VIRTUAL1_CONTACT_PHONE);
        detailedContact.setEmail(VIRTUAL1_CONTACT_EMAIL);
        detailedContact.setTitle(VIRTUAL1_CONTACT_TITLE);
        detailedContact.setFirstName(VIRTUAL1_CONTACT_FIRST_NAME);
        detailedContact.setInitials(StringUtils.EMPTY);
        return detailedContact;
    }


}
