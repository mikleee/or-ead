package uk.co.virtual1.salesforce;


import com.sforce.soap.partner.Field;
import com.sforce.soap.partner.PicklistEntry;
import com.sforce.soap.partner.sobject.SObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.virtual1.salesforce.object.Access;
import uk.co.virtual1.salesforce.object.Account;
import uk.co.virtual1.salesforce.object.AccountPbt;
import uk.co.virtual1.salesforce.object.AnalogueLine;
import uk.co.virtual1.salesforce.object.Asset;
import uk.co.virtual1.salesforce.object.BaseSalesforceObject;
import uk.co.virtual1.salesforce.object.Case;
import uk.co.virtual1.salesforce.object.CaseComment;
import uk.co.virtual1.salesforce.object.CaseContactRole;
import uk.co.virtual1.salesforce.object.ChargeType;
import uk.co.virtual1.salesforce.object.CloudProvisioning;
import uk.co.virtual1.salesforce.object.Component;
import uk.co.virtual1.salesforce.object.Contact;
import uk.co.virtual1.salesforce.object.EndCustomer;
import uk.co.virtual1.salesforce.object.Exchange;
import uk.co.virtual1.salesforce.object.FeedItem;
import uk.co.virtual1.salesforce.object.InnerVLAN;
import uk.co.virtual1.salesforce.object.NNI;
import uk.co.virtual1.salesforce.object.NetOpsCase;
import uk.co.virtual1.salesforce.object.Opportunity;
import uk.co.virtual1.salesforce.object.PartnerConnectWrapper;
import uk.co.virtual1.salesforce.object.PricingEntry;
import uk.co.virtual1.salesforce.object.Project;
import uk.co.virtual1.salesforce.object.Quote;
import uk.co.virtual1.salesforce.object.Radius;
import uk.co.virtual1.salesforce.object.RecordType;
import uk.co.virtual1.salesforce.object.RetailPortalLead;
import uk.co.virtual1.salesforce.object.SFIPJustDevice;
import uk.co.virtual1.salesforce.object.SfAttachment;
import uk.co.virtual1.salesforce.object.SfEmail;
import uk.co.virtual1.salesforce.object.SfIpJust;
import uk.co.virtual1.salesforce.object.Site;
import uk.co.virtual1.salesforce.object.VPN;
import uk.co.virtual1.salesforce.object.Virtual1DatacentrePostcode;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static uk.co.virtual1.salesforce.QueryTemplates.*;


@SuppressWarnings("WeakerAccess")
@Service
public class SalesforceService {
    private static final Logger LOGGER = Logger.getLogger(SalesforceService.class);
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private static final String[] EXCLUDED_RECORD_TYPES = {"Outage", "Planned Works"};

    private final ConvertingSFObjects converter = new ConvertingSFObjects();

    private final CustomizationType customizationType = CustomizationType.VIRTUAL1;

    @Autowired
    private SalesforceDataSource dataSource;


    public enum CustomizationType {
        VIRTUAL1,
        NONE
    }


    public Map<String, String> getPickListValue(String object, String fieldName) {
        Map<String, String> map = new LinkedHashMap<>();

        for (Field field : dataSource.describeSObject(object)) {
            if (field.getName().equalsIgnoreCase(fieldName)) {
                PicklistEntry[] picklistValues = field.getPicklistValues();
                for (PicklistEntry picklistEntry : picklistValues) {
                    map.put(picklistEntry.getValue(), picklistEntry.getLabel());
                }
            }
        }
        return map;
    }

    public void testConnection() {
        String queryString = "SELECT count() FROM Account";
        dataSource.retrieveAll(queryString);
    }

    // ------------------------ accounts ------------------------

    public Account getAccount(String id) {
        String query = format("SELECT %s FROM Account WHERE Id = '%s'", ACCOUNT_FIELDS, id);
        return retrieveAccount(query);
    }


    public Account getAccountByName(String name) {
        String query = format("SELECT %s FROM Account WHERE Name = '%s'", ACCOUNT_FIELDS, name);
        return retrieveAccount(query);
    }

    private Account retrieveAccount(String query) {
        SObject sObject = retrieveOne(query);
        return sObject == null ? null : converter.convertAccount(sObject);
    }

    public String updateAccountSpecialInstructions(Account account) {
        switch (customizationType) {
            case VIRTUAL1:
                SObject sObject = converter.convertForSpecialInstructionsUpdate(account);
                return update(sObject, account);
            default:
                return null;
        }
    }

    public String updateAccountBillingAddress(Account account) {
        switch (customizationType) {
            case VIRTUAL1:
                SObject sObject = converter.convertForBillingAddressUpdate(account);
                return update(sObject, account);
            default:
                return null;
        }
    }

    public AccountPbt getAccountPBT(String accountId) {
        String query = format("SELECT %s FROM Account WHERE Id='%s' ", ACCOUNT_FIELDS, accountId);
        SObject sObject = retrieveOne(query);
        if (sObject == null) {
            return null;
        } else {
            AccountPbt accountPbt = converter.convertAccountPBT(sObject);
            return populateAccountPbt(accountPbt);
        }
    }

