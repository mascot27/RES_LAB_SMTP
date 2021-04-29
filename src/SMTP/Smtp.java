package SMTP;

import model.Mail;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class Smtp {

    String hostname;
    int port;

    public Smtp(String hostname, int port){
        this.hostname = hostname;
        this.port = port;

    }
    public void sendPrank(Mail mail){


        BufferedReader reader;
        PrintWriter writer;

        try (Socket socket = new Socket(hostname, port)) {

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),StandardCharsets.UTF_8),true);


            String line = reader.readLine();
            System.out.println(line);

            writer.println("EHLO "+ hostname + "\r" );
            System.out.println("EHLO "+ hostname);


            line = reader.readLine();
            System.out.println(line);
            line = reader.readLine();
            System.out.println(line);
            line = reader.readLine();
            System.out.println(line);

            writer.println("MAIL FROM: " + mail.getSender().getAddressMail() + "\r" );

            line = reader.readLine();
            System.out.println(line);

            writer.println("RCPT TO: recever" + mail.getRecever().getAddressMail() + "\r" );

            line = reader.readLine();
            System.out.println(line);

            writer.println("DATA" + "\r" );
            System.out.println("DATA");

            line = reader.readLine();
            System.out.println(line);

            writer.println("From: " + mail.getSender().getAddressMail()+ "\r");
            writer.println("To: " + mail.getRecever().getAddressMail()+ "\r");
            writer.println("Subject: " + mail.getSubject()+ "\r\r" ); // une ligne pour séparé le corps du sujet
            writer.println(mail.getBody() + "\r" );
            writer.println("." + "\r" );
            System.out.println(".");

            line = reader.readLine();
            System.out.println(line);

            writer.println("quit" + "\r" );
            System.out.println("quit");

            line = reader.readLine();
            System.out.println(line);


        } catch (UnknownHostException ex) {

            System.out.println("Server not found: " + ex.getMessage());

        } catch (IOException ex) {

            System.out.println("I/O error: " + ex.getMessage());
        }

    }


}
