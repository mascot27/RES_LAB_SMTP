package model;

public class Mail {

    private Person sender;
    private Person recever;
    private String subject;
    private String body;

    public Mail(Person semder, Person recever, String subject, String body){
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
