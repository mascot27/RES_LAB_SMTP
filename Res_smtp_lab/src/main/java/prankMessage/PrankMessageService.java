package prankMessage;

import java.util.List;
import java.util.Scanner;

public class PrankMessageService implements IPrankMessageProviderService {

    List<Message> messages;

    @Override
    public Message getPrankMessage() {
        var msg = new Message();

        // TODO: implement me

        return msg;
    }

    public PrankMessageService(String filePath){
        messages = readMessageFile(filePath);


    }

    private List<Message> readMessageFile(String filename){

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
}
