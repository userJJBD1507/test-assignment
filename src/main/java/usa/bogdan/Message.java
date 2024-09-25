package usa.bogdan;

import java.util.Date;

public class Message {
    String version;
    String subversion;
    String order;
    Date date;
    int waitingTime;

    public Message(String version, String subversion, String order, Date date, int waitingTime) {
        this.version = version;
        this.subversion = subversion;
        this.order = order;
        this.date = date;
        this.waitingTime = waitingTime;
    }
}