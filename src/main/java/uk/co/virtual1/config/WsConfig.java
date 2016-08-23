package uk.co.virtual1.config;

import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * @author Mikhail Tkachenko created on 07.04.16 17:53
 */
@EnableWs
@Configuration
public class WsConfig extends WsConfigurerAdapter {
//    @Autowired
//    private AuthenticationWsInterceptor authenticationWsInterceptor;
//
//    @Override
//    public void addInterceptors(List<EndpointInterceptor> interceptors) {
//        interceptors.add(authenticationWsInterceptor);
//    }

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/api/soap/*");
    }

    @Bean(name = "provisioning")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema cloudSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("ttb-provisioning");
        wsdl11Definition.setLocationUri("/api/soap");
        wsdl11Definition.setTargetNamespace("http://upshot.co.uk/model/xml");
        wsdl11Definition.setSchema(cloudSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema cloudSchema() {
        return new SimpleXsdSchema(new ClassPathResource("/wsdl/ws-api.xsd"));
    }
}