    private AccountPbt populateAccountPbt(AccountPbt accountPbt) {
        String pbt = accountPbt.getPbt();
        if (pbt == null || pbt.equals("None")) {
            accountPbt.setPbt("None");
            accountPbt.setStatus(AccountPbt.PbtValue.NONE);
        } else {
            List<String> pbtValues = new ArrayList<>();
            Map<String, String> pickListValue = getPickListValue("Account", "PBT__c");
            for (String value : pickListValue.values()) {
                pbtValues.add(value);
            }
            accountPbt.setValues(pbtValues);
            accountPbt.setStatus(AccountPbt.PbtValue.SELECTED);
        }
        return accountPbt;
    }


    // ------------------------


    // ------------------------ access ------------------------

    public Access getAccess(String accessId) {
        String query = format("SELECT %s FROM Access__c WHERE Id ='%s'", ACCESS_FIELDS_WITH_COLLECTIONS, accessId);

        SObject sObject = retrieveOne(query);
        if (sObject != null) {
            Access access = converter.convertAccess(sObject);
            return populateInnerVlans(access);
        } else {
            return null;
        }
    }

    public List<Access> getAccessesByStatus(String accountId, String status) {
        String query = format("SELECT %s FROM Access__c WHERE Site_Name_B_End__r.End_Customer_Name__r.Account_Name__r.Id = '%s'", ACCESS_FIELDS_WITH_COLLECTIONS, accountId);

        if ("active".equals(status)) {
            query += "AND Status__c in ('To be ordered', 'In provisioning', 'Active', 'Active-Pending Cease', 'Suspended')";
        } else if ("cancelled".equals(status)) {
            query += "AND Status__c = 'Cancelled'";
        } else if ("ceased".equals(status)) {
            query += "AND Status__c = 'Ceased'";
        }

        List<Access> result = new ArrayList<>();
        for (SObject sObject : retrieveAll(query)) {
            Access access = converter.convertAccess(sObject);
            result.add(access);
        }
        return result;
    }

    private Access populateInnerVlans(Access access) {
        String query = format("SELECT %s FROM Inner_VLAN__c WHERE Access_ID__c = '%s'", INNER_VLAN_FIELDS, access.getName());
        for (SObject sObject : retrieveAll(query)) {
            InnerVLAN vlan = converter.convertInnerVLAN(sObject);
            access.getInnerVlans().add(vlan);
        }
        return access;
    }

    public String createAccess(Access access) {
        SObject sfObject = converter.convert(access);
        return create(sfObject, access);
    }

    public String updateAccess(Access access) {
        SObject caseObject = converter.convert(access);
        return update(caseObject, access);
    }

    // ------------------------


    // ------------------------ analogue line ------------------------

    public AnalogueLine getAnalogueLine(String id) {
        String query = format("SELECT %s FROM Analogue_Line__c WHERE Id ='%s'", ANALOGUE_LINE_FIELDS, id);
        SObject sObject = retrieveOne(query);
        return sObject == null ? null : converter.convertAnalogueLine(sObject);
    }

    public List<AnalogueLine> getAnalogueLineForAccess(String accessId) {
        String query = format("SELECT %s FROM Analogue_Line__c WHERE Access_ID__c ='%s'", ANALOGUE_LINE_FIELDS, accessId);
        List<AnalogueLine> analogueLines = new ArrayList<>();
        for (SObject sObject : retrieveAll(query)) {
            AnalogueLine analogueLine = converter.convertAnalogueLine(sObject);
            analogueLines.add(analogueLine);
        }
        return analogueLines;
    }

    public String createAnalogueLine(AnalogueLine analogueLine) {
        SObject sfObject = converter.convert(analogueLine);
        return create(sfObject, analogueLine);
    }

    // ------------------------


    // ------------------------ asset ------------------------

    public List<Asset> getAssets(String accountId) {
        String query = accountAssetsQuery(accountId);
        return retrieveAssets(query);
    }

    public List<Asset> getAssetsByStatus(String accountId, String status) {
        if (status == null) {
            return new ArrayList<>();
        }

        String query = accountAssetsQuery(accountId);
        if ("active".equals(status)) {
            query = query + "AND Status__c in ('To be ordered', 'In provisioning', 'Active', 'Active-Pending Cease', 'Suspended')";
        } else if ("cancelled".equals(status)) {
            query = query + "AND Status__c = 'Cancelled'";
        } else if ("ceased".equals(status)) {
            query = query + "AND Status__c = 'Ceased'";
        }
        return retrieveAssets(query);
    }

    public List<Asset> getEndCustomerAssets(String endCustomerId, String assetType) {
        String query = format("SELECT %s FROM Assets__c WHERE Project_Number__r.End_Customer_Name__r.Id = '%s' AND Asset_Type_New__c= '%s'", ASSET_FIELDS_WITH_COLLECTIONS, endCustomerId, assetType);
        return retrieveAssets(query);
    }

    // TODO: 28.07.16
    public Asset getAsset(String assetId) {
        String query = format("SELECT %s FROM Assets__c WHERE Id ='%s'", ASSET_FIELDS_WITH_COLLECTIONS, assetId);
        SObject sObject = retrieveOne(query);
        return sObject == null ? null : converter.convertAsset(sObject);
    }

    private List<Asset> retrieveAssets(String query) {
        List<Asset> assets = new ArrayList<>();
        for (SObject sObject : dataSource.retrieveAll(query)) {
            Asset asset = converter.convertAsset(sObject);
            assets.add(asset);
        }
        return assets;
    }

