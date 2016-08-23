package uk.co.virtual1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import uk.co.virtual1.controller.CommonAttributesFilter;

import javax.servlet.DispatcherType;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Mikhail Tkachenko created on 01.05.2016
 */
@EnableWebMvc
@Configuration
public class Web extends WebMvcConfigurerAdapter {
    public final static String[] STATIC_RESOURCES_LOCATIONS = {
            "**/*.js",
            "**/*.css",
            "**/*.map",
            "**/*.eot",
            "**/*.svg",
            "**/*.ttf",
            "**/*.woff",
            "**/*.woff2",
            "**/*.png",
            "**/*.ico",
            "**/*.gif"
    };

    private final static Set<String> COMMON_ATTRIBUTES_FILTER_URLS = new HashSet<String>() {{
        add("/*");
    }};

    @Autowired
    private CommonAttributesFilter commonAttributesFilter;

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp().prefix("/web/page/");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(STATIC_RESOURCES_LOCATIONS).addResourceLocations("classpath:/web/").addResourceLocations("classpath:/");
    }

    @Bean
    public FilterRegistrationBean myFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(commonAttributesFilter);
        registration.setUrlPatterns(COMMON_ATTRIBUTES_FILTER_URLS);
        registration.setOrder(Integer.MAX_VALUE);
        return registration;
    }

}