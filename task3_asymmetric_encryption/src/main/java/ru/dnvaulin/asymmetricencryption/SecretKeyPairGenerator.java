package ru.dnvaulin.asymmetricencryption;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class SecretKeyPairGenerator {
    public KeyPair generateKey(int length) throws NoSuchAlgorithmException {
        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
        keyGenerator.initialize(length);
        return keyGenerator.genKeyPair();
    }
}
