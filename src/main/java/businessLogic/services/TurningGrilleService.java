package businessLogic.services;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by oscar on 28/08/16.
 */
public class TurningGrilleService {

    // Constants for the map
    public static final int CIPHER = 0;
    public static final int KEY = 1;
    public static final int DIRECTION = 2;
    public static final int MASK1 = 3;
    public static final int MASK2 = 3;
    public static final int MASK3 = 3;

    public static int[] decrypt(HashMap<Integer, int[]> cipherAndKey) {
        int[] cipher = cipherAndKey.get(CIPHER);
        int[] key = cipherAndKey.get(KEY);
        char direction = (char) cipherAndKey.get(DIRECTION)[0];
        int[] message = new int[cipher.length];
        int n = (int) Math.sqrt(cipher.length);
        List<int[]> masks = retrieveMasks(key, direction, n);
        //printMasks(masks);
        for (int i = 0; i < message.length; i++) {
            int decipherIdx = masks.get((int) Math.floor(i / n))[i % n];
            message[i] = cipher[decipherIdx];
        }
        return message;
    }

    public static HashMap<Integer, int[]> encrypt(int[] message, char direction) {
        int n = (int) Math.sqrt(message.length);
        int[] cipherMessage = new int[message.length];
        List<int[]> masks = generateMasks(n, direction);
        //printMasks(masks);
        for (int i = 0; i < message.length; i++) {
            int cipherIdx = masks.get((int) Math.floor(i / n))[i % n];
            cipherMessage[cipherIdx] = message[i];
        }
        HashMap<Integer, int[]> cipherAndKey = new HashMap<>();
        cipherAndKey.put(CIPHER, cipherMessage);
        cipherAndKey.put(KEY, masks.get(0));
        cipherAndKey.put(DIRECTION, new int[] {direction});
        cipherAndKey.put(MASK1, masks.get(1));
        cipherAndKey.put(MASK2, masks.get(2));
        cipherAndKey.put(MASK3, masks.get(3));
        return cipherAndKey;
    }

    private static List<int[]> retrieveMasks(int[] key, char direction, int n) {
        List<int[]> masks = new ArrayList<>(4);
        masks.add(key);
        for (int i = 1; i < 4; i++)
            masks.add(new int[key.length]);

        for (int i = 0; i < key.length; i++) {
            int idx = key[i];
            for (int j = 1; j < 4; j++) {
                if (direction == 'l')
                    idx = rotateIndexLeft(idx, n);
                else if (direction == 'r')
                    idx = rotateIndexRight(idx, n);
                masks.get(j)[i] = idx;
            }
        }
        return masks;
    }

    private static List<int[]> generateMasks(int n, char direction) {
        int numHolesPerRotation = (n * n) / 4;

        // A list of indexes for each rotation that mark the taken places.
        List<int[]> masks = new ArrayList<>(4);
        for (int i = 0; i < 4; i++)
            masks.add(new int[numHolesPerRotation]);

        // List of those indexes that haven't been taken in any of the rotations.
        List<Integer> notTakenIndexes = IntStream.range(0, n * n).boxed().collect(Collectors.toList());

        for (int i = 0; i < numHolesPerRotation; i++) {
            // We take a random initial index and find its rotations.
            int idx = notTakenIndexes.get(new Random().nextInt(notTakenIndexes.size()));
            // We delete the initial index from the not-taken ones and add it in the first mask.
            masks.get(0)[i] = notTakenIndexes.remove(notTakenIndexes.indexOf(idx));

            // Then we rotate that initial index 3 times and each rotated index is deleted from the not-taken ones and
            // added to their respective masks.
            for (int j = 1; j < 4; j++) {
                if (direction == 'l')
                    idx = rotateIndexLeft(idx, n);
                else if (direction == 'r')
                    idx = rotateIndexRight(idx, n);
                masks.get(j)[i] = notTakenIndexes.remove(notTakenIndexes.indexOf(idx));
            }
        }
        return masks;
    }

    private static int[] rotateLeft(int[] originalMatrix) {
        int n = (int) Math.sqrt(originalMatrix.length);
        int[] rotatedMatrix = new int[originalMatrix.length];
        for (int idx = 0; idx < originalMatrix.length; idx++)
            rotatedMatrix[rotateIndexLeft(idx, n)] = originalMatrix[idx];
        return rotatedMatrix;
    }

    private static int[] rotateRight(int[] originalMatrix) {
        int n = (int) Math.sqrt(originalMatrix.length);
        int[] rotatedMatrix = new int[originalMatrix.length];
        for (int idx = 0; idx < originalMatrix.length; idx++)
            rotatedMatrix[rotateIndexRight(idx, n)] = originalMatrix[idx];
        return rotatedMatrix;
    }

    private static int rotateIndexLeft(int idx, int n) {
        return (n - 1 - idx % n) * n + (int) Math.floor(idx / n);
    }

    private static int rotateIndexRight(int idx, int n) {
        return (idx % n) * n + n - 1 - (int) Math.floor(idx / n);
    }

    private static boolean areArraysEqual(int[] array1, int[] array2) {
        if (array1.length != array2.length)
            return false;

        for (int i = 0; i < array1.length; i++)
            if (array1[i] != array2[i])
                return false;

        return true;
    }

    private static void printMasks(List<int[]> masks) {
        for (int i = 0; i < masks.size(); i++) {
            System.out.println("\nMask " + i);
            Arrays.asList(masks.get(i)).stream().forEach(l -> System.out.println(Arrays.toString(l)));
        }
    }

    public static void main(String[] args) {
        int[] originalArray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        int[] originalArrayCopy = rotateRight(rotateLeft(originalArray));
        int[] rotatedLeftArray = rotateLeft(originalArray);
        System.out.println(areArraysEqual(originalArray, originalArrayCopy));
        System.out.println(areArraysEqual(originalArray, rotatedLeftArray));

        List<int[]> masks = generateMasks((int) Math.sqrt(originalArray.length), 'l');
        //printMasks(masks);

        System.out.println("\n");
        HashMap<Integer, int[]> cipherAndKey = encrypt(originalArray, 'r');
        System.out.println(Arrays.toString(cipherAndKey.get(CIPHER)));
        System.out.println(Arrays.toString(cipherAndKey.get(KEY)));

        int[] message = decrypt(cipherAndKey);
        System.out.println("\n");
        System.out.println(Arrays.toString(message));
    }

}
