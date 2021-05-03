package mail;

import config.ConfigurationModel;
import mail.IMailClientService;
import mail.MailModel;

/**
 * concrete implementatation of our smtp client
 */
public class SmtpClientService implements IMailClientService {

    private final ConfigurationModel configuration;

    public SmtpClientService(ConfigurationModel config){
        configuration = config;
    }

    @Override
    public void sendMail(MailModel mailModel) {

    }
}
