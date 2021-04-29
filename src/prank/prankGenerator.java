package prank;

import SMTP.Smtp;
import config.*;

import model.Group;
import model.Mail;
import model.Message;
import model.Person;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class prankGenerator {

    String addressVictims;
    String message;
    String serverAddress;
    int serverPort;
    int nbGroup;
    Smtp smtp;

    Vector<Person> listPerson = new Vector<>();
    Vector<Message> listMessage = new Vector<>();
    Vector<Group> listGroup = new Vector<>();
    Vector<Mail> listMail = new Vector<>();

    public prankGenerator(String victimFile, String messageFile, String configProperties){

        //CONFIG
        try
        {
            // Le fichier d'entrée
            FileInputStream file = new FileInputStream(configProperties);
            readConfig conf = new readConfig(file);
            serverAddress = conf.getServerAddress();
            serverPort = conf.getServerPort();
            nbGroup = conf.getNbGroup();

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        //ADDRESSE VICTIM
        try
        {
            // Le fichier d'entrée
            FileInputStream file = new FileInputStream(victimFile);
            readVictims vict = new readVictims(file);
            addressVictims = vict.getListVictims();

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        //MESSAGE
        try
        {
            // Le fichier d'entrée
            FileInputStream file = new FileInputStream(messageFile);
            readMessage mess = new readMessage(file);
            message = mess.getListMessage();

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        smtp = new Smtp(serverAddress, serverPort);

    }

    /*
    rempli les différent vecteur pour construire les mail par la suite
     */
    public void generator(){

        Scanner scanner = new Scanner(addressVictims);

        //remplissage du vector de personne
        while(scanner.hasNextLine())
        {
            String s = scanner.nextLine();
            String[] arrOfStr = s.split("\n");
            for(String line : arrOfStr){
                listPerson.add(new Person(line));
            }
        }

        scanner.close();

        //remplissage du vector de message
        String[] temp = message.split("--");
        for(String s : temp){
            listMessage.add(new Message(s));
        }


        for(int i = 0 ; i < nbGroup; i++){
            listGroup.add(new Group());
        }

        //mélange de la liste de personne
        Collections.shuffle(listPerson);


        /*
        met les personnes dans les groupe
         */
        int nbPers = listPerson.size();
        for(int i = 0; i < nbGroup; i++) {
            int groupSize = nbPers / nbGroup;
            for (int j = i * groupSize ; j  < groupSize * (i + 1); j++) {
                listGroup.get(i).addPersonGroup(listPerson.get(j));
                //System.out.println("dans groupe " + i + " = personne" + j);
            }
        }

    }

    /*
    créer la liste des faux email avec tout le nécessaire à l'envoir sur un serveur SMTP
     */
    public void createPrankMail(){

        String subjet = "Salut!";

        //remplissage du vector de mail
        for(int i = 0; i < listGroup.size(); i ++){
            //les personne d'un groupe auront le meme corps de mesage
            String prankBody = randomMessage();
            for(int j = 0; j < listGroup.get(i).getSize() - 1; j++) {
                Mail prank = new Mail(listGroup.get(i).getPerson(0), listGroup.get(i).getPerson(1+j), subjet, prankBody);
                listMail.add(prank);
            }
        }
    }

    //prends un message aléatoirement parmis la liste
    public String randomMessage(){
        int  index= (int)(Math.random() * listMessage.size());

        return listMessage.get(index).getMsg();
    }


    public void activate() {
        generator();
        createPrankMail();
        sendAllPrank(smtp);
    }

    /*
    balance tout les mail sur mock
    */
    public void sendAllPrank(Smtp s){
        for(Mail m : listMail){
            s.sendPrank(m);
        }
    }
}
