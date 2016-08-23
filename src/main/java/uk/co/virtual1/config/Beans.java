package uk.co.virtual1.config;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;


/**
 * @author Mikhail Tkachenko created on 30.04.16 11:50
 */
@Configurable
public class Beans {

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
}
