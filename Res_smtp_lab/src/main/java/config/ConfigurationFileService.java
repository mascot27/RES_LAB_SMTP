package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;


/**
 * filled the configuration with the config.properties file passed in parameter
 */
public class ConfigurationFileService implements IConfigurationService {

    private Configuration config;

    @Override
    public Configuration GetConfiguration() {
        return config;
    }

    public ConfigurationFileService(String configFilePath){
        try {
            config = ReadConfigFile(configFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            // TODO use logger
        }
    }

    private Configuration ReadConfigFile(String configFilePath) throws IOException {
        Configuration configuration = new Configuration();
        FileInputStream file = new FileInputStream(configFilePath);

        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine())
        {
            String s = scanner.nextLine();
            String[] arrOfStr = s.split("=");
            if(arrOfStr[0].equals( "smtpServerAddress"))
                configuration.SmtpServerAddress = arrOfStr[1];
            else if(arrOfStr[0].equals( "smtpServerPort"))
                configuration.SmtpServerPort = Integer.parseInt(arrOfStr[1]);
            else if(arrOfStr[0].equals( "numberOfGroups"))
                configuration.nbGroups = Integer.parseInt(arrOfStr[1]);
        }
        scanner.close();
        file.close();

        return configuration;
    }

}
