package readFiles;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class ReadConfig {

    String serverAddress;
    int serverPort;
    int nbGroup;

    public ReadConfig(String configProperties) {
        //CONFIG
        try
        {
            // Le fichier d'entrée
            FileInputStream file = new FileInputStream(configProperties);
            readAll(file);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
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
