import prank.*;


public class prankApp {

    private static final String CONFIG_FILE = "config/config.properties";
    private static final String MESSAGE_FILE = "config/message.utf8";
    private static final String VICTIMS_FILE = "config/victims.utf8";

    public static void main(String[] args)
    {

        prankGenerator prank = new prankGenerator(VICTIMS_FILE, MESSAGE_FILE, CONFIG_FILE);
        prank.activate();

    }
}
