import config.ConfigurationFileService;
import config.IConfigurationService;
import mail.IMailClientService;
import mail.MailModel;
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

        IPrankService prankService = new PrankService(prankMessageProviderService, victimsProviderService);
        List<MailModel> mailsForCampagne = prankService.getMailsForCampagne();

        //envoie de tout les mail crées
        for(var mail : mailsForCampagne){
            mailClientService.sendMail(mail);
        }
    }
}
