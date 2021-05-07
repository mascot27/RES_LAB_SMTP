package prankMessage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PrankMessageService implements IPrankMessageProviderService {

    List<Message> messages;
    Random rand = new Random();

    @Override
    public Message getPrankMessage() {
        return messages.get(rand.nextInt(messages.size()));
    }

    public PrankMessageService(String filePath){
        try {
            messages = readMessageFile(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Message> readMessageFile(String filename) throws IOException {
        List<Message> listMessage = new ArrayList<>();

        FileInputStream file = new FileInputStream(filename);
        Scanner scanner = new Scanner(file);
        //renvoie true s'il y a une autre ligne à lire
        Message currentMsg = new Message();
        currentMsg.Text = "";
        while(scanner.hasNextLine())
        {
            String s = scanner.nextLine();
            if(currentMsg.Title == null)
                currentMsg.Title = s;
            else if(!s.equals("--"))
                currentMsg.Text += s + '\n';
            else {
                listMessage.add(currentMsg);
                currentMsg = new Message();
                currentMsg.Text = "";
            }
        }
        listMessage.add(currentMsg);
        scanner.close();
        file.close();
        return listMessage;
    }
}
