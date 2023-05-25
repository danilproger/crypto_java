package ru.dnvaulin.pbe;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("For usage type argument: password");
            return;
        }

        char[] password = args[0].toCharArray();
        byte[] message = "Java".getBytes(StandardCharsets.UTF_8);

        SecretKeyGenerator secretKeyPairGenerator = new SecretKeyGenerator();
        PasswordBasedEncryptionService passwordBasedEncryptionService = new PasswordBasedEncryptionService();

        SecretKey key = secretKeyPairGenerator.generateKey(password, 128);
        byte[] encryptedMessage = passwordBasedEncryptionService.encryptMessage(key, message);
        byte[] decryptedMessage = passwordBasedEncryptionService.decryptMessage(key, encryptedMessage);

        System.out.printf("Encrypted message: %s\nDecrypted message: %s\nOriginal message: %s\n",
                bytesToHex(encryptedMessage),
                new String(decryptedMessage, StandardCharsets.UTF_8),
                new String(message, StandardCharsets.UTF_8)
        );
        System.out.println();
    }

    private static String bytesToHex(byte[] bytes) {
        char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}