    private String accountAssetsQuery(String accountId) {
        return format("SELECT %s FROM Assets__c WHERE Site_Name__r.End_Customer_Name__r.Account_Name__r.Id = '%s'", ASSET_FIELDS, accountId);
    }

    public String createAsset(Asset asset) {
        SObject sfObject = converter.convert(asset);
        sfObject.setFieldsToNull(new String[]{"Loopback_IPv6__c"});
        return create(sfObject, asset);
    }


    // ------------------------


    // ------------------------ case ------------------------

    public Case getCase(String caseId) {
        if (caseId.length() >= 15) {
            return getCaseByField("Id", caseId);
        } else {
            return getCaseByField("CaseNumber", caseId);
        }
    }

    /**
     * retrieve case where access is not fully populated it is populated in the cached service
     */
    private Case getCaseByField(String fieldName, String caseId) {
        String query = format("SELECT %s FROM Case WHERE %s = '%s'", CASE_FIELDS_WITH_COLLECTIONS, fieldName, caseId);
        SObject object = retrieveOne(query);
        return object != null ? converter.convertCase(object) : null;
    }

    public List<Case> getCasesByAccess(String accessId, String status) {
        String query = format("SELECT %s FROM Case WHERE Hidden_From_1Portal__c=false AND Access_ID__c = '%s' " +
                "AND ((Type = 'Automated Provision' AND RecordType.Name = 'System Automation') " +
                "OR Type = 'Provisioning' " +
                "OR Type = 'Support' " +
                "OR (Type = 'EI' AND Asset_Type_B_End_del_c__c = '1Cloud Demo Enterprise')) ", CASE_FIELDS_SHORT, accessId);

        if (status.equals("showOnlyOpen")) {
            query += "AND IsClosed=false";
        }
        return retrieveCases(query);
    }

    public List<Case> getCasesByAsset(String assetId, String status) {
        String query = format("SELECT %s FROM Case WHERE Hidden_From_1Portal__c=false AND Asset_ID_B_End__c = '%s' " +
                "AND ((Type = 'Automated Provision' AND RecordType.Name = 'System Automation') " +
                "OR Type = 'Provisioning' " +
                "OR Type = 'Support' " +
                "OR (Type = 'EI' AND Asset_Type_B_End_del_c__c = '1Cloud Demo Enterprise')) ", CASE_FIELDS_SHORT, assetId);

        if (status.equals("showOnlyOpen")) {
            query += "AND IsClosed=false";
        }

        return retrieveCases(query);
    }

    public List<Case> getCases(String accountId, String caseType, boolean hasPublicComment, boolean includeOnlyNonClosed, List<String> accessStatuses, boolean fromCasesPage) {
        String query = format("SELECT %s FROM Case WHERE Hidden_From_1Portal__c = false AND AccountId = '%s'", CASE_FIELDS_WITH_COLLECTIONS, accountId);

        if (fromCasesPage) {
            query += "AND (Type = '" + caseType + "' OR (Type = 'EI' AND Asset_Type_B_End_del_c__c = '1Cloud Demo Enterprise'))";
        } else {
            query += "AND Type = '" + caseType + "'";
        }

        query = caseListQueryFilters(query, hasPublicComment, includeOnlyNonClosed, accessStatuses);
        return retrieveCases(query);
    }

    List<Case> getCasesForCSV(String accountId, String caseType, boolean hasPublicComment, boolean includeOnlyNonClosed, List<String> accessStatuses) {
        String query = format("SELECT %s FROM Case WHERE Hidden_From_1Portal__c = false AND AccountId = '%s' AND  Type = '%s'", CASE_FIELDS_WITH_COLLECTIONS, accountId, caseType);
        query = caseListQueryFilters(query, hasPublicComment, includeOnlyNonClosed, accessStatuses);
        return retrieveCases(query);
    }

    private String caseListQueryFilters(String query, boolean hasPublicComment, boolean includeOnlyNonClosed, List<String> accessStatuses) {
        String result = query;

        for (String excludedRecordType : EXCLUDED_RECORD_TYPES) {
            result += " AND  RecordType.Name != '" + excludedRecordType + "'";
        }
        if (hasPublicComment) {
            result += " AND Id IN (SELECT ParentId FROM CaseComment WHERE IsPublished = true)";
        }
        if (accessStatuses != null && !accessStatuses.isEmpty()) {
            result += " AND Access_Status__c IN " + in(accessStatuses);
        }
        if (includeOnlyNonClosed) {
            result += " AND IsClosed = false ";
        }
        return result;
    }

    private List<Case> retrieveCases(String query) {
        List<Case> cases = new ArrayList<>();
        for (SObject sObject : retrieveAll(query)) {
            Case sfCase = converter.convertCase(sObject);
            cases.add(sfCase);
        }
        return cases;
    }

    public String createCase(Case sfcase) {
        SObject caseObject = converter.convert(sfcase);
        return create(caseObject, sfcase);
    }

    public String updateCase(Case sfcase) {
        SObject caseObject = converter.convertForUpdate(sfcase);
        return update(caseObject, sfcase);
    }


