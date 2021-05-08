package prankCampagne;

import mail.Mail;

import java.util.List;


/**
 * Define interraction with the prank campagne service
 */
public interface IPrankService {


    /**
     * Provide a list of generated prank mail ready to be send
     * @return a list of forged emails
     */
    List<Mail> getMailsForCampagne();

}
