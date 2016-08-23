package uk.co.virtual1.service;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;
import static uk.co.virtual1.service.EnviromentKeyPatterns.*;

/**
 * @author Mikhail Tkachenko created on 26.05.16 16:35
 */
public enum EnvironmentKey {
    REST_API_KEY("rest.api.key", STRING),

    SF_SEND_NOTIFICATIONS("sf.send.notifications", BOOLEAN),
    SF_USER_NAME("sf.userName", STRING),
    SF_PASSWORD("sf.password", STRING),
    SF_TOKEN("sf.token"),
    SF_SANDBOX("sf.sandbox", BOOLEAN);


    String key;
    Pattern pattern;
    String defaultValue;

    EnvironmentKey(String key) {
        this(key, null);
    }

    EnvironmentKey(String key, Pattern pattern) {
        this(key, pattern, null);
    }

    EnvironmentKey(String key, Pattern pattern, String defaultValue) {
        this.pattern = pattern;
        this.key = key;
        this.defaultValue = defaultValue;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void validate(String value) {
        if (StringUtils.isBlank(value)) {
            value = defaultValue;
        }

        if (value == null) {
            value = "";
        }

        if (pattern != null && !pattern.matcher(value).matches()) {
            throw new IllegalArgumentException("The " + key + " has invalid value, it must match to regexp:   " + pattern.pattern());
        }
    }

    public String getKey() {
        return key;
    }


    public EnvironmentKey fromKey(String key) {
        for (EnvironmentKey k : values()) {
            if (k.key.equals(key)) {
                return k;
            }
        }
        throw new IllegalArgumentException("EnvironmentKey " + key + " is not supported");
    }

}