    public String updateCaseContact(Case sfcase) {
        LOGGER.info("Update contactId for " + sfcase);
        SObject caseObject = new SObject();
        caseObject.setId(sfcase.getId());
        caseObject.setType("Case");
        caseObject.setField("ContactId", sfcase.getContact().getId());
        return dataSource.update(caseObject);
    }

    // TODO: 17.08.16
    public String updateCaseReference(Case sfcase) {
        LOGGER.debug("Update a case reference in SF. " + sfcase);
        SObject caseObject = new SObject();
        caseObject.setType("Case");
        caseObject.setId(sfcase.getId());
        caseObject.setField("Partner_Internal_Ref__c", sfcase.getReference());
        return dataSource.update(caseObject);
    }

    public String addCaseComment(CaseComment caseComment) {
        SObject sObject = converter.convert(caseComment);
        return create(sObject, caseComment);
    }

    public String addCaseContactRole(CaseContactRole caseContactRole) {
        SObject sObject = converter.convert(caseContactRole);
        return create(sObject, caseContactRole);
    }

    // ------------------------


    // ------------------------ charge type ------------------------

    public ChargeType getChargeTypeByName(String chargeTypeName) {
        if (StringUtils.isBlank(chargeTypeName)) {
            return null;
        }

        String query = format("SELECT %s FROM Charge_Type__c WHERE Name='%s'", COMMON_FIELDS, escapeSOQL(chargeTypeName));
        return retrieveChargeType(query);
    }

    private ChargeType retrieveChargeType(String query) {
        SObject sObject = retrieveOne(query);
        return sObject != null ? converter.convertChargeType(sObject) : null;
    }

    public String сreateChargeType(ChargeType chargeType) {
        SObject sObject = converter.convert(chargeType);
        return create(sObject, chargeType);
    }

    // ------------------------


    // ------------------------ CloudProvisioning ------------------------

    public CloudProvisioning getCloudProvisioning() {
        String query = format("select %s FROM Cloud_Provisioning__c", CLOUD_PROVISIONING_FIELDS);
        SObject sObject = retrieveOne(query);
        return sObject == null ? null : converter.convertCloudProvisioning(sObject);
    }

    // TODO: 28.07.16
    public CloudProvisioning getCloudProvisioningFromAsset(String projectId) {
        String query = format("SELECT Id, (SELECT %s FROM Assets__r ) from project__c where Id = '%s'", CLOUD_PROVISIONING_FIELDS, projectId);
        List<SObject> sObjects = retrieveAll(query);
        return converter.convertCloudProvisioningFromAssets(sObjects);
    }

    // ------------------------


    // ------------------------ contacts ------------------------

    public List<Contact> getContactsByRole(String accountId, String role) {
        String query = format("SELECT %s FROM Contact WHERE Account.Id = '%s' and Left_the_Company__c = false", CONTACT_FIELDS, accountId);

        if (StringUtils.isNotBlank(role)) {
            query += " and Role__c includes ('" + escapeSOQL(role) + "')";
        }
        query += " ORDER BY Name ASC";

        List<Contact> result = new ArrayList<>();
        for (SObject sObject : retrieveAll(query)) {
            Contact contact = converter.convertContact(sObject);
            result.add(contact);
        }
        return result;
    }

    public Contact getContactByEmail(String accountId, String email) {
        String query = format("SELECT %s FROM Contact WHERE Account.Id = '%s' and Left_the_Company__c = false and Email= '%s'", CONTACT_FIELDS, accountId, escapeSOQL(email));
        return retrieveContact(query);
    }

    public Contact getContactById(String contactId) {
        String query = format("SELECT %s FROM Contact WHERE Id = '%s'", CONTACT_FIELDS, contactId);
        return retrieveContact(query);
    }

    private Contact retrieveContact(String query) {
        SObject sObject = retrieveOne(query);
        return sObject == null ? null : converter.convertContact(sObject);
    }

    public String createContact(Contact contact) {
        SObject sfObject = converter.convert(contact);
        return create(sfObject, contact);
    }

    public String updateContact(Contact contact) {
        SObject sfObject = converter.convert(contact);
        return update(sfObject, contact);
    }

    // -----------------------------


    // ------------------------ end customer ------------------------

    public EndCustomer getEndCustomer(String endCustomerId) {
        String query = format("SELECT %s FROM End_Customer__c  WHERE Id = '%s'", END_CUSTOMER_FIELDS, endCustomerId);
        return retrieveEndCustomer(query);
    }

    public EndCustomer getEndCustomerByName(String accountId, String name) {
        String query = format("SELECT %s FROM End_Customer__c  WHERE Account_Name__c = '%s' and Name = '%s'", END_CUSTOMER_FIELDS, accountId, name);
        return retrieveEndCustomer(query);
    }

    private EndCustomer retrieveEndCustomer(String query) {
        SObject sObject = retrieveOne(query);
        return sObject == null ? null : converter.convertEndCustomer(sObject);
    }


    public List<EndCustomer> getEndCustomers(String accountId) {
        String query = format("SELECT %s FROM End_Customer__c  WHERE Account_Name__r.Id = '%s'", END_CUSTOMER_FIELDS, accountId);
        List<EndCustomer> result = new ArrayList<>();
        for (SObject sObject : retrieveAll(query)) {
            EndCustomer endCustomer = converter.convertEndCustomer(sObject);
            result.add(endCustomer);
        }
        return result;
    }

