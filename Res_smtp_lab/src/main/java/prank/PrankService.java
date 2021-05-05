package prank;

import mail.MailModel;

import java.util.*;

public class PrankService implements IPrankService {

    private final IPrankMessageService prankMessageService;

    public PrankService(IPrankMessageService prankMessageService) {
        this.prankMessageService = prankMessageService;
    }


    @Override
    public List<MailModel> getMailsForCampagne(CampagneModel campagne) {
        var mailsToSend = new ArrayList<MailModel>();
        var numberOfGroups = campagne.Victims.size() / campagne.MinNumberOfVictimsByGroup;
        Collections.shuffle(campagne.Victims);

        var iterator = campagne.Victims.iterator();

        /*

        // construit le mail de prank pour chaque groupe
        for(int i = 0; i < numberOfGroups; i++){
            var mail = new MailModel();
            var message = prankMessageService.getPrankMessage();
            mail.Subject = message.Title;
            mail.Body = message.Text;

            if(!iterator.hasNext()){
                throw new RuntimeException("Il y a moins de victimes que de groupes");
            }

            mail.SenderEmail = iterator.next();
            mailsToSend.add(mail);
        }

        // ajout des destinataires
        int mailIndex = 0;
        while(iterator.hasNext()){
            var mail = mailsToSend.get(mailIndex);
            mail.DestinatairesEmails.add(iterator.next());
            mailIndex = (mailIndex + 1) % (numberOfGroups);
        }

        */


        return mailsToSend;
    }

}
