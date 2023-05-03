package ru.dnvaulin.symmetricencryption;

import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class SecretKeyGenerator {
    public Key generateKey(int length) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(length);
        return keyGenerator.generateKey();
    }
}
