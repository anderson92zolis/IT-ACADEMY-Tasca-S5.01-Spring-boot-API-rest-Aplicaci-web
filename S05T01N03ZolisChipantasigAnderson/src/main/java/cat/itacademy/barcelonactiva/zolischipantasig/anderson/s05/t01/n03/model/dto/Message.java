package cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n03.model.dto;

public class Message {

    private String messageBody;

    public Message(String message) {
        this.messageBody = message;
    }

    public String getMessage() {
        return messageBody;
    }

    public void setMessage(String message) {
        this.messageBody = message;
    }
}