    public String createEndCustomer(EndCustomer endCustomer) {
        SObject sObject = converter.convert(endCustomer);
        return create(sObject, endCustomer);
    }

    public String updateEndCustomer(EndCustomer endCustomer) {
        SObject sObject = converter.convert(endCustomer);
        return update(sObject, endCustomer);
    }

    // -----------------------------


    // ------------------------ exchange ------------------------

    public Exchange getExchange(String id) {
        String query = format("SELECT %s FROM Exchange__c WHERE Id='%s'", COMMON_FIELDS, id);
        return retrieveExchange(query);
    }

    public Exchange getExchangeByName(String name) {
        String query = format("SELECT %s FROM Exchange__c WHERE Name='%s'", COMMON_FIELDS, name);
        return retrieveExchange(query);
    }

    private Exchange retrieveExchange(String query) {
        SObject sObject = retrieveOne(query);
        return sObject == null ? null : converter.convertExchange(sObject);
    }

    public String createExchangeCode(Exchange exchange) {
        SObject sfObject = converter.convert(exchange);
        return create(sfObject, exchange);
    }

    // -----------------------------


    // ------------------------ ip just ------------------------

    public List<SfIpJust> getPartnerIpJusts(String partnerId) {
        String query = format("SELECT %s FROM IP_Justification__c WHERE PartnerName__r.Id = '%s'", IP_JUST_FIELDS_WITH_COLLECTIONS, partnerId);

        List<SfIpJust> result = new ArrayList<>();
        for (SObject sObject : retrieveAll(query)) {
            SfIpJust sfIpJust = converter.convertSfIpJust(sObject);
            result.add(sfIpJust);
        }
        return result;
    }

    public SfIpJust getIpJust(String sfIpJustId) {
        String query = format("SELECT %s FROM IP_Justification__c WHERE Id = '%s'", IP_JUST_FIELDS_WITH_COLLECTIONS, sfIpJustId);
        return retrieveIpJust(query);
    }

    public SfIpJust getSfIpJustByProject(String projectId) {
        String query = format("SELECT %s FROM IP_Justification__c WHERE ProjectId__c = '%s'", IP_JUST_FIELDS_WITH_COLLECTIONS, projectId);
        return retrieveIpJust(query);
    }

    public String createSfIpJust(SfIpJust sfIpJust) {
        SObject sfObject = converter.convert(sfIpJust);
        String id = create(sfObject, sfIpJust);
        sfIpJust.setId(id);

        List<SFIPJustDevice> devices = sfIpJust.getSfipJustDevices();
        if (!devices.isEmpty()) {
            for (SFIPJustDevice device : devices) {
                device.setIpJustId(id);
            }
            createIpJustDevices(devices);
        }

        if (sfIpJust.getNetworkDiagram() != null) {
            sfIpJust.getNetworkDiagram().setParentId(id);
            createAttachment(sfIpJust.getNetworkDiagram());
        }

        return id;
    }

    public String updateSfIPJust(SfIpJust ipJust) {
        SfIpJust persisted = getIpJust(ipJust.getId());
        updateIpJustDevices(ipJust.getSfipJustDevices(), persisted);
        updateSfIpJustDiagram(ipJust, persisted);
        SObject object = converter.convert(ipJust);
        return update(object, ipJust);
    }

    private void updateIpJustDevices(List<SFIPJustDevice> devices, SfIpJust persisted) {
        List<SFIPJustDevice> createCandidates = new ArrayList<>();
        List<SFIPJustDevice> updateCandidates = new ArrayList<>();
        List<String> deleteCandidates = new ArrayList<>();
        for (SFIPJustDevice d : persisted.getSfipJustDevices()) {
            deleteCandidates.add(d.getId());
        }

        for (SFIPJustDevice device : devices) {
            if (device.getId() == null) {
                createCandidates.add(device);
            } else {
                updateCandidates.add(device);
                deleteCandidates.remove(device.getId());
            }
        }

        if (!deleteCandidates.isEmpty()) delete(deleteCandidates);
        if (!createCandidates.isEmpty()) createIpJustDevices(devices);
        if (!updateCandidates.isEmpty()) updateIpJustDevices(devices);
    }

    private void createIpJustDevices(List<SFIPJustDevice> devices) {
        SObject[] bulk = converter.convertIpJustDevices(devices);
        List<String> ids = dataSource.create(bulk);
        LOGGER.info("Ip just devices created " + ids);
    }

    private void updateIpJustDevices(List<SFIPJustDevice> devices) {
        SObject[] bulk = converter.convertIpJustDevices(devices);
        dataSource.update(bulk);
        LOGGER.info("Ip just devices updated " + devices);
    }

    private void updateSfIpJustDiagram(SfIpJust ipJust, SfIpJust persisted) {
        SfAttachment diagram = ipJust.getNetworkDiagram();
        SfAttachment currentDiagram = persisted.getNetworkDiagram();

        if (diagram == null) {
            if (currentDiagram != null) {
                LOGGER.info("Remove " + currentDiagram);
                delete(currentDiagram.getId());
            }
        } else {
            if (currentDiagram == null || !currentDiagram.getName().equals(diagram.getName())) {
                diagram.setParentId(ipJust.getId());
                createAttachment(diagram);
            }
        }
    }

