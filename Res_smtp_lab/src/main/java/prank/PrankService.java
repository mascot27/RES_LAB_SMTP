package prank;

import mail.MailModel;

import java.util.ArrayList;
import java.util.List;

public class PrankService implements IPrankService {

    private final IPrankMessageService prankMessageService;

    public PrankService(IPrankMessageService prankMessageService) {
        this.prankMessageService = prankMessageService;
    }

    @Override
    public List<MailModel> getMailsForCampagne(CampagneModel campagne) {
        var mailsToSend = new ArrayList<MailModel>();


        // TODO: constitution des groups

        var mail = new MailModel();
        var message = prankMessageService.getPrankMessage();
        mail.Subject = message.Title;
        mail.Body = message.Text;


        mail.SenderEmail = "jean.luc@heig-vd.ch";
        mail.DestinatairesEmails.add("asdasd@heig-vd.ch");
        mail.DestinatairesEmails.add("asda123sd@heig-vd.ch");

        mailsToSend.add(mail);
        return mailsToSend;
    }
}
