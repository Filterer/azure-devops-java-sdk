package org.azd;

import org.azd.exceptions.AzDException;
import org.azd.exceptions.ConnectionException;
import org.azd.helpers.JsonMapper;
import org.azd.interfaces.AzDClient;
import org.azd.interfaces.CoreDetails;
import org.azd.interfaces.ServiceHooksDetails;
import org.azd.utils.AzDClientApi;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.LinkedHashMap;

public class ServiceHooksApiTest {
    private static final JsonMapper MAPPER = new JsonMapper();
    private static AzDClient webApi;
    private static ServiceHooksDetails s;
    private static CoreDetails c;

    @Before
    public void init() throws AzDException {
        String dir = System.getProperty("user.dir");
        File file = new File(dir + "/src/test/java/org/azd/_unitTest.json");
        MockParameters m = MAPPER.mapJsonFromFile(file, MockParameters.class);
        String organization = m.getO();
        String token = m.getT();
        String project = m.getP();
        webApi = new AzDClientApi(organization, project, token);
        c = webApi.getCoreApi();
        s = webApi.getServiceHooksApi();
    }

    @Test
    public void shouldCreateASubscription() throws ConnectionException, AzDException {
        var projectId = c.getProject("azure-devops-java-sdk");

        var pI = new LinkedHashMap<String, Object>(){{
            put("buildStatus", "Failed");
            put("definitionName", "Demo-CI");
            put("projectId", projectId.getId());
        }};

        var cI = new LinkedHashMap<String, Object>(){{
            put("url", "https://mywebsite/api/webhook");
        }};

        var res = s.createSubscription("tfs", "build.complete", "1.0-preview.1", "webHooks",
                "httpRequest", pI, cI);

        res.getId();
    }

    @Test
    public void shouldGetASubscription() throws ConnectionException, AzDException {
        var subscriptions = s.getSubscriptions();
        s.getSubscription(subscriptions.getSubscriptions().stream().findFirst().get().getId());
    }

    @Test
    public void shouldGetSubscriptions() throws ConnectionException, AzDException {
        s.getSubscriptions();
    }

    @Test
    public void shouldDeleteASubscription() throws ConnectionException, AzDException {
        var subscriptions = s.getSubscriptions();
        s.deleteSubscription(subscriptions.getSubscriptions().stream().findFirst().get().getId());
    }
}
