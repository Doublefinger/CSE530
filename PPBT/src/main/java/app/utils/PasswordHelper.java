package app.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Random;

public class PasswordHelper {

    private static final int SALT_SIZE_IN_BYTES = 16;

    // Generate salt to secure password encryption
    public static String generateSalt(){
        byte[] buffer = new byte[SALT_SIZE_IN_BYTES];

        SecureRandom random = new SecureRandom();
        random.nextBytes(buffer);
        return Base64.encodeBase64String(buffer);
    }

    public static String sHA1(String message){
        return String.valueOf(DigestUtils.sha1(message));
    }

    public static String uTF8SHA1(String message){
        return String.valueOf(DigestUtils.sha1(message.getBytes(Charset.forName("UTF-8"))));
    }

    // Encode password
    public static String encodePassword(String user, String pass, String salt){
        byte[] bUser = user.getBytes(Charset.forName("UTF-16LE"));
        byte[] bSalt = Base64.decodeBase64(salt);
        byte[] bPass = pass.getBytes(Charset.forName("UTF-16LE"));
        byte[] bAll = new byte[bUser.length +  bSalt.length + bPass.length];
        byte[] bRet = null;

        System.arraycopy(bUser, 0, bAll, 0, bUser.length);
        System.arraycopy(bSalt, 0, bAll, bUser.length, bSalt.length);
        System.arraycopy(bPass, 0, bAll, bUser.length + bSalt.length, bPass.length);
        bRet = DigestUtils.sha1(bAll);
        return Base64.encodeBase64String(bRet);
    }

    private static final String passwordChars = "34678abdefgjmprtwy";
    private static Random random = new Random();

    public static String randomPassword(int length){
        String password = "";
        for (int i = 0; i < length; i++){
            password += passwordChars.charAt(random.nextInt(passwordChars.length()));
        }
        return password;
    }

    public static final String Alphabet = "abcdefghijklmnopqrstuvwyxzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String randomAlphabetString(int length){
        String password = "";
        for (int i = 0; i < length; i++){
            password += Alphabet.charAt(random.nextInt(Alphabet.length()));
        }
        return password;
    }
}
