import config.ConfigurationFileService;
import config.IConfigurationService;
import mail.IMailClientService;
import mail.MailModel;
import mail.SmtpClientService;
import prankCampagne.IPrankService;
import prankCampagne.MailGenerator;
import prankCampagne.PrankService;
import prankMessage.IPrankMessageProviderService;
import prankMessage.PrankMessageService;
import readFiles.ReadConfig;
import victims.IVictimsProviderService;
import victims.VictimsFilesProviderService;

import java.util.ArrayList;
import java.util.List;

public class PrankApplication {

    private static final String CONFIG_FILE = "config/config.properties";
    private static final String MESSAGE_FILE = "config/message.utf8";
    private static final String VICTIMS_FILE = "config/victims.utf8";

    private static List<MailModel> listeMailToSend = new ArrayList<>();

    public static void main(String[] args) {

        IVictimsProviderService victimsProviderService = new VictimsFilesProviderService(VICTIMS_FILE);
        IConfigurationService configurationService = new ConfigurationFileService(CONFIG_FILE);

        var config = configurationService.GetConfiguration();

        IPrankMessageProviderService prankMessageProviderService = new PrankMessageService();
        IPrankService prankService = new PrankService(prankMessageProviderService, victimsProviderService);

        IMailClientService mailClientService = new SmtpClientService(config);

        List<MailModel> forgedMails = new ArrayList<>();
        forgedMails = prankService.getMailsForCampagne();

        //lecture de tout les fichiers et stockage des données nécessaire
        MailGenerator Mg = new MailGenerator(VICTIMS_FILE, MESSAGE_FILE, config.nbGroups);

        Mg.FillAllMailToSend(listeMailToSend);


        var config = configurationReaderService.GetConfiguration();


        IMailClientService smtpClientService = new SmtpClientService(config);


        //envoie de tout les mail crées
        for(MailModel mail : listeMailToSend){
            smtpClientService.sendMail(mail);
        }

        /*
        // 0) initialize services
        IConfigurationService configurationReaderService = new ConfigurationService();
        IPrankMessageService prankMessageService = new PrankMessageService();

        IPrankService prankService = new PrankService(prankMessageService);

        var config = configurationReaderService.GetConfiguration();
        IMailClientService smtpClientService = new SmtpClientService(config);

        smtpClientService.sendMail(listeMailToSend.get(0));

        // 1) initialize campagne victims
        CampagneModel campagne = new CampagneModel();
        campagne.MinNumberOfVictimsByGroup = 3;
        campagne.Victims.add("corentin.zeller@heig-vd.ch");
        campagne.Victims.add("donald.duck@heig-vd.ch");
        campagne.Victims.add("fred.dupont@heig-vd.ch");


        // 2) send it to the moon
        var mails = prankService.getMailsForCampagne(campagne);

        for (MailModel mail : mails) {
            System.out.println("DEBUT");
            smtpClientService.sendMail(mail);
        }
        */

    }
}
