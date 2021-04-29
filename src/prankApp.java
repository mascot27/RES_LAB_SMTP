

import java.io.*;
import java.nio.charset.StandardCharsets;

import prank.*;

import java.net.*;

public class prankApp {

    private static final String CONFIG_FILE = "config/config.properties";
    private static final String MESSAGE_FILE = "config/message.utf8";
    private static final String VICTIMS_FILE = "config/victims.utf8";

    public static void main(String[] args)
    {

        prankGenerator prank = new prankGenerator(VICTIMS_FILE, MESSAGE_FILE, CONFIG_FILE);


        //ESSAI DE COMMUNICTATION AVEC MOCK

        String hostname = "localhost";

        BufferedReader reader;
        PrintWriter writer;

        try (Socket socket = new Socket(hostname, 2525)) {

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),StandardCharsets.UTF_8),true);

            String line = reader.readLine();
            System.out.println(line);
            writer.println("EHLO localhost");

            line = reader.readLine();
            System.out.println(line);

            writer.println("quit");
            line = reader.readLine();
            System.out.println(line);

        } catch (UnknownHostException ex) {

            System.out.println("Server not found: " + ex.getMessage());

        } catch (IOException ex) {

            System.out.println("I/O error: " + ex.getMessage());
        }

    }
}
