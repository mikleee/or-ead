package uk.co.virtual1.factory;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import uk.co.virtual1.exception.ProvisioningException;
import uk.co.virtual1.model.xml.out.BritishAddress;
import uk.co.virtual1.model.xml.out.Constants;
import uk.co.virtual1.model.xml.out.DetailedContact;
import uk.co.virtual1.model.xml.out.Site;
import uk.co.virtual1.salesforce.object.Access;
import uk.co.virtual1.salesforce.object.Case;
import uk.co.virtual1.service.ApplicationEnvironment;
import uk.co.virtual1.service.SoapSerializer;

import static uk.co.virtual1.service.EnvironmentKey.*;

/**
 * @author Mikhail Tkachenko created on 23.08.16 15:24
 */
abstract class MessageFactory implements InitializingBean {
    DetailedContact virtual1DetailedContact;

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


    @Override
    public void afterPropertiesSet() throws Exception {
        virtual1DetailedContact = virtual1DetailedContact();
    }

    private DetailedContact virtual1DetailedContact() {
        DetailedContact detailedContact = new DetailedContact();
        detailedContact.setContactName(environment.get(VIRTUAL1_CONTACT_NAME));
        detailedContact.setTelephone(environment.get(VIRTUAL1_CONTACT_PHONE));
        detailedContact.setEmail(environment.get(VIRTUAL1_CONTACT_EMAIL));
        detailedContact.setTitle(environment.get(VIRTUAL1_CONTACT_TITLE));
        detailedContact.setFirstName(environment.get(VIRTUAL1_CONTACT_FIRST_NAME));
        detailedContact.setInitials(StringUtils.EMPTY);
        return detailedContact;
    }

    Site createSiteAEnd(Case sfCase) {
        Site result = new Site();
        result.setEnd(Constants.END_A);

        result.setCompanyName("British Telcomns PLC"); //todo value need to be clarified
        result.setOnSiteTelephone(environment.get(VIRTUAL1_CONTACT_PHONE));

        BritishAddress britishAddress = result.getAddress().getBritishAddress();
        britishAddress.setPremisesName(sfCase.getAccess().getExchange().getExchangeName());
        britishAddress.setThoroughfareNumber("11"); //todo value need to be clarified
        britishAddress.setThoroughfareName("Nottingham Street"); //todo value need to be clarified
        britishAddress.setPostTown("LONDON"); //todo value need to be clarified
        britishAddress.setPostCode("W1U 5EL"); //todo value need to be clarified

        result.getLocation().setFloor("1"); // TODO: 25.08.16 remove to config
        result.getLocation().setRoom("15/16"); //todo value need to be clarified
        result.getLocation().setPosition("Floor=1/Room=134/Rack=303"); //todo value need to be clarified

        result.setDetailedContact(virtual1DetailedContact);

        return result;
    }

    Site createSiteBEnd(Case sfCase) {
        Access access = sfCase.getAccess();
        uk.co.virtual1.salesforce.object.Site site = access.getSiteBEnd();
        Site result = new Site();
        result.setEnd(Constants.END_B);

        result.setCompanyName(site.getEndCustomer().getName());
        result.setOnSiteTelephone(site.getPhone());

        BritishAddress britishAddress = result.getAddress().getBritishAddress();
        britishAddress.setPremisesName(site.getBuildingName());
        britishAddress.setThoroughfareNumber(site.getStreetNumber());
        britishAddress.setThoroughfareName(site.getStreetName());
        britishAddress.setPostTown(site.getTownCity());
        britishAddress.setPostCode(site.getPostCode());

        result.getLocation().setFloor(access.getFloorBEnd());
        result.getLocation().setRoom(access.getRoomBEnd());
        result.getLocation().setPosition(access.getRackBEnd());

        result.setDetailedContact(createDetailedContact(sfCase));
        return result;
    }


    // TODO: 24.08.2016 discuss hardcoded values
    private DetailedContact createDetailedContact(Case sfCase) {
        DetailedContact detailedContact = new DetailedContact();
        detailedContact.setContactName(sfCase.getContact().getName());
        detailedContact.setTelephone(sfCase.getContact().getTelephone());
        detailedContact.setEmail(environment.get(VIRTUAL1_CONTACT_EMAIL));
        detailedContact.setTitle(environment.get(VIRTUAL1_CONTACT_TITLE));
        detailedContact.setFirstName(environment.get(VIRTUAL1_CONTACT_FIRST_NAME));
        detailedContact.setInitials(StringUtils.EMPTY);
        return detailedContact;
    }


}
