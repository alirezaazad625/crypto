package ir.sharif.messenger.client;

public class Message {

    // messageId approximately equal to (time * 2^32)
    Long id;

    // Senders username
    String from;

    // Receivers username
    String to;

    // Message content
    String text;

    Message(Long id,String from,String to,String text){
        this.id = id;
        this.from = from;
        this.to = to;
        this.text = text;
    }
}