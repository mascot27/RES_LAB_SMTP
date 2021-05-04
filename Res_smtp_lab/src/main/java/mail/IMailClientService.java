package mail;

import java.io.IOException;

/**
 * define operation need to be supported by implementations of the smtp client
 * is responsible to communicate with the smtp server
 */
public interface IMailClientService {
    void sendMail(MailModel mailModel);
}
