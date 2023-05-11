package ru.dnvaulin.symmetricencryption;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import static ru.dnvaulin.symmetricencryption.Hex.bytesToHex;
import static ru.dnvaulin.symmetricencryption.Hex.hexToByteArray;

public class SymmetricEncryptionService {
    public String encryptMessage(Key key, byte[] message) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedMessage = cipher.doFinal(message);
        return bytesToHex(encryptedMessage);
    }

    public byte[] decryptMessage(Key key, String hexEncryptedMessage) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] message = hexToByteArray(hexEncryptedMessage);
        return cipher.doFinal(message);
    }
}
