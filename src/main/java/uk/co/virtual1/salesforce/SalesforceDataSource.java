package uk.co.virtual1.salesforce;

import com.sforce.soap.partner.Connector;
import com.sforce.soap.partner.DeleteResult;
import com.sforce.soap.partner.DescribeSObjectResult;
import com.sforce.soap.partner.Error;
import com.sforce.soap.partner.Field;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.soap.partner.QueryResult;
import com.sforce.soap.partner.SaveResult;
import com.sforce.soap.partner.fault.ApiFault;
import com.sforce.soap.partner.sobject.SObject;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;
import com.sforce.ws.SessionRenewer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.virtual1.service.ApplicationEnvironment;
import uk.co.virtual1.service.EnvironmentKey;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Mikhail Tkachenko created on 11.08.16 10:21
 */
@Service
final class SalesforceDataSource {
    private final static Logger LOGGER = Logger.getLogger(SalesforceDataSource.class);

    @Autowired
    private ApplicationEnvironment environment;

    private SalesforceExceptionCallback exceptionHandler;

    private PartnerConnection connection;


    private final static class SessionRenewerImpl implements SessionRenewer {
        @Override
        public SessionRenewalHeader renewSession(ConnectorConfig connectorConfig) throws ConnectionException {
            LOGGER.trace("Salesforce session has been expired try to obtain a new one...");
            connectorConfig.setSessionId(null);
            PartnerConnection connection = Connector.newConnection(connectorConfig);
            SessionRenewalHeader header = new SessionRenewalHeader();
            header.name = new QName("urn:partner.soap.sforce.com", "SessionHeader");
            header.headerElement = connection.getSessionHeader();
            return header;
        }
    }

    PartnerConnection getConnection() {
        if (connection == null) {
            createConnection();
        }
        return connection;
    }

    private void createConnection() {
        try {
            ConnectorConfig config = createConnectorConfig();
            connection = Connector.newConnection(config);

            //Create metadata connection
            ConnectorConfig metadataConfig = new ConnectorConfig();
            metadataConfig.setSessionId(connection.getSessionHeader().getSessionId());

            // hack http://code.google.com/p/sfdc-wsc/issues/detail?id=37
            String metaurl = config.getServiceEndpoint();
            metaurl = metaurl.replace("Soap/u", "Soap/m");
            metadataConfig.setServiceEndpoint(metaurl);
        } catch (ApiFault e) {
            handleApiFault(e);
            throw new SalesforceException("Error on connecting to Salesforce.", e);
        } catch (ConnectionException e) {
            throw new SalesforceException("Error on connecting to Salesforce.", e);
        }
    }

    private ConnectorConfig createConnectorConfig() {
        ConnectorConfig config = new ConnectorConfig();
        if (environment.getBoolean(EnvironmentKey.SF_SANDBOX)) {
            config.setAuthEndpoint("https://test.salesforce.com/services/Soap/u/29.0");
            config.setServiceEndpoint("https://test.salesforce.com/services/Soap/u/29.0");
        }

        config.setUsername(environment.get(EnvironmentKey.SF_USER_NAME));
        config.setPassword(environment.get(EnvironmentKey.SF_PASSWORD) + environment.get(EnvironmentKey.SF_TOKEN));
        config.setSessionRenewer(new SessionRenewerImpl());
        return config;
    }


    List<SObject> retrieveAll(String queryString) {
        try {
            List<SObject> result = new ArrayList<>();
            QueryResult queryResult = getConnection().query(queryString);
            if (queryResult.getRecords() != null) {
                Collections.addAll(result, queryResult.getRecords());

                String queryLocator = queryResult.getQueryLocator();
                while (queryLocator != null) {
                    queryResult = getConnection().queryMore(queryLocator);
                    queryLocator = queryResult.getQueryLocator();
                    Collections.addAll(result, queryResult.getRecords());
                }
            }
            return result;
        } catch (ApiFault e) {
            handleApiFault(e);
            throw new SalesforceException("Error on retrieve all", e);
        } catch (ConnectionException e) {
            throw new SalesforceException("Error on retrieve all", e);
        }
    }


    String delete(String id) {
        try {
            DeleteResult result = getConnection().delete(new String[]{id})[0];
            validateResult(result);
            return result.getId();
        } catch (ApiFault e) {
            handleApiFault(e);
            throw new SalesforceException("Error on delete", e);
        } catch (ConnectionException e) {
            throw new SalesforceException("Error on delete", e);
        }
    }

    List<String> delete(String[] ids) {
        try {
            DeleteResult[] results = getConnection().delete(ids);
            List<String> deleted = new ArrayList<>();
            for (DeleteResult result : results) {
                validateResult(result);
                deleted.add(result.getId());
            }
            return deleted;
        } catch (ApiFault e) {
            handleApiFault(e);
            throw new SalesforceException("Error on delete", e);
        } catch (ConnectionException e) {
            throw new SalesforceException("Error on delete", e);
        }
    }


    String update(SObject sfObject) {
        try {
            SaveResult update = getConnection().update(new SObject[]{sfObject})[0];
            validateResult(update);
            return update.getId();
        } catch (ApiFault e) {
            handleApiFault(e);
            throw new SalesforceException("Error on update", e);
        } catch (ConnectionException e) {
            throw new SalesforceException("Error on update", e);
        }
    }

    List<String> update(SObject[] sfObjects) {
        try {
            List<String> ids = new ArrayList<>();
            SaveResult[] create = getConnection().update(sfObjects);
            for (SaveResult result : create) {
                validateResult(result);
                ids.add(result.getId());
            }
            return ids;
        } catch (ApiFault e) {
            handleApiFault(e);
            throw new SalesforceException("Error on update", e);
        } catch (ConnectionException e) {
            throw new SalesforceException("Error on update", e);
        }
    }

    String create(SObject sfObject) {
        try {
            SaveResult create = getConnection().create(new SObject[]{sfObject})[0];
            validateResult(create);
            return create.getId();
        } catch (ApiFault e) {
            handleApiFault(e);
            throw new SalesforceException("Error on create", e);
        } catch (ConnectionException e) {
            throw new SalesforceException("Error on create", e);
        }
    }

    List<String> create(SObject[] sfObjects) {
        try {
            List<String> ids = new ArrayList<>();
            SaveResult[] create = getConnection().create(sfObjects);
            for (SaveResult result : create) {
                validateResult(result);
                ids.add(result.getId());
            }
            return ids;
        } catch (ApiFault e) {
            handleApiFault(e);
            throw new SalesforceException("Error on create", e);
        } catch (ConnectionException e) {
            throw new SalesforceException("Error on create", e);
        }
    }

    Field[] describeSObject(String object) {
        try {
            DescribeSObjectResult describe = getConnection().describeSObject(object);
            return describe.getFields();
        } catch (ApiFault e) {
            handleApiFault(e);
            throw new SalesforceException("Error on describe object", e);
        } catch (ConnectionException e) {
            throw new SalesforceException("Error on describe object", e);
        }
    }

    private void validateResult(SaveResult result) {
        validateResult(result.getErrors());
    }

    private void validateResult(DeleteResult result) {
        validateResult(result.getErrors());
    }

    private void validateResult(Error[] errors) {
        if (errors.length > 0) {
            throw new SalesforceException(errors[0].getMessage());
        }
    }

    private void handleApiFault(ApiFault e) {
        if (exceptionHandler != null) {
            try {
                exceptionHandler.onError(e);
            } catch (Exception ignore) {
            }
        }
    }

}
