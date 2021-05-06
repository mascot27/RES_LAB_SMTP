package prankCampagne;

import mail.MailModel;
import prankMessage.Message;
import readFiles.MessageFileReader;
import readFiles.VictimsFileReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class MailGenerator {

    private String emailsOfVictims;
    private String messagesAvailable;
    private int nbGroup;

    public MailGenerator(String victimFile, String messageFile, int nbGroup) {

        this.nbGroup = nbGroup;

        //ADDRESSE VICTIM
        try {
            // Le fichier d'entrée
            FileInputStream file = new FileInputStream(victimFile);
            VictimsFileReader victimsFileReader = new VictimsFileReader(file);
            emailsOfVictims = victimsFileReader.getListVictims();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //MESSAGE
        try {
            // Le fichier d'entrée
            FileInputStream file = new FileInputStream(messageFile);
            MessageFileReader messageFileReader = new MessageFileReader(file);
            messagesAvailable = messageFileReader.getListMessage();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void FillAllMailToSend(List<MailModel> listMailToSend){
        generator();
        createPrankMail(listMailToSend);
    }


    /*
    rempli les différent vecteur pour construire les mail par la suite
    */
    public void generator(){


        //remplissage du vector de message
        String[] temp = messagesAvailable.split("--\n");
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
    public void createPrankMail(List<MailModel> listmailToSend){

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

