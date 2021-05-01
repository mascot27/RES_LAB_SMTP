package config;

import java.io.*;
import java.util.Scanner;

public class readConfig {

    String serverAddress;
    int serverPort;
    int nbGroup;

    public readConfig(FileInputStream configFile) {
        readAll(configFile);
    }

    public void readAll(FileInputStream cf){

                 // Le fichier d'entrée

            Scanner scanner = new Scanner(cf);

            //renvoie true s'il y a une autre ligne à lire
            while(scanner.hasNextLine())
            {
                String s = scanner.nextLine();
                String[] arrOfStr = s.split("=");
                if(arrOfStr[0].equals( "smtpServerAddress"))
                    this.serverAddress = arrOfStr[1];
                else if(arrOfStr[0].equals( "smtpServerPort"))
                  this.serverPort = Integer.parseInt(arrOfStr[1]);
                else if(arrOfStr[0].equals( "numberOfGroups"))
                  this.nbGroup = Integer.parseInt(arrOfStr[1]);

            }
            scanner.close();


    }

    public String getServerAddress() {
        return serverAddress;
    }
    public int getServerPort() {
        return serverPort;
    }
    public int getNbGroup() {
        return nbGroup;
    }






}
