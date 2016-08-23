package uk.co.virtual1.test;

import flexjson.JSONDeserializer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.co.virtual1.Starter;
import uk.co.virtual1.config.Beans;
import uk.co.virtual1.model.json.JsonResponse;
import uk.co.virtual1.salesforce.SalesforceService;
import uk.co.virtual1.salesforce.object.Case;
import uk.co.virtual1.test.util.LocalHttpClient;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Starter.class, Beans.class})
@ActiveProfiles({"test"})
@WebIntegrationTest
public class Tests {
    private LocalHttpClient httpClient = new LocalHttpClient();

    @Autowired
    private SalesforceService salesforceService;

    @Test
    public void ping() {
        String json = httpClient.send("/ping");
        JsonResponse<String> response = new JSONDeserializer<JsonResponse<String>>().deserialize(json, JsonResponse.class);
        Assert.assertEquals(response.getStatus(), "ok");
    }

    @Test
    public void provision() {
        Case aCase = getCase();
        String json = httpClient.send("/provision", aCase);
        JsonResponse<String> response = new JSONDeserializer<JsonResponse<String>>().deserialize(json, JsonResponse.class);
        Assert.assertEquals(response.getStatus(), "ok");
    }

    private Case getCase() {
        Case aCase = salesforceService.getCase("500S00000080IQr");
        Assert.assertNotNull(aCase);
        Assert.assertNotNull(aCase.getAccess());
        return aCase;
    }

}
