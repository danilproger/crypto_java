package ru.dnvaulin.project;

import sun.security.tools.keytool.CertAndKeyGen;
import sun.security.x509.X500Name;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class KeyStoreService {

    public CertAndKeyGen storeKey(KeyStoreType keyStoreType, String keyName, char[] password) throws KeyStoreException, CertificateException, IOException, NoSuchAlgorithmException, InvalidKeyException, SignatureException, NoSuchProviderException {
        KeyStore keyStore = KeyStore.getInstance(keyStoreType.name());
        keyStore.load(null, password);

        CertAndKeyGen generator = new CertAndKeyGen("RSA", "SHA1WithRSA");
        generator.generate(1024);

        Key key = generator.getPrivateKey();
        X509Certificate certificate = generator.getSelfCertificate(
                new X500Name("CN=ROOT"), (long) 365 * 24 * 3600);
        X509Certificate[] certificates = new X509Certificate[1];
        certificates[0] = certificate;

        keyStore.setKeyEntry(keyName, key, password, certificates);
        keyStore.store(new FileOutputStream("keystore." + keyStoreType.name().toLowerCase()), password);

        return generator;
    }

    public KeyStore loadKeyStore(String keyStorePath, char[] password) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        KeyStoreType type = KeyStoreType.valueOf((keyStorePath.substring(keyStorePath.lastIndexOf('.') + 1)).toUpperCase());
        KeyStore keyStore = KeyStore.getInstance(type.name());
        keyStore.load(new FileInputStream(keyStorePath), password);
        return keyStore;
    }


    public enum KeyStoreType {
        JKS,
        JCEKS,
    }
}
