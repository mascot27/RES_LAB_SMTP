package config;

public class ConfigurationService implements IConfigurationService {

    ConfigurationModel config = new ConfigurationModel();
    @Override
    public ConfigurationModel GetConfiguration() {
        return config;
    }

    public ConfigurationService(String s, int i){
        config.SmtpServerPort = i;
        config.SmtpServerAddress = s;
    }

}
