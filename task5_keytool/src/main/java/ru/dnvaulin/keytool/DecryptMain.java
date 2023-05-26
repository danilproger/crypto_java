package ru.dnvaulin.keytool;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyStore;

public class DecryptMain {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("For usage type 2 arguments: [keystore] [message]");
            return;
        }

        String keyStoreName = args[0];
        byte[] encryptedMessage = hexToByteArray(args[1]);

        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(new FileInputStream(keyStoreName), "password".toCharArray());
        Key key = keyStore.getKey("private", "password".toCharArray());

        AsymmetricEncryptionService asymmetricEncryptionService = new AsymmetricEncryptionService();
        byte[] messageBytes = asymmetricEncryptionService.decryptMessage(key, encryptedMessage);
        String message = new String(messageBytes, StandardCharsets.UTF_8);

        System.out.println("Decrypted message: " + message);
    }

    public static byte[] hexToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
}
