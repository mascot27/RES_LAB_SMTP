package prankCampagne;

import config.IConfigurationService;
import mail.Mail;
import prankMessage.IPrankMessageProviderService;
import victims.IVictimsProviderService;

import java.util.*;

public class PrankService implements IPrankService {

    private final IPrankMessageProviderService prankMessageService;
    private final IVictimsProviderService victimsProviderService;
    private final IConfigurationService configurationService;

    public PrankService(
            IPrankMessageProviderService prankMessageService,
            IVictimsProviderService victimsProviderService,
            IConfigurationService configurationService) {
        this.prankMessageService = prankMessageService;
        this.victimsProviderService = victimsProviderService;
        this.configurationService = configurationService;
    }


    @Override
    public List<Mail> getMailsForCampagne() {
        var mailsToSend = new ArrayList<Mail>();

        var victims = victimsProviderService.GetVictims();
        List<VictimGroup> groups = makeGroups(configurationService.GetConfiguration().nbGroups, victims);

        for (VictimGroup group : groups) {
            var mail = new Mail();
            var message = prankMessageService.getPrankMessage();
            mail.Subject = message.Title;
            mail.Body = message.Text;
            mail.Sender = group.Sender;
            mail.Recipients = group.Recipients;

            mailsToSend.add(mail);
        }

        return mailsToSend;
    }

    private List<VictimGroup> makeGroups(int nbGroups, List<String> victims){
        List<VictimGroup> groups = new ArrayList<>();

        Collections.shuffle(victims);

        var victimsIterator = victims.iterator();

        for(int i = 0; i < nbGroups; i++){
            var newVictimGroup = new VictimGroup();
            newVictimGroup.Sender = victimsIterator.next();
            groups.add(newVictimGroup);
        }

        var groupsIterator = groups.iterator();

        while(victimsIterator.hasNext()){
            if(!groupsIterator.hasNext()){
                groupsIterator = groups.iterator();
            }
            groupsIterator.next().Recipients.add(victimsIterator.next());
        }

        return groups;
    }

    private class VictimGroup{
        public String Sender;
        public List<String> Recipients = new ArrayList<>();
    }

}
