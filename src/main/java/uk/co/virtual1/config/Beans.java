package uk.co.virtual1.config;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import uk.co.virtual1.salesforcebox.SalesforceService;
import uk.co.virtual1.service.ApplicationEnvironment;
import uk.co.virtual1.service.EnvironmentKey;


/**
 * @author Mikhail Tkachenko created on 30.04.16 11:50
 */
@Configurable
public class Beans {
    @Autowired
    private ApplicationEnvironment environment;

    @Bean
    public JSONSerializer jsonSerializer() {
        return new JSONSerializer().exclude("*.class");
    }

    @Bean
    @Scope("prototype")
    public <T> JSONDeserializer<T> jsonDeserializer() {
        return new JSONDeserializer<>();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
    }

    @Bean
    public SalesforceService salesforceService() {
        return new SalesforceService(
                environment.get(EnvironmentKey.SF_USER_NAME),
                environment.get(EnvironmentKey.SF_PASSWORD),
                environment.get(EnvironmentKey.SF_TOKEN),
                environment.getBoolean(EnvironmentKey.SF_SANDBOX),
                environment.get(EnvironmentKey.SF_CLIENT_IDENTIFIER)
        );
    }
}
