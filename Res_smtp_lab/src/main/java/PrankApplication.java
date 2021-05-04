import config.ConfigurationService;
import config.IConfigurationService;
import mail.IMailClientService;
import mail.MailModel;
import mail.SmtpClientService;
import prank.*;

public class PrankApplication {

    public static void main(String[] args) {

        // 0) initialize services
        IConfigurationService configurationReaderService = new ConfigurationService();
        IPrankMessageService prankMessageService = new PrankMessageService();

        IPrankService prankService = new PrankService(prankMessageService);

        var config = configurationReaderService.GetConfiguration();
        IMailClientService smtpClientService = new SmtpClientService(config);

        // 1) initialize campagne victims
        CampagneModel campagne = new CampagneModel();
        campagne.MinNumberOfVictimsByGroup = 3;
        campagne.Victims.add("corentin.zeller@heig-vd.ch");
        campagne.Victims.add("donald.duck@heig-vd.ch");
        campagne.Victims.add("fred.dupont@heig-vd.ch");

        // 2) send it to the moon
        var mails = prankService.getMailsForCampagne(campagne);
        for (MailModel mail : mails) {
            smtpClientService.sendMail(mail);
        }

    }
}
