package mail;

import prank.Person;

import java.util.ArrayList;
import java.util.List;

public class MailModel {

    public Person sender;
    public Person recever;
    public String subject;
    public String body;

    public MailModel(Person semder, Person recever, String subject, String body){
        this.sender = semder;
        this.recever = recever;
        this.subject = subject;
        this.body = body;
    }

    public Person getSender(){
        return sender;
    }

    public Person getRecever(){
        return recever;
    }

    public String getBody() {
        return body;
    }

    public String getSubject() {
        return subject;
    }
}
