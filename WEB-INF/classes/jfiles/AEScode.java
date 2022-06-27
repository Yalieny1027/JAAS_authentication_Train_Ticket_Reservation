package jfiles;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AEScode {
    
    public static String encrypt(String plainText, byte[] rawkey) throws Exception {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(rawkey, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            // String encoded = new BASE64Encoder().encode(bBytes);
            // byte[] decoded = new BASE64Decoder().decodeBuffer(encoded);

            // String plain = charset.decode(ByteBuffer.wrap(byteArrray)).toString();
            Charset charset = StandardCharsets.US_ASCII;
            byte[] plain = charset.encode(plainText).array();

            String encrypted = Base64.getEncoder().encodeToString(plain);
            // byte[] decoded = Base64.getDecoder().decode(encrypted);
            return(encrypted);

            // byte[] encrypted = cipher.doFinal((new BASE64Decoder()).decodeBuffer(plainText));
            // return (new BASE64Encoder()).encode(encrypted);
        } catch (Exception e) {
            // System.out.println(e.getLocalizedMessage());
            throw e;
        }
    }
    public static String decrypt(String encryptedText, byte[] rawkey) throws Exception {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(rawkey, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            
            Charset charset = StandardCharsets.US_ASCII;
            byte[] enc = charset.encode(encryptedText).array();


            String decrypted = Base64.getEncoder().encodeToString(enc);
            // byte[] decoded = Base64.getDecoder().decode(encryptedText);
            return(decrypted);
            // byte[] decrypted = cipher.doFinal((new BASE64Decoder()).decodeBuffer(encryptedText));
            // return (new BASE64Encoder()).encode(decrypted);
        } catch (Exception e) {
            // System.out.println(e.getLocalizedMessage());
            throw e;
        }
    }
}
