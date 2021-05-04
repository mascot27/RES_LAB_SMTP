package mail;

import java.util.ArrayList;
import java.util.List;

public class MailModel {
    public String Subject;
    public String Body;
    public List<String> DestinatairesEmails = new ArrayList<>();
    public String SenderEmail;
}
