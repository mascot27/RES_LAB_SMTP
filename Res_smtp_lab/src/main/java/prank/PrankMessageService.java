package prank;

public class PrankMessageService implements IPrankMessageService {
    @Override
    public Message getPrankMessage() {
        var msg = new Message();
        msg.Text = "Visesnfs ndf jdfnksjbdf";
        msg.Title = "saluti";

        return msg;
    }
}
