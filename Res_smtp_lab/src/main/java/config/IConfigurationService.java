package config;

/**
 * define what configuration service should provide
 */
public interface IConfigurationService {


    /**
     * Get the needed configuration: port, address
     */
    Configuration GetConfiguration();
}
