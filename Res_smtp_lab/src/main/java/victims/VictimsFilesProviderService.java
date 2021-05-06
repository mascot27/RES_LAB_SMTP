package victims;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class VictimsFilesProviderService implements IVictimsProviderService{

    private List<String> victims;

    public VictimsFilesProviderService(String victimsFile){
        try {
            victims = readVictimFile(victimsFile);
        } catch (IOException e) {
            e.printStackTrace();
            // TODO: use logger
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
            victims = Arrays.asList(s.split("\n"));
        }
        scanner.close();
        file.close();
        return victims;
    }
}
