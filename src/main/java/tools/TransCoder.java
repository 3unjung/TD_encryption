package tools;

import org.apache.commons.lang3.StringUtils;
import org.germain.tool.ManaBox;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class TransCoder {
    private final Map<String, Character> encodedToDecoded;
    private final Map<Character, String> decodedToEncoded;
    private static final int ALPHABET_SIZE = 26;
    private static final int MAX_KEY_LENGTH = ALPHABET_SIZE*ALPHABET_SIZE;

    public TransCoder(String encryptedKey) {
        String decryptedKey = ManaBox.decrypt(encryptedKey);
        int keyLength = decryptedKey.length();
        if (keyLength > MAX_KEY_LENGTH) {
            throw new IllegalArgumentException("La clé ne peut pas contenir plus de " + MAX_KEY_LENGTH + "caractères");
        }

        encodedToDecoded = new HashMap<>();
        decodedToEncoded = new HashMap<>();

        String curEncoded;
        int curDecodedIndex = 0;
        char curDecoded;
        for (char firstEncoded = 'A'; firstEncoded <= 'Z'; firstEncoded++) {
            for (char secondEncoded = 'A'; secondEncoded <= 'Z'; secondEncoded++) {
                if (curDecodedIndex >= keyLength) {
                    break;
                }
                curDecoded = decryptedKey.charAt(curDecodedIndex++);
                curEncoded = String.valueOf(firstEncoded) + secondEncoded;

                encodedToDecoded.put(curEncoded, curDecoded);
                decodedToEncoded.put(curDecoded, curEncoded);
            }
        }
    }

    public String encode(@NotNull String msg) {
        msg = StringUtils.stripAccents(msg);
        StringBuilder encodedMsgBuilder = new StringBuilder();

        for (int i = 0; i < msg.length(); i++) {
            encodedMsgBuilder.append(decodedToEncoded.get(msg.charAt(i)));
        }

        return encodedMsgBuilder.toString();
    }

    public String decode(@NotNull String msg) {
        StringBuilder decodedMsgBuilder = new StringBuilder();

        for (int i = 0; i < msg.length(); i += 2) {
            decodedMsgBuilder.append(encodedToDecoded.get(msg.substring(i, i + 2)));
        }

        return decodedMsgBuilder.toString();
    }
}
