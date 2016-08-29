package businessLogic.services;

/**
 * Created by oscar on 29/08/16.
 */
public class DataPreparation {

    private static final char FILL_CHAR = '~';

    public static int[] fillMessage(String message) {
        int n = (int) Math.ceil(Math.sqrt(message.length()));
        if (n % 2 != 0) n++;
        int[] filledMessage = new int[n * n];
        for (int i = 0; i < n * n; i++) {
            if(i < message.length())
                filledMessage[i] = message.charAt(i);
            else
                filledMessage[i] = FILL_CHAR;
        }
        return filledMessage;
    }

    public static String trimFill(String message) {
        StringBuilder tempMessage = new StringBuilder(message);
        for (int i = message.length() - 1; i >= 0; --i)
            if (message.charAt(i) == FILL_CHAR)
                tempMessage.deleteCharAt(i);
        return tempMessage.toString();
    }

}
