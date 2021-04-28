

import java.io.*;
import java.util.Scanner;
import readFile.*;

public class main {

    private static final String CONFIG_FILE = "config/config.properties";

    static String serverAddress;
    static int serverPort;
    static int nbGroup;

    public static void main(String[] args)
    {

        //récuperation addresse serveur, port et nombre de groupe dans le fichier config

        try
        {
            // Le fichier d'entrée
            FileInputStream file = new FileInputStream(CONFIG_FILE);
            Scanner scanner = new Scanner(file);

            //renvoie true s'il y a une autre ligne à lire
            while(scanner.hasNextLine())
            {
                String s = scanner.nextLine();
                String[] arrOfStr = s.split("=");
                if(arrOfStr[0].equals( "smtpServerAddress"))
                System.out.println(arrOfStr[1]);
                else if(arrOfStr[0].equals( "smtpServerPort"))
                    System.out.println(arrOfStr[1]);
                else if(arrOfStr[0].equals( "numberOfGroups"))
                    System.out.println(arrOfStr[1]);

            }
            scanner.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        //création des messages


    }
}
