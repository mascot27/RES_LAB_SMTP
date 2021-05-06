package mail;

import java.io.IOException;

/**
 * define operation need to be supported by implementations of the smtp client
 * is responsible to communicate with the smtp server
 */
public interface IMailClientService {

    /**
     * Send the mail
     * @param mail mail to send
     */
    void sendMail(MailModel mail);
}
