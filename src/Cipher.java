import com.sun.org.glassfish.external.statistics.Stats;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Cipher {
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789,.() '\"![]/%_;?-=:\n";
    public static final String SIMPLE_ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ";

    private static final int FORWARD = 1;
    private static final int BACKWARD = -1;

    // Set this variable to the default alphabet you wish to use
    private static final String DEFAULT_ALPHABET = ALPHABET;
    public static final Dictionary dict = new Dictionary();
    public static final String[] dictionaryList = dict.getWordList();

    /**
     * Returns plaintext encrypted by the rotation cipher with a shift of
     * movement.
     *
     * @param alphabet    the alphabet to be used for the encryption
     * @param plainText   the plain text to be encrypted.
     * @param shiftAmount the number of characters in ALPHABET to shift by.
     * @return returns the encrypted plainText.
     */
    public static String rotationCipherEncrypt(String plainText, int shiftAmount, String alphabet) {
        String output;
        if (shiftAmount == 0) {
            output = plainText;
        } else if (shiftAmount > 0) {
            output = AZrotation(alphabet, plainText, shiftAmount);
        } else {
            shiftAmount *= -1;
            output = ZArotation(alphabet, plainText, shiftAmount);
        }
        return output;
    }

    public static String rotationCipherEncrypt(String plainText, int shiftAmount) {
        return rotationCipherEncrypt(plainText, shiftAmount, DEFAULT_ALPHABET);
    }

    private static String AZrotation(String alphabet, String input, int rotation) {
        String output = "";
        int indexOfReplacement;
        rotation %= alphabet.length();
        for (int i = 0; i < input.length(); i++) {
            if (alphabet.indexOf(input.substring(i, i + 1)) + rotation + 1 > alphabet.length()) {
                indexOfReplacement = alphabet.indexOf(input.substring(i, i + 1)) + rotation - alphabet.length();
            } else {
                indexOfReplacement = alphabet.indexOf(input.substring(i, i + 1)) + rotation;
            }
            output += alphabet.substring(indexOfReplacement, indexOfReplacement + 1);
        }
        return output;
    }

    private static String ZArotation(String alphabet, String input, int rotation) {
        String output = "";
        int indexOfReplacement;
        rotation %= alphabet.length();
        for (int i = 0; i < input.length(); i++) {
            if (alphabet.indexOf(input.substring(i, i + 1)) - rotation < 0) {
                indexOfReplacement = alphabet.indexOf(input.substring(i, i + 1)) - rotation + alphabet.length();
            } else {
                indexOfReplacement = alphabet.indexOf(input.substring(i, i + 1)) - rotation;
            }
            output += alphabet.substring(indexOfReplacement, indexOfReplacement + 1);
        }
        return output;
    }

    /**
     * Returns a the result of decrypting cipherText by shiftAmount using the
     * rotation cipher.
     *
     * @param alphabet    the alphabet to be used for the encryption
     * @param cipherText  the encrypted text.
     * @param shiftAmount the key to decrypt the cipher.
     * @return returns the decrypted cipherText.
     */
    public static String rotationCipherDecrypt(String cipherText, int shiftAmount, String alphabet) {
        return rotationCipherEncrypt(cipherText, -shiftAmount, alphabet);
    }

    public static String rotationCipherDecrypt(String cipherText, int shiftAmount) {
        return rotationCipherDecrypt(cipherText, -shiftAmount, DEFAULT_ALPHABET);
    }

    /**
     * Returns plaintext encrypted by the permutation cipher encoded with the
     * input permutation. A permutation is given by a String the same length as
     * alphabet that contains the exact same characters as alphabet but possibly
     * in another ordering.
     *
     * @param alphabet    the alphabet to be used for the encryption
     * @param plainText   the plain text to be encrypted.
     * @param permutation a permutation of alphabet, to be used in encoding
     * @return returns the encrypted plainText.
     */
    public static String permutationCipherEncrypt(String plainText, String permutation, String alphabet) {
        return "Need to implement permutationCipherEncrypt";
    }

    /**
     * Returns the result of decrypting cipherText with the key permutation.
     *
     * @param alphabet   the alphabet to be used for the encryption
     * @param cipherText the encrypted text.
     * @param code       the decryption key
     * @return returns the decrypted cipherText.
     */
    public static String permutationCipherDecrypt(String cipherText, String code, String alphabet) {
        return "Need to implement permutationCipherDecrypt";
    }

    /***
     * Returns a random permutation of the alphabet string
     *
     * @param alphabet
     *            represents the alphabet
     * @return a random permutation of alphabet
     */
    public static String generateRandomPermutation(String alphabet) {
        return "";
    }

    /**
     * Returns plaintext encrypted by the vigenere cipher encoded with the
     * String code
     *
     * @param alphabet  the alphabet to be used for the encryption
     * @param plainText the plain text to be encrypted.
     * @param code      the code to use as the encryption key.
     * @return returns the encrypted plainText.
     */
    public static String vigenereCipherEncrypt(String plainText, String code, String alphabet) {
        ArrayList<Integer> codeIndexes = codeIndexes(plainText, code, 1, alphabet);

        String finalText = "";
        for (int i = 0; i < plainText.length(); i++) {
            finalText += rotationCipherEncrypt(plainText.substring(i, i + 1), codeIndexes.get(i), alphabet);
        }
        return finalText;
    }

    public static ArrayList<Integer> codeIndexes(String plainText, String code, int direction, String alphabet) {
        ArrayList<Integer> codeIndexes = new ArrayList<Integer>();
        for (int i = 0; i < plainText.length(); i++) {
            int j = i;
            j %= code.length();
            codeIndexes.add(direction * alphabet.indexOf(code.substring(j, j + 1)));
        }
        return codeIndexes;
    }

    public static String vigenereCipherEncrypt(String plainText, String code) {
        return vigenereCipherEncrypt(plainText, code, DEFAULT_ALPHABET);
    }


    /**
     * Returns the result of decrypting cipherText with the key code.
     *
     * @param alphabet   the alphabet to be used for the encryption
     * @param cipherText the encrypted text.
     * @param code       the decryption key
     * @return returns the decrypted cipherText.
     */
    public static String vigenereCipherDecrypt(String cipherText, String code, String alphabet) {
        ArrayList<Integer> codeIndexes = codeIndexes(cipherText, code, -1, alphabet);

        String finalText = "";
        for (int i = 0; i < cipherText.length(); i++) {
            finalText += rotationCipherEncrypt(cipherText.substring(i, i + 1), codeIndexes.get(i), alphabet);
        }
        return finalText;
    }

    /**
     * returns a copy of the input plaintext String with invalid characters
     * stripped out.
     *
     * @param plaintext The plaintext string you wish to remove illegal characters
     *                  from
     * @param alphabet  A string of all legal characters.
     * @return String A copy of plain with all characters not in alphabet
     * removed.
     */
    private static String stripInvalidChars(String plaintext, String alphabet) {
        int count = 0;
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) { // loop through plaintext
            if (alphabet.indexOf(plaintext.charAt(i)) >= 0) // get index of char
                b.append(plaintext.charAt(i)); // if it exists, keep it
            else {
                // otherwise skip it &
                //System.out.println("Stripping letter: \"" + plaintext.charAt(i) // display
                // a
                // message
                //       + "\"");
                count++;
            }
        }

        //       System.out.println("StripInvalidChars removed a total of " + count + " chars.");
        return b.toString();
    }

    public static String loadFileAsString(String filepath) {
        StringBuilder output = new StringBuilder();

        try (Scanner scanner = new Scanner(new File(filepath))) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                output.append(line + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error in loadFileAsString(...) with filepath: " + filepath);
            e.printStackTrace();
        }

        return output.toString();
    }

    public static boolean saveStringToFile(String contents, String filepath) {
        File outFile = new File(filepath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
            writer.write(contents);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /***
     * Return true if input text is probably English (as opposed to cipherText)
     * @param text
     * @return true if input text is English, false otherwise
     */
    public static boolean isEnglish(String text, double englishPercentThreshold) {
        text = text.toLowerCase();
        text = stripInvalidChars(text, SIMPLE_ALPHABET);
        ArrayList<String> words = new ArrayList<>();
        int indexOfSpace = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.substring(i, i + 1).equals(" ")) {
                words.add(text.substring(indexOfSpace + 1, i));
                indexOfSpace = i;
            }
        }
        double englishCount = 0;
        for (int i = 0; i < words.size(); i++) {
            if (dict.isWord(words.get(i))) {
                englishCount++;
            }
        }
        if (englishCount / words.size() >= englishPercentThreshold) {
            return true;
        }
        return false;
    }

    /***
     * Brute force attack cipherText by trying all possible rotation amounts until we have plaintext.
     *
     * @param cipherText the cipher text
     * @return the rotation amount the original cipher was encoded with
     */
    public static int bruteForceCrackRotation(String cipherText, double percentThreshold) {
        for (int shift = 1; shift < ALPHABET.length(); shift++) {
            if (isEnglish(rotationCipherDecrypt(cipherText, shift), percentThreshold)) {
                return shift;
            }
        }
        return -1;
    }

    /***
     * Brute force attack cipherText by trying all possible passwords of length 2.
     * @param cipherText
     * @return the correct password for this text.
     */
    public static String bruteForceCrackLength2Password(String cipherText) {
        return decodeLengthN(cipherText, 2);
    }

    /***
     * Brute force attack cipherText by trying all possible passwords of length 3.
     * @param cipherText
     * @return the correct password for this text.
     */
    public static String bruteForceCrackLength3Password(String cipherText) {
        return decodeLengthN(cipherText, 3);
    }

    public static String crackLength8Password(String cipherText) {
        return decodeLengthN(cipherText, 8);
    }

    //Decodes for any password length N
    public static String decodeLengthN(String cipherText, int N) {
        ArrayList<StatsObject> groups = splitIntoNGroups(cipherText, N);
        StatsObject key = new StatsObject();
        for (int i = 0; i < N; i++) {
            key.add(distanceFromFreqAndSpace(groups.get(i), DEFAULT_ALPHABET));
        }
        return key.toString();
    }

    private static ArrayList<StatsObject> splitIntoNGroups(String cipherText, int N) {
        ArrayList<StatsObject> output = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            output.add(getGroupsFromIndex(cipherText, i, N));
        }
        return output;
    }

    private static StatsObject getGroupsFromIndex(String cipherText, int i, int keyLength) {
        StatsObject group = new StatsObject();
        for (int j = i; j < cipherText.length(); j += keyLength) {
            group.add(cipherText.substring(j, j + 1));
        }
        return group;
    }

    private static String distanceFromFreqAndSpace(StatsObject group, String alphabet) {
        int index1 = alphabet.indexOf(group.getMostFrequent());
        int index2 = alphabet.indexOf(" ");
        if (index1 - index2 < 0) {
            return alphabet.substring((index1 - index2 + alphabet.length()), (index1 - index2 + alphabet.length() + 1));
        } else {
            return alphabet.substring((index1 - index2), (index1 - index2 + 1));
        }
    }

    /***
     * This method attempts to crack the password by testing many different lengths.
     * @param cipherText the cipher text to crack
     * @return the password used to encrypt the cipherText
     */
//    //This is the only method that is not fully functional
//    public static String crackPassword(String cipherText) {
//        int i = 0;
//
//    }
}