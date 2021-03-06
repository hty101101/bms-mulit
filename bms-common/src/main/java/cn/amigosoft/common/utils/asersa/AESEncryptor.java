package cn.amigosoft.common.utils.asersa;

import java.security.SecureRandom;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加密器
 *
 * @author Eric_Ni
 */
public class AESEncryptor {

    /**
     * AES加密
     */
    public static String encrypt(String seed, String cleartext)
            throws Exception {
        byte[] rawKey = getRawKey(seed.getBytes());
        byte[] result = encrypt(rawKey, cleartext.getBytes());
        return toHex(result);
    }

    /**
     * AES解密
     */
    public static String decrypt(String seed, String encrypted)
            throws Exception {
        if (seed == null || "".equals(seed) || encrypted == null
                || "".equals(encrypted)) {
            return null;
        }
        byte[] rawKey = getRawKey(seed.getBytes());
        byte[] enc = toByte(encrypted);
        byte[] result = decrypt(rawKey, enc);
        return new String(result);
    }

    private static byte[] getRawKey(byte[] seed) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(seed);
        kgen.init(128, sr); // 192 and 256 bits may not be available
        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();
        return raw;
    }

    private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
        if (raw == null || clear == null) {
            return null;
        }
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(clear);
        return encrypted;
    }

    private static byte[] decrypt(byte[] raw, byte[] encrypted)
            throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] decrypted = cipher.doFinal(encrypted);
        return decrypted;
    }

    public static String toHex(String txt) {
        return toHex(txt.getBytes());
    }

    public static String fromHex(String hex) {
        return new String(toByte(hex));
    }

    public static byte[] toByte(String hexString) {
        if (hexString == null || "".equals(hexString)) {
            return null;
        }
        int len = hexString.length() / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++) {
            result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2),
                    16).byteValue();
        }
        return result;
    }

    public static String toHex(byte[] buf) {
        if (buf == null)
            return "";
        StringBuffer result = new StringBuffer(2 * buf.length);
        for (int i = 0; i < buf.length; i++) {
            appendHex(result, buf[i]);
        }
        return result.toString();
    }

    private final static String HEX = "0123456789ABCDEF";

    private static void appendHex(StringBuffer sb, byte b) {
        sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
    }

    private static String DefaultKey = "13141314";

    // 先把13141314用密匙管理器生成真正的十六位，KEY：97E208814DE9AFE71C2E242E9232AFE9

    public static String decryptKey(String encrypted) throws Exception {
        return decrypt(DefaultKey, encrypted);
    }

    public static String encryptKey(String cleartext) throws Exception {
        return encrypt(DefaultKey, cleartext);
    }

    /**
     * 产生0～max的随机整数 包括0 不包括max
     *
     * @param max 随机数的上限
     * @return
     */
    public static int getRandom(int max) {
        return new Random().nextInt(max);
    }

    /**
     * 产生 min～max的随机整数 包括 min 但不包括 max
     *
     * @param min
     * @param max
     * @return
     */
    public static int getRandom(int min, int max) {
        int r = getRandom(max - min);
        return r + min;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            Long keFuId = 1067246875800000014L;
            String keFuIdStr = "20_" + keFuId;
            String k = encrypt("chat-amigo", keFuIdStr);
            System.out.println(k);
            String p = decrypt("chat-amigo", k);
            System.out.println(p);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
