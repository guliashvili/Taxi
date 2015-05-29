package ge.taxistgela.helper;

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
    private static final String SALT = "rati1azo%giO(";
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
}
