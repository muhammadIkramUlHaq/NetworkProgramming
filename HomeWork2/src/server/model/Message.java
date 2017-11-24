package server.model;

/**
 * Created with IntelliJ IDEA.
 * User: Ikram
 */
public class Message {

    private String message;

    public void addMessage(String localMessage) {
        this.message = localMessage;
    }

    public String getMessage() {
        return this.message;
    }
}
