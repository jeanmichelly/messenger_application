package utt.isi.if26.project.android.messenger.network;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

/**
 * Created by jean-michelly on 03/01/2015.
 */
public class SecurityPassword {

    final public static String GLOBAL_SALT = "ps";

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }

        String result = formatter.toString();
        formatter.close();

        return result;
    }

    public static String encryptPassword(String password, String modeCrypt) {
        String sha1 = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance(modeCrypt);
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        }
        catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sha1;
    }
}
