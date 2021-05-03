package prank;

public class PrankMessageService implements IPrankMessageService {
    @Override
    public Message getPrankMessage() {
        var msg = new Message();
        msg.Text = "Visesnfs ndf jdfnksjbdf éè";
        msg.Text = "saluti èé ! ";

        return msg;
    }
}
