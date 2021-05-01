package config;

import java.io.FileInputStream;
import java.util.Scanner;

public class readMessage {

    String listMessage = "";

    public readMessage(FileInputStream file) {
        readAll(file);
    }

    public void readAll(FileInputStream vic){
        Scanner scanner = new Scanner(vic);

        //renvoie true s'il y a une autre ligne à lire
        while(scanner.hasNextLine())
        {
            String s = scanner.nextLine();
            String[] arrOfStr = s.split("\n");
            for(String line : arrOfStr){
                listMessage += line + "\n";
            }
        }
        scanner.close();

    }

    public String getListMessage() {
        return listMessage;
    }

}
