package uk.co.virtual1.service;

/**
 * @author Mikhail Tkachenko created on 04.05.16 10:52
 */
public interface ApplicationEnvironment {

    String get(EnvironmentKey key);

    Integer getInt(EnvironmentKey key);

    Boolean getBoolean(EnvironmentKey key);

}
