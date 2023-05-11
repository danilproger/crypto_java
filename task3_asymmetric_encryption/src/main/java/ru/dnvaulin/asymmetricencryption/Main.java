package ru.dnvaulin.asymmetricencryption;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;

public class Main {
    public static void main(String[] args) throws Exception {
        byte[] message = "Java".getBytes(StandardCharsets.UTF_8);
        SecretKeyPairGenerator secretKeyPairGenerator = new SecretKeyPairGenerator();
        AsymmetricEncryptionService asymmetricEncryptionService = new AsymmetricEncryptionService();
        SignatureService signatureService = new SignatureService();

        KeyPair keyPair = secretKeyPairGenerator.generateKey(1024);
        byte[] encryptedMessage = asymmetricEncryptionService.encryptMessage(keyPair.getPublic(), message);
        byte[] decryptedMessage = asymmetricEncryptionService.decryptMessage(keyPair.getPrivate(), encryptedMessage);

        System.out.printf("Encrypted message: %s\nDecrypted message: %s\nOriginal message: %s\n",
                bytesToHex(encryptedMessage),
                new String(decryptedMessage, StandardCharsets.UTF_8),
                new String(message, StandardCharsets.UTF_8)
        );
        System.out.println();

        byte[] sign = signatureService.generateSign(keyPair.getPrivate(), encryptedMessage);
        boolean isSignValid = signatureService.validateSign(keyPair.getPublic(), encryptedMessage, sign);

        if (isSignValid) {
            System.out.printf("Signature: %s\nSign is ok\n", bytesToHex(sign));
        }
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
