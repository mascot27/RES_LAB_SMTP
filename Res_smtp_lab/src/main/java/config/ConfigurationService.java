package config;

public class ConfigurationService implements IConfigurationService {

    @Override
    public ConfigurationModel GetConfiguration() {
        var config = new ConfigurationModel();
        // TODO: get value from file
        config.SmtpServerPort = 2525;
        config.SmtpServerAddress = "localhost";

        return config;
    }
}
