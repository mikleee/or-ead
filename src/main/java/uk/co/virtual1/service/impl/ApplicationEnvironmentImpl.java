package uk.co.virtual1.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import uk.co.virtual1.service.ApplicationEnvironment;
import uk.co.virtual1.service.EnvironmentKey;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Mikhail Tkachenko created on 04.05.16 10:52
 */
@Service
public class ApplicationEnvironmentImpl implements ApplicationEnvironment {
    private final static Logger LOGGER = Logger.getLogger(ApplicationEnvironmentImpl.class);
    private final static Map<EnvironmentKey, String> environment = new LinkedHashMap<>();

    @Autowired
    private ApplicationContext applicationContext;


    @Override
    public String get(EnvironmentKey key) {
        return environment.get(key);
    }

    @Override
    public Integer getInt(EnvironmentKey key) {
        String s = get(key);
        return getInt(s, key.getKey());
    }

    @Override
    public Boolean getBoolean(EnvironmentKey key) {
        String s = get(key);
        return getBoolean(s, key.getKey());
    }


    private Integer getInt(String s, String key) {
        try {
            return Integer.valueOf(s);
        } catch (NumberFormatException e) {
            throw new RuntimeException(String.format("%s global application property should be integer but found: [%s]", key, s));
        }
    }

    private Boolean getBoolean(String s, String key) {
        if ("true".equals(s)) {
            return true;
        } else if ("false".equals(s)) {
            return false;
        } else {
            throw new RuntimeException(String.format("%s global application property should be boolean but found: [%s]", key, s));
        }
    }

    @PostConstruct
    private void init() throws Exception {
        Map<String, String> staticReport = initEnvironment();
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(report(staticReport));
        }
    }

    private Map<String, String> initEnvironment() {
        Map<String, String> report = new LinkedHashMap<>();
        for (EnvironmentKey key : EnvironmentKey.values()) {
            String value = registerProperty(key);
            report.put(key.getKey(), value);
        }

        validateEnvironment();
        return report;
    }

    private String registerProperty(EnvironmentKey key) {
        String s = applicationContext.getEnvironment().getProperty(key.getKey());
        if (s == null) {
            s = key.getDefaultValue();
        }
        environment.put(key, s);
        return s;
    }

    private void validateEnvironment() {
        for (EnvironmentKey key : environment.keySet()) {
            key.validate(get(key));
        }
    }

    private String report(Map<String, String> data) {
        final String lineDelimiter = "\n========================";
        final String identDelimiter = "\n| ";
        StringBuilder s = new StringBuilder(lineDelimiter);

        for (Map.Entry<String, String> item : data.entrySet()) {
            s.append(identDelimiter).append(item.getKey()).append(" = ").append(item.getValue());
        }
        s.append(lineDelimiter);
        return s.toString();
    }

    private void report(Map<String, String> staticReport, Map<String, String> dynamicReport) {
        StringBuilder report = new StringBuilder("\n")
                .append("Static application environment initialized successfully:")
                .append(report(staticReport))
                .append("\n\n")
                .append("Dynamic application configuration initialized successfully:")
                .append(report(dynamicReport));
        LOGGER.info(report);
    }


}
