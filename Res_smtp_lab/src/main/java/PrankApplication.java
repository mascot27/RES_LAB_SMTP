import config.ConfigurationFileService;
import config.IConfigurationService;
import mail.IMailClientService;
import mail.Mail;
import mail.SmtpClientService;
import prankCampagne.IPrankService;
import prankCampagne.PrankService;
import prankMessage.IPrankMessageProviderService;
import prankMessage.PrankMessageService;
import victims.IVictimsProviderService;
import victims.VictimsFilesProviderService;

import java.util.List;

public class PrankApplication {

    private static final String CONFIG_FILE = "config/config.properties";
    private static final String MESSAGE_FILE = "config/message.utf8";
    private static final String VICTIMS_FILE = "config/victims.utf8";


    public static void main(String[] args) {

        IVictimsProviderService victimsProviderService = new VictimsFilesProviderService(VICTIMS_FILE);
        IConfigurationService configurationService = new ConfigurationFileService(CONFIG_FILE);
        IPrankMessageProviderService prankMessageProviderService = new PrankMessageService(MESSAGE_FILE);

        IMailClientService mailClientService = new SmtpClientService(configurationService);

        IPrankService prankService = new PrankService(prankMessageProviderService, victimsProviderService, configurationService);
        List<Mail> mailsForCampagne = prankService.getMailsForCampagne();

        for(var mail : mailsForCampagne){
            mailClientService.sendMail(mail);
        }
    }
}
