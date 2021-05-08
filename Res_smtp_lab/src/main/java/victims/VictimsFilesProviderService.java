package victims;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


/**
 * Concrete implementatation which will create a list of emails in the application and which
 * can be used to send them jokes.
 */
public class VictimsFilesProviderService implements IVictimsProviderService{

    private List<String> victims;

    public VictimsFilesProviderService(String victimsFile){
        try {
            victims = readVictimFile(victimsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> GetVictims() {
        return victims;
    }


    private List<String> readVictimFile(String filePath) throws IOException {
        List<String> victims = new ArrayList<>();
        FileInputStream file = new FileInputStream(filePath);
        Scanner scanner = new Scanner(file);

        //renvoie true s'il y a une autre ligne à lire
        while(scanner.hasNextLine())
        {
            String s = scanner.nextLine();
            String[] arrOfStr = s.split("\n");
            victims.addAll(Arrays.asList(arrOfStr));
        }
        scanner.close();
        file.close();
        return victims;
    }
}
