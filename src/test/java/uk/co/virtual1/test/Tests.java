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
import uk.co.virtual1.salesforcebox.SalesforceService;
import uk.co.virtual1.salesforcebox.object.Access;
import uk.co.virtual1.salesforcebox.object.Case;
import uk.co.virtual1.test.util.LocalHttpClient;

import java.io.FileWriter;
import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Starter.class, Beans.class})
@ActiveProfiles({"test"})
@WebIntegrationTest
public class Tests {
    private LocalHttpClient httpClient = new LocalHttpClient();
    private JSONDeserializer<JsonResponse> deserializer = new JSONDeserializer<>();

    @Autowired
    private SalesforceService salesforceService;

    @Test
    public void ping() {
        String json = httpClient.send("/ping");
        JsonResponse<String> response = deserializer.use("body", String.class).deserialize(json, JsonResponse.class);
        Assert.assertEquals(response.getStatus(), "ok");
    }

    @Test
    public void provision() {
        Case aCase = getCase();
        JsonResponse<String> response = provision(aCase);
        Assert.assertEquals(response.getStatus(), "ok");
         write("temp-case-" + aCase.getId() + ".xml", response.getBody());
    }

    @Test
    public void createProvisionFiles() {
        String[] ids = {"500S00000080YAD"};
        for (String id : ids) {
            Case aCase = getCase(id);
            JsonResponse<String> response = provision(aCase);
            Assert.assertEquals(response.getStatus(), "ok");
            write("order-" + id + ".xml", response.getBody());
        }
    }

    @Test
    public void provisionWithOutPricingEntries() {
        Case aCase = getCase();
        aCase.getAccess().getPricingEntryList().clear();
        JsonResponse<String> response = provision(aCase);
        Assert.assertEquals(response.getStatus(), "error");
    }

    private JsonResponse<String> provision(Case aCase) {
        String json = httpClient.send("/provision", aCase);
        return deserializer.use("body", String.class).deserialize(json, JsonResponse.class);
    }

    private Case getCase(String id) {
        Case aCase = salesforceService.getCase(id);
        Assert.assertNotNull(aCase);
        Assert.assertNotNull(aCase.getAccess());
        Access access = salesforceService.getAccess(aCase.getAccess().getId());
        Assert.assertNotNull(access);
        Assert.assertNotEquals(access.getPricingEntryList().size(), 0);
        aCase.setAccess(access);
        return aCase;
    }

    private Case getCase() {
        return getCase("50023000001GzSe");
    }

    private void write(String file, String content) {
        try (FileWriter writer = new FileWriter("/home/misha/Work/Projects/or-ead/misc/" + file)) {
            writer.write(content);
        } catch (IOException ignore) {
        }
    }

}