    private SfIpJust retrieveIpJust(String query) {
        SObject sObject = retrieveOne(query);
        if (sObject != null) {
            SfIpJust sfIpJust = converter.convertSfIpJust(sObject);
            if (sfIpJust.getNetworkDiagram() != null) {
                SfAttachment attachment = getAttachment(sfIpJust.getNetworkDiagram().getId());
                sfIpJust.setNetworkDiagram(attachment);
            }
            return sfIpJust;
        } else {
            return null;
        }
    }

    // -----------------------------


    // ------------------------ NNI ------------------------

    public NNI getNniByName(String name) {
        String query = format("SELECT %s FROM NNI__c WHERE Name='%s'", NNI_FIELDS, name);
        SObject sObject = retrieveOne(query);
        return sObject == null ? null : converter.convertNNI(sObject);
    }

    public NNI getNniByUpstreamDeviceName(String carrierProvider, String nniType, String upstreamDeviceName) {
        String query = format("SELECT %s FROM NNI__c WHERE Carrier_Provider__r.Name='%s' AND NNI_Type__c='%s' and Upstream_Device_Name__c='%s'",
                NNI_FIELDS, escapeSOQL(carrierProvider), escapeSOQL(nniType), escapeSOQL(upstreamDeviceName));
        SObject sObject = retrieveOne(query);
        return sObject == null ? null : converter.convertNNI(sObject);
    }

    // -----------------------------

    // ------------------------ pricing entry ------------------------

    public String createPricingEntry(PricingEntry pricingEntry) {
        SObject sfObject = converter.convert(pricingEntry);
        return create(sfObject, pricingEntry);
    }

    // -----------------------------


    // ------------------------ project ------------------------

    public List<Project> getPartnerProjects(String accountId) {
        String query = format("SELECT %s FROM Project__c WHERE End_Customer_Name__r.Account_Name__r.Id = '%s' AND Project_Status_del__c NOT IN ('Completed', 'Ceased', 'Cancelled')",
                PROJECT_FIELDS, accountId);
        return retrieveProjects(query);
    }

    public List<Project> getEndCustomerProjects(String endCustomerId) {
        String query = format("SELECT %s FROM Project__c WHERE End_Customer_Name__r.Id = '%s' AND Project_Status_del__c NOT IN ('Completed', 'Ceased', 'Cancelled')",
                PROJECT_FIELDS, endCustomerId);
        return retrieveProjects(query);
    }

    @Deprecated
    public List<Project> getProjectsForCloudRequest(String accountId, String projectType) {
        String sql = "select Id, Customer_Signed_By__c, Project_Status_del__c from Project__c where Account_ID__c=\'" + accountId + "\' and Project_Type__c=\'" + projectType + "\'";
        List<Project> projects = new ArrayList<>();
        for (SObject sObject : retrieveAll(sql)) {
            Project project = getProjectForCloudRequest((String) sObject.getField("Id"));
            projects.add(project);
        }

        return projects;
    }

    @Deprecated
    public Project getProjectForCloudRequest(String projectId) {
        String queryString = "SELECT Id, Customer_Signed_By__c, Project_Status_del__c FROM Project__c WHERE Id = '" + projectId + "'";
        SObject sObject = retrieveOne(queryString);
        return sObject == null ? null : converter.convertProjectForCloudRequest(sObject);
    }

    private List<Project> retrieveProjects(String query) {
        List<Project> projects = new ArrayList<>();
        for (SObject sObject : retrieveAll(query)) {
            Project project = converter.convertProject(sObject);
            projects.add(project);
        }
        return projects;
    }

    public Project getProject(String projectId) {
        String query = format("SELECT %s FROM Project__c WHERE Id = '%s'", PROJECT_FIELDS_WITH_COLLECTIONS, projectId);
        SObject sObject = retrieveOne(query);
        return sObject == null ? null : converter.convertProject(sObject);
    }

    public String createProject(Project project) {
        SObject sfObject = converter.convert(project);
        return create(sfObject, project);
    }

    public String updateProject(Project project) {
        SObject projectObject = converter.convert(project);
        return update(projectObject, project);
    }

    // -----------------------------


    // ------------------------ record type ------------------------

    @Deprecated
    public String getRecordTypeId(String objectType, String name) {
        List<RecordType> recordTypes = getRecordTypes(objectType);
        for (RecordType recordType : recordTypes) {
            if (name.equals(recordType.getName())) {
                return recordType.getId();
            }
        }
        return null;
    }

    public List<RecordType> getRecordTypes(String objectType) {
        String query = format("SELECT %s FROM RecordType WHERE SobjectType = '%s' and IsActive = True", RECORD_TYPE_FIELDS, objectType);
        List<RecordType> result = new ArrayList<>();
        for (SObject sObject : retrieveAll(query)) {
            RecordType recordType = converter.convertRecordType(sObject);
            result.add(recordType);
        }
        return result;
    }

    // -----------------------------


    // ------------------------ site ------------------------

    public List<Site> getSites(String accountId) {
        String query = format("SELECT %s FROM Site__c WHERE End_Customer_Name__r.Account_Name__c = '%s'", SITE_FIELDS, accountId);
        return retrieveSites(query);
    }

