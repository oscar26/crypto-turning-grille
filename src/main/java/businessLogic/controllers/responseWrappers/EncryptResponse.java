package businessLogic.controllers.responseWrappers;

import java.util.List;

/**
 * Created by oscar on 29/08/16.
 */
public class EncryptResponse {

    private String cipherMessage;
    private int[] key;
    private char direction;
    private int[][] masks;

    public String getCipherMessage() {
        return cipherMessage;
    }

    public void setCipherMessage(String cipherMessage) {
        this.cipherMessage = cipherMessage;
    }

    public int[] getKey() {
        return key;
    }

    public void setKey(int[] key) {
        this.key = key;
    }

    public int[][] getMasks() {
        return masks;
    }

    // Converting from list of int[] to int[][]
    public void setMasks(List<int[]> masks) {
        int[][] convertedMasks = new int[masks.size()][masks.get(0).length];
        for (int i = 0; i < masks.size(); i++)
            for (int j = 0; j < masks.get(0).length; j++)
                convertedMasks[i][j] = masks.get(i)[j];
        this.masks = convertedMasks;
    }

    public void setMasks(int[][] masks) {
        this.masks = masks;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }
}
