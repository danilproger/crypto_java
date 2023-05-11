package ru.dnvaulin.symmetricencryption;

import java.nio.charset.StandardCharsets;
import java.security.Key;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Для использования введите аргумент: Сообщение");
            return;
        }

        MessageDigestSha256 messageDigestSha256 = new MessageDigestSha256();
        SecretKeyGenerator secretKeyGenerator = new SecretKeyGenerator();
        SymmetricEncryptionService service = new SymmetricEncryptionService();

        String message = args[0];
        Key key = secretKeyGenerator.generateKey(256);
        String hexDigest = messageDigestSha256.getDigestHex(message.getBytes(StandardCharsets.UTF_8));
        String hexEncryptedMessage = service.encryptMessage(key, message.getBytes(StandardCharsets.UTF_8));

        System.out.printf("Дайджест сообщения: %s, зашифрованное сообщение: %s%n", hexDigest, hexEncryptedMessage);
        byte[] decryptedMessage = service.decryptMessage(key, hexEncryptedMessage);
        System.out.printf("Расшифрованное сообщение: %s\n", new String(decryptedMessage, StandardCharsets.UTF_8));
    }
}
