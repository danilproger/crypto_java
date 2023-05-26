package ru.dnvaulin.keytool;

import sun.security.tools.keytool.CertAndKeyGen;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class CertAndKeyGenerator {
    public CertAndKeyGen generateCertAndKey(int length) throws NoSuchAlgorithmException, InvalidKeyException {
        CertAndKeyGen generator = new CertAndKeyGen("RSA", "SHA256withRSA");
        generator.generate(length);
        return generator;
    }
}
