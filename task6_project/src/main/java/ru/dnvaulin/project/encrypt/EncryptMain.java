package ru.dnvaulin.project.encrypt;

import ru.dnvaulin.project.AsymmetricEncryptionService;
import ru.dnvaulin.project.KeyStoreService;
import ru.dnvaulin.project.SignatureService;
import sun.security.tools.keytool.CertAndKeyGen;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.UUID;

public class EncryptMain {
    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            System.out.println("For usage type 3 arguments: [random_type] [password] [message]");
            return;
        }

        RandomService.RandomType randomType = RandomService.RandomType.valueOf(args[0].toUpperCase());
        String password = args[1];
        String message = args[2];

        RandomService randomService = new RandomService();
        KeyStoreService keyStoreService = new KeyStoreService();
        AsymmetricEncryptionService asymmetricEncryptionService = new AsymmetricEncryptionService();
        SignatureService signatureService = new SignatureService();

        Random random = randomService.getRandom(randomType);

        KeyStoreService.KeyStoreType keyStoreType = KeyStoreService.KeyStoreType.values()[random.nextInt(KeyStoreService.KeyStoreType.values().length)];

        String keyName = UUID.randomUUID().toString();
        CertAndKeyGen generator = keyStoreService.storeKey(keyStoreType, keyName, password.toCharArray());

        byte[] encryptedMessage = asymmetricEncryptionService.encryptMessage(generator.getPublicKey(), message.getBytes(StandardCharsets.UTF_8));
        byte[] sign = signatureService.generateSign(generator.getPrivateKey(), encryptedMessage);

        System.out.printf("" +
                        "KeyStore type: %s\n" +
                        "KeyName: %s\n" +
                        "Encrypted message: %s\n" +
                        "Sign: %s\n",
                keyStoreType,
                keyName,
                bytesToHex(encryptedMessage),
                bytesToHex(sign)
        );
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
