package uk.co.virtual1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import uk.co.virtual1.config.Beans;
import uk.co.virtual1.config.Security;
import uk.co.virtual1.config.Web;
import uk.co.virtual1.config.WsConfig;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class Starter {

    public static void main(String[] args) {
        SpringApplication.run(new Class<?>[]{Starter.class, Beans.class, Security.class, Web.class, WsConfig.class}, args);
    }

}