package config;

public class ConfigurationService implements IConfigurationService {

    @Override
    public ConfigurationModel GetConfiguration() {
        var config = new ConfigurationModel();
        config.SmtpServerPort = 2525;
        config.SmtpServerAddress = "localhost";

        return config;
    }
}
