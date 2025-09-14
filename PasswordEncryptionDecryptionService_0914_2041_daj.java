// 代码生成时间: 2025-09-14 20:41:00
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordEncryptionDecryptionService {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";
    private static final byte[] KEY = new byte[] {
        (byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04, (byte) 0x05, (byte) 0x06, (byte) 0x07,
        (byte) 0x08, (byte) 0x09, (byte) 0x0A, (byte) 0x0B, (byte) 0x0C, (byte) 0x0D, (byte) 0x0E, (byte) 0x0F
    };

    public String encrypt(String input) throws Exception {
        // Create a new secret key based on the predefined key
        SecretKey secretKey = new SecretKeySpec(KEY, ALGORITHM);

        // Initialize the cipher for encryption
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Encrypt the input string and return the encrypted data as a base64-encoded string
        return Base64.getEncoder().encodeToString(cipher.doFinal(input.getBytes()));
    }

    public String decrypt(String input) throws Exception {
        // Create a new secret key based on the predefined key
        SecretKey secretKey = new SecretKeySpec(KEY, ALGORITHM);

        // Initialize the cipher for decryption
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // Decrypt the input string and return the decrypted data
        return new String(cipher.doFinal(Base64.getDecoder().decode(input)));
    }

    // A simple method to generate a new AES key
    public SecretKey generateNewKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(128); // Use 128-bit AES key
        return keyGenerator.generateKey();
    }
}
