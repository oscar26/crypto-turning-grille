package businessLogic.controllers.requestWrappers;

/**
 * Created by oscar on 29/08/16.
 */
public class EncryptRequest {

    private String message;
    private char direction; // Turning direction: 'l' for left and 'r' for right.

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

}
