package prank;

import mail.MailModel;
import readFiles.ReadMessage;
import readFiles.ReadVictims;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class MailGenerator {

    String addressVictims;
    String message;
    int nbGroup;

    Vector<Person> listPerson = new Vector<>();
    Vector<Message> listMessage = new Vector<>();
    Vector<Group> listGroup = new Vector<>();

    public MailGenerator(String victimFile, String messageFile, int nbGroup) {

        this.nbGroup = nbGroup;

        //ADDRESSE VICTIM
        try {
            // Le fichier d'entrée
            FileInputStream file = new FileInputStream(victimFile);
            ReadVictims vict = new ReadVictims(file);
            addressVictims = vict.getListVictims();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //MESSAGE
        try {
            // Le fichier d'entrée
            FileInputStream file = new FileInputStream(messageFile);
            ReadMessage mess = new ReadMessage(file);
            message = mess.getListMessage();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void FillAllMailToSend(Vector<MailModel> listMailToSend){
        generator();
        createPrankMail(listMailToSend);
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
        String[] temp = message.split("--\n");
        for(String s : temp){
            String[] seperateSubject = s.split("\\R", 2);
            String subjet = seperateSubject[0];
            String body = seperateSubject[1];
            listMessage.add(new Message(body, subjet));
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
    public void createPrankMail(Vector<MailModel> listmailToSend){

        String subjet = "Salut!";

        //remplissage du vector de mail
        for(int i = 0; i < listGroup.size(); i ++){
            //les personne d'un groupe auront le meme corps de mesage
            Message prankMsg = randomMessage();
            for(int j = 0; j < listGroup.get(i).getSize() - 1; j++) {
                MailModel prank = new MailModel(listGroup.get(i).getPerson(0), listGroup.get(i).getPerson(1+j), prankMsg.Title, prankMsg.Text);
                listmailToSend.add(prank);
            }
        }
    }

    //prends un message aléatoirement parmis la liste
    public Message randomMessage(){
        int  index= (int)(Math.random() * listMessage.size());

        return listMessage.get(index);
    }
}

