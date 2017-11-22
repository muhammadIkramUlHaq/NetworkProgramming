package server.controller;

import server.model.Message;

/**
 * Created with IntelliJ IDEA.
 * User: Ikram
 */
public class Controller {

    private final Message messageInstance = new Message();

    public void addMessage(String message) {
        messageInstance.addMessage(message);
    }

    public String getMessage() {
        return messageInstance.getMessage();
    }
}
