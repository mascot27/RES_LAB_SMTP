package config;

/**
 * configuration model for the application
 */
public class ConfigurationModel {
    public int SmtpServerPort;
    public String SmtpServerAddress;

    public void set(String s, int i){
        SmtpServerAddress = s;
        SmtpServerPort = i;
    }
}
