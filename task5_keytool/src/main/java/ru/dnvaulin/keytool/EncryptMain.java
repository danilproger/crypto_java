package ru.dnvaulin.keytool;

import sun.security.tools.keytool.CertAndKeyGen;
import sun.security.x509.X500Name;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.X509Certificate;

public class EncryptMain {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("For usage type 2 arguments: [keystore] [keylength]");
            return;
        }

        String keystore = args[0];
        int keyLength = Integer.parseInt(args[1]);

        byte[] message = "Java".getBytes(StandardCharsets.UTF_8);
        CertAndKeyGenerator certAndKeyGenerator = new CertAndKeyGenerator();
        AsymmetricEncryptionService asymmetricEncryptionService = new AsymmetricEncryptionService();

        CertAndKeyGen keyPair = certAndKeyGenerator.generateCertAndKey(keyLength);
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(null, null);

        Key privateKey = keyPair.getPrivateKey();
        X509Certificate cert = keyPair.getSelfCertificate(new X500Name("CN=ROOT"), (long) 365 * 24 * 3600);
        X509Certificate[] certChain = new X509Certificate[1];
        certChain[0] = cert;

        keyStore.setKeyEntry("private", privateKey, "password".toCharArray(), certChain);
        keyStore.store(new FileOutputStream(keystore), "password".toCharArray());

        byte[] encryptedMessage = asymmetricEncryptionService.encryptMessage(cert.getPublicKey(), message);

        System.out.println("Encrypted message: " + bytesToHex(encryptedMessage));
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
