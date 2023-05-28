package ru.dnvaulin.project.decrypt;

import ru.dnvaulin.project.AsymmetricEncryptionService;
import ru.dnvaulin.project.KeyStoreService;
import ru.dnvaulin.project.SignatureService;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyStore;
import java.security.PublicKey;

public class DecryptMain {
    public static void main(String[] args) throws Exception {
        if (args.length != 5) {
            System.out.println("For usage type 5 arguments: " +
                    "[keystore full path] " +
                    "[keystore pass] " +
                    "[encrypted word] " +
                    "[sign] " +
                    "[key name]");
            return;
        }

        String keyStorePath = args[0];
        String password = args[1];
        byte[] encryptedMessage = hexToByteArray(args[2]);
        byte[] sign = hexToByteArray(args[3]);
        String keyName = args[4];

        KeyStoreService keyStoreService = new KeyStoreService();
        AsymmetricEncryptionService asymmetricEncryptionService = new AsymmetricEncryptionService();
        SignatureService signatureService = new SignatureService();

        KeyStore keyStore = keyStoreService.loadKeyStore(keyStorePath, password.toCharArray());
        PublicKey publicKey = keyStore.getCertificate(keyName).getPublicKey();
        Key privateKey = keyStore.getKey(keyName, password.toCharArray());

        boolean isValidSign = signatureService.validateSign(publicKey, encryptedMessage, sign);
        byte[] decryptedMessage = asymmetricEncryptionService.decryptMessage(privateKey, encryptedMessage);

        String message = new String(decryptedMessage, StandardCharsets.UTF_8);

        System.out.printf("" +
                        "DecryptedMessage: %s\n" +
                        "Sign valid: %s\n",
                message,
                isValidSign
        );
    }

    public static byte[] hexToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}
