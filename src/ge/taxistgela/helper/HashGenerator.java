package ge.taxistgela.helper;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Alex on 5/28/2015.
 */
public class HashGenerator {

    /**
     * Hash algorithm name.
     */
    private static final String HASH_ALGO = "MD5";
    private static final String SALT = "rati1azo%giO(";
    private static final byte[] key = new byte[]{'a', 'V', 'H', 'f', 'i', 'Q', 'b', 'c', 'S', 'M', 'P', 'K', 'z', 'q', 'q', 'q'};

    /**
     * Returns MD5 hash of String.
     *
     * @param word
     * @return MD5 hash.
     */
    public static String getHash(String word) {
        try {
            MessageDigest m = MessageDigest.getInstance(HASH_ALGO);
            return hexToString(m.digest(word.getBytes()));
        } catch (NoSuchAlgorithmException exception) {
            return null;
        }
    }

    public static String getSaltHash(String word) {
        word += SALT;
        return getHash(word);
    }

    /**
     * Given byte[] array, produces a hex String.
     *
     * @param bytes
     * @return Hex String generated from byte[] array.
     */
    private static String hexToString(byte[] bytes) {
        StringBuffer buff = new StringBuffer();
        for (byte aByte : bytes) {
            int val = aByte;
            val = val & 0xff;  // remove higher bits, sign
            if (val < 16) buff.append('0'); // leading 0
            buff.append(Integer.toString(val, 16));
        }
        return buff.toString();
    }

    public static String encryptAES(String text) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Key key = new SecretKeySpec(HashGenerator.key, "AES");
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encr = c.doFinal(text.getBytes());
        return new BASE64Encoder().encode(encr);
    }

    public static String decryptAES(String text) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
        Key key = new SecretKeySpec(HashGenerator.key, "AES");
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decr = c.doFinal(new BASE64Decoder().decodeBuffer(text));
        return new String(decr);
    }











}
