package ru.dnvaulin.project;

import java.security.*;

public class SignatureService {
    public byte[] generateSign(PrivateKey key, byte[] message) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(key);
        signature.update(message);
        return signature.sign();
    }

    public boolean validateSign(PublicKey key, byte[] message, byte[] sign) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(key);
        signature.update(message);
        return signature.verify(sign);
    }
}