    public List<Site> getSitesByName(String endCustomerId, String siteName) {
        String query = format("SELECT %s FROM Site__c WHERE End_Customer_Name__r.Id = '%s' and Name = '%s'", SITE_FIELDS, endCustomerId, escapeSOQL(siteName));
        return retrieveSites(query);
    }

    private List<Site> retrieveSites(String query) {
        List<Site> result = new ArrayList<>();
        for (SObject sObject : retrieveAll(query)) {
            Site site = converter.convertSite(sObject);
            result.add(site);
        }
        return result;
    }

    public Site getSite(String siteId) {
        String query = format("SELECT %s FROM Site__c  WHERE Id = '%s'", SITE_FIELDS, siteId);
        SObject sObject = retrieveOne(query);
        return sObject == null ? null : converter.convertSite(sObject);
    }

    public String createSite(Site site) {
        SObject sfObject = converter.convert(site);
        return create(sfObject, site);
    }

    public String updateSite(Site site) {
        SObject sfObject = converter.convert(site);
        return update(sfObject, site);
    }

    // -----------------------------


    // ------------------------ vpn ------------------------

    public VPN getVPNByName(String name) {
        String query = format("SELECT %s FROM VPN__c WHERE Name='%s'", VPN_FIELDS, name);
        SObject sObject = retrieveOne(query);
        return sObject == null ? null : converter.convertVPN(sObject);
    }

    public List<VPN> getVPNs(String accountId) {
        String query = format("SELECT %s FROM VPN__c WHERE End_Customer__r.Account_Name__c = '%s'", VPN_FIELDS, accountId);

        List<VPN> result = new ArrayList<>();
        for (SObject sObject : retrieveAll(query)) {
            VPN vpn = converter.convertVPN(sObject);
            if (vpn.getVlans().isEmpty() && vpn.getRadiuses().isEmpty()) {
                continue; //ignore if no vlans
            }
            result.add(vpn);
        }
        return result;
    }

    public String createVPN(VPN vpn) {
        SObject sObject = converter.convert(vpn, this);
        return create(sObject, vpn);
    }

    // -----------------------------


    public List<NetOpsCase> getNetOpsCases() {
        String queryString = "SELECT Id,Access_ID_Name__c,Access_Type__c,End_Customer_Name__c,Site_Name_B_End__c," +
                "NetOps_Owner__r.Name,Provisional_RFS__c,Ordered_with_Carrier_Date__c,Site_Survey_Date__c," +
                "Site_Survey_Results__c,Installation_Date__c, Asset_ID_B_End__r.Name, Asset_ID_B_End__r.PO_Number_del__c," +
                "Asset_ID_B_End__r.Loop_Back_Address__c,Asset_ID_B_End__r.Status__c,Access_ID__r.Service_Type__c " +
                " FROM Case order" +
                " WHERE Type = 'Provisioning' and IsClosed = false " +
                " and Access_ID__r.Status__c <> 'Active' and Access_ID__r.Status__c <> 'Duplicate' and Access_ID__r.Status__c <> 'Closed'" +
                " and Provisional_RFS__c <> null and NetOps_Owner__c <> null " +
                " and (not Access_ID__r.End_Customer_Name__c like 'Test Customer%')" +
                " and (not Account.Name like 'Test Customer%')" +
                " ORDER BY Provisional_RFS__c desc";

        List<NetOpsCase> result = new ArrayList<>();
        for (SObject sObject : retrieveAll(queryString)) {
            NetOpsCase sfCase = converter.convertNetOpsCase(sObject);
            if (StringUtils.isNotBlank(sfCase.getAccessName()) || StringUtils.isNotBlank(sfCase.getAssetName())) {
                result.add(sfCase);
            }
        }
        return result;
    }

    // TODO: 29.07.16 cache
    public List<String> getClosedStatuses() {
        String query = "SELECT MasterLabel FROM CaseStatus WHERE IsClosed = true";

        List<String> closedStatuses = new ArrayList<>();
        for (SObject sObject : retrieveAll(query)) {
            closedStatuses.add((String) sObject.getField("MasterLabel"));
        }
        return closedStatuses;
    }

    public List<Virtual1DatacentrePostcode> getDatacentrePostcodes() {
        String queryString = "SELECT Id, Name, Postcode__c, Virtual1_Exchange_Name__c from Virtual1Datacentres__c WHERE IsDeleted=false";

        List<Virtual1DatacentrePostcode> postcodes = new ArrayList<>();
        List<SObject> sObjects = retrieveAll(queryString);
        for (SObject sObject : sObjects) {
            Virtual1DatacentrePostcode postcode = converter.convertVirtul1DatacentrePostcode(sObject);
            postcodes.add(postcode);
        }
        return postcodes;
    }

    /**
     * not cached
     */
    String createOpportunity(Opportunity opportunity) {
        SObject sObject = converter.convert(opportunity);
        return create(sObject, opportunity);
    }

    @SuppressWarnings("unused")
    public void updateWorkScheduledDate(String caseId, Date scheduledDate) {
        LOGGER.info("Update a case in SF. " + caseId);

        SObject caseObject = new SObject();
        caseObject.setType("Case");
        caseObject.setId(caseId);
        caseObject.setField("Work_Scheduled_Date__c", scheduledDate);

        dataSource.create(caseObject);
    }

