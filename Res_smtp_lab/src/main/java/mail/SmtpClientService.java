package mail;

import config.ConfigurationModel;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

/**
 * concrete implementatation of our smtp client
 */
public class SmtpClientService implements IMailClientService {
    private final static Logger LOG = Logger.getLogger(SmtpClientService.class.getName());
    private final static String LINE_RETURN = "\r\n";

    private final ConfigurationModel configuration;
    private BufferedReader in;
    private BufferedWriter out;

    public SmtpClientService(ConfigurationModel config) {
        configuration = config;
    }

    @Override
    public void sendMail(MailModel mailModel) {
        // see: https://www.javatpoint.com/socket-programming
        // https://www.codejava.net/java-se/networking/java-socket-client-examples-tcp-ip

        try {
            Socket socket = new Socket("127.0.0.1", 6000);
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

            var serverVersion = in.readLine();
            System.out.println("connected to: " + serverVersion);
            sendHello(in, out, "server");
            sendMailFrom(in, out, mailModel.SenderEmail);
            send(out, "MAIL FROM:<" + mailModel.SenderEmail + ">");

            for (String destinatairesEmail : mailModel.DestinatairesEmails) {
                send(out, "RCPT TO:<" + destinatairesEmail + ">");
            }
            send(out, "DATA");

            send(out, "Subject: In the place");
            send(out, "From: Mickey Mouse <Mickey.Mouse@Mickey.com>");
            // message body
            send(out, "I'm alive. Help me!");
            send(out, "\n.\n");
            send(out, "QUIT");
            socket.close();
            in.close();
            out.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * send the 
     * @param in
     * @param out
     * @param senderEmail
     */
    private void sendMailFrom(BufferedReader in, BufferedWriter out, String senderEmail) {
    }

    /**
     * initiate the EHLO sequence
     * @param in bufferedReader of socket stream
     * @param out bufferedWriter of socket stream
     */
    private void sendHello(BufferedReader in, BufferedWriter out, String Name) throws Exception {
        String line = null;
        send(out, "EHLO " + Name + LINE_RETURN);

        while ((line = in.readLine()) != null) {
            System.out.println("received: "  + line);
            if(line.startsWith("250 ")){
                break;
            }
        }
    }

    private void send(BufferedReader in, BufferedWriter out, String s) {
        try {
            out.write(s + LINE_RETURN);
            out.flush();
            System.out.println(s);
            s = in.readLine();
            System.out.println(s);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void send(BufferedWriter out, String s) {
        try {
            out.write(s + LINE_RETURN);
            out.flush();
            System.out.println(s);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
