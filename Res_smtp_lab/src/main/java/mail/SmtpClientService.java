package mail;

import config.ConfigurationModel;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Logger;

/**
 * concrete implementatation of our smtp client
 */
public class SmtpClientService implements IMailClientService {
    private final static Logger LOG = Logger.getLogger(SmtpClientService.class.getName());
    private final static String CL_RF = "\r\n";
    //private final static String LINE_RETURN = "\n";

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
            Socket socket = new Socket("127.0.0.1", 2525);
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

            System.out.println("connected to: " + in.readLine());
            // smtp sequence
            sendHello(in, out, "server");
            sendMailFrom(in, out, mailModel.SenderEmail);
            sendRcptTo(in, out, mailModel.DestinatairesEmails);

            sendData(in, out);

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

    private void sendData(BufferedReader in, BufferedWriter out) throws IOException {
        send(out, "DATA");
        String line = "";
        while ((line = in.readLine()) != null) {
            System.out.println("received: "  + line);
            if(line.startsWith("3")){ // wait for ok signal
                break;
            }
        }
    }

    private void sendRcptTo(BufferedReader in, BufferedWriter out, List<String> destinatairesEmails) throws IOException {
        String line;
        for (String email : destinatairesEmails) {
            send(out, "RCPT TO:<" + email + ">");
            while ((line = in.readLine()) != null) {
                System.out.println("received: "  + line);
                if(line.startsWith("250 ")){ // wait for ok signal
                    break;
                }
            }
        }
    }

    /**
     * send the MAIL FROM sequence
     * @param in bufferedReader of socket stream
     * @param out bufferedWriter of socket stream
     * @param senderEmail email address of the sender
     */
    private void sendMailFrom(BufferedReader in, BufferedWriter out, String senderEmail) throws IOException {
        String line;
        send(out, "MAIL FROM:<" + senderEmail + ">");
        while ((line = in.readLine()) != null) {
            System.out.println("received: "  + line);
            if(line.startsWith("250 ")){ // wait for ok signal
                break;
            }
        }
    }

    /**
     * initiate the EHLO sequence
     * @param in bufferedReader of socket stream
     * @param out bufferedWriter of socket stream
     */
    private void sendHello(BufferedReader in, BufferedWriter out, String Name) throws Exception {
        String line;
        send(out, "EHLO " + Name + CL_RF);

        while ((line = in.readLine()) != null) {
            System.out.println("received: "  + line);
            if(line.startsWith("250 ")){ // wait for ok signal
                break;
            }
        }
    }

    private void send(BufferedReader in, BufferedWriter out, String s) {
        try {
            out.write(s + CL_RF);
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
            out.write(s + CL_RF);
            out.flush();
            System.out.println(s);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
