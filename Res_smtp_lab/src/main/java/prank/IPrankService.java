package prank;

import mail.MailModel;

import java.util.List;

public interface IPrankService {

    List<MailModel> getMailsForCampagne(CampagneModel campagneModel);

}
