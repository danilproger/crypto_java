package ru.dnvaulin.symmetricencryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static ru.dnvaulin.symmetricencryption.Hex.bytesToHex;

public class MessageDigestSha256 {

    public String getDigestHex(byte[] message) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] digest = messageDigest.digest(message);
        return bytesToHex(digest);
    }
}
