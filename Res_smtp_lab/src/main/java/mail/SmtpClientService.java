package mail;

import config.Configuration;
import config.IConfigurationService;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;

/**
 * concrete implementatation of our smtp client
 */
public class SmtpClientService implements IMailClientService {

    private final static Logger LOG = Logger.getLogger(SmtpClientService.class.getName());
    private final static String CL_RF = "\r\n";

    private final Configuration configuration;
    private BufferedReader in;
    private BufferedWriter out;

    public SmtpClientService(Configuration config) {
        configuration = config;
    }

    public SmtpClientService(IConfigurationService configurationService) {
        configuration = configurationService.GetConfiguration();
    }

    @Override
    public void sendMail(Mail mail) {
        // see: https://www.javatpoint.com/socket-programming
        // https://www.codejava.net/java-se/networking/java-socket-client-examples-tcp-ip
        // https://tools.ietf.org/html/rfc5321#appendix-D
        try {
            Socket socket = new Socket(configuration.SmtpServerAddress, configuration.SmtpServerPort);
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

            System.out.println("connected to: " + in.readLine());
            // smtp sequence
            sendHello(in, out, "server");

            sendMailFrom(in, out, mail.Sender);
            sendRcptTo(in, out, mail.Recipients);
            sendData(in, out);
            send(out, "From: " + mail.Sender);
            // php: mail($to, '=?utf-8?B?'.base64_encode($subject).'?=', $message, $headers)
            send(out, "Subject: =?utf-8?B?" + Base64.getEncoder().encodeToString(mail.Subject.getBytes(StandardCharsets.UTF_8)) + "?=");
            // set header Content-Type: text/plain; charset=utf-8
            send(out, "Content-Type: text/plain; charset=utf-8");
            send(out, "To: " + String.join(",", mail.Recipients)+ CL_RF);

            send(out, mail.Body);
            send(in, out, CL_RF + "." + CL_RF);
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

    /**
     * send RCPT TO sequence
     * @param in bufferedReader of socket stream
     * @param out bufferedWriter of socket stream
     * @param recipients recipients addresses
     */
    private void sendRcptTo(BufferedReader in, BufferedWriter out, List<String> recipients) throws IOException {
        String line;
        for (String email : recipients) {
            send(out, "RCPT TO:<" + email + ">");
            while ((line = in.readLine()) != null) {
                System.out.println("received: "  + line);
                if(line.startsWith("250 ")){ // wait for ok signal
                    break;
                }
                // TODO: error handlingx
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