    /**
     * not cached
     */
    String addFeedItem(FeedItem feedItem) {
        SObject sObject = converter.convert(feedItem);
        return create(sObject, feedItem);
    }

    public void createRetailPortalLead(RetailPortalLead lead) {
        LOGGER.info("Create new retail portal lead");

        SObject leadObject = new SObject();
        leadObject.setType("Retail_Portal_Leads__c");

        leadObject.setField("Company_Name__c", lead.getCompanyName());
        leadObject.setField("Email_Address__c", lead.getEmailAddress());
        leadObject.setField("Position__c", lead.getPosition());
        leadObject.setField("Quote_Type__c", lead.getQuoteType());
        leadObject.setField("Telephone_Number__c", lead.getTelephoneNumber());
        leadObject.setField("Name__c", lead.getLeadName());

        String leadId = create(leadObject, lead);

        List<SObject> quoteObjects = new ArrayList<>();
        for (Quote quote : lead.getQuotes()) {
            SObject quoteObject = new SObject();
            quoteObject.setType("Quote__c");

            quoteObject.setField("Carrier_Provider__c", quote.getCarrierProvider());
            quoteObject.setField("Post_code__c", quote.getPostCode());
            quoteObject.setField("Product__c", quote.getProduct());
            if (quote.getQuotePrice() != null) {
                quoteObject.setField("Quote_Price__c", quote.getQuotePrice().doubleValue());
            }
            quoteObject.setField("Retail_Portal_Lead__c", leadId);

            quoteObjects.add(quoteObject);
        }

        dataSource.create(quoteObjects.toArray(new SObject[]{}));
    }

    // TODO: 19.08.16
    public String createRadius(Radius radius) {
        SObject sfObject = converter.convert(radius);
        return create(sfObject, radius);
    }

    // TODO: 19.08.16
    public String createComponent(Component component) {
        SObject sfObject = converter.convert(component);
        return create(sfObject, component);
    }


    // TODO: 18.08.16
    public List<PartnerConnectWrapper> getPartnerConnects(String accountId) {
        List<PartnerConnectWrapper> partnerConnects = new ArrayList<>();


        String queryString = "SELECT Name,Site_Name_B_End__r.Name \n" +
                "FROM Access__c \n" +
                "WHERE Site_Name_B_End__r.End_Customer_Name__r.Account_Name__r.Id =\'" + accountId +
                "\' AND Service_Type__c = 'Partner Connect' AND (Status__c = 'Active' OR Status__c = 'In Provisioning')";

        List<SObject> sObjects = retrieveAll(queryString);
        for (SObject sObject : sObjects) {

            PartnerConnectWrapper wrapper = new PartnerConnectWrapper();

            wrapper.setName((String) sObject.getField("Name"));
            wrapper.setSiteName((String) sObject.getChild("Site_Name_B_End__r").getChild("Name").getValue());
            partnerConnects.add(wrapper);
        }

        return partnerConnects;
    }


    // ------------------------ misc ------------------------


    private SfAttachment getAttachment(String id) {
        String query = format("SELECT %s FROM Attachment WHERE Id = '%s'", ATTACHMENT_FIELDS, id);
        return retrieveAttachment(query);
    }

    private SfAttachment retrieveAttachment(String query) {
        SObject sObject = retrieveOne(query);
        return sObject == null ? null : converter.convertAttachment(sObject);
    }


    String createAttachment(SfAttachment attachment) {
        SObject sObject = converter.convert(attachment);
        return create(sObject, attachment);
    }

    public String createAttachment(String parentId, String name, byte[] content) {
        SfAttachment attachment = new SfAttachment();
        attachment.setParentId(parentId);
        attachment.setName(name);
        attachment.setBody(content);
        return createAttachment(attachment);
    }

    public void createAttachment(String objectId, String filename, String data) {
        if (data != null) {
            byte[] content = data.getBytes(UTF8);
            createAttachment(objectId, filename, content);
        }
    }

    private String updateAttachment(SfAttachment attachment) {
        SObject sObject = converter.convert(attachment);
        return update(sObject, attachment);
    }

    public String createEmailMessage(SfEmail sfEmail) {
        SObject sObject = converter.convert(sfEmail);
        return create(sObject, sfEmail);
    }


    // ------------------------ CRUD ------------------------

    public List<String> delete(String[] ids) {
        List<String> result = dataSource.delete(ids);
        LOGGER.info("Objects deleted from salesforce " + result);
        return result;
    }

    public List<String> delete(List<String> ids) {
        return delete(ids.toArray(new String[]{}));
    }

    private String delete(String id) {
        id = dataSource.delete(id);
        LOGGER.info("Objects deleted from salesforce " + id);
        return id;
    }

    private String create(SObject sObject, BaseSalesforceObject object) {
        String id = dataSource.create(sObject);
        object.setId(id);
        LOGGER.info("Created " + object);
        return id;
    }

    private String update(SObject sObject, BaseSalesforceObject object) {
        String id = dataSource.update(sObject);
        LOGGER.info("Updated " + object);
        return id;
    }

    private List<SObject> retrieveAll(String query) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(query);
        }
        return dataSource.retrieveAll(query);
    }

    private SObject retrieveOne(String query) {
        List<SObject> list = retrieveAll(query);
        return list.isEmpty() ? null : list.get(0);
    }

}