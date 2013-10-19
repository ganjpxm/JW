package org.ganjp.jpw.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncryptUtil {
	private static String DEFAULT_JCE = "com.sun.crypto.provider.SunJCE"; 
	private static String IBM_JCE = "com.ibm.crypto.provider.IBMJCE"; 
	protected static final Log log = LogFactory.getLog(EncryptUtil.class); 
	private static final byte[] KEYVALUE = "5^)(9-p35@%3#4S!4S0)$Y%%^&5(j.&^&o(*0)$Y%!#O@*GpG@=+@j.&6^)(0-=+".getBytes();
	private static final int BUFFERLEN = 512;
	private static final String ALGO = "AES";
    private static final byte[] keyValue = new byte[] { 'E', 's', 'D', 'b', 's', 's', 't','S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };

	// 8-byte Salt
	byte[] salt = {(byte)0xA9, (byte)0x9B, (byte)0xC8, (byte)0x32, (byte)0x56, (byte)0x35, (byte)0xE3, (byte)0x03};
	int iterationCount = 19;
	
	public EncryptUtil() {
		
	}
	
	/**
	 * <p>Encryt File</p>
	 * 
	 * @param originalFile
	 * @param enryptFile
	 * @throws Exception
	 */
	public static void encryptFile(String originalFile, String enryptFile)
			throws Exception {
		FileInputStream in = new FileInputStream(originalFile);
		File file = new File(enryptFile);
		if (!file.exists())
			file.createNewFile();
		FileOutputStream out = new FileOutputStream(file);
		int c, pos, keylen;
		pos = 0;
		keylen = KEYVALUE.length;
		byte buffer[] = new byte[BUFFERLEN];
		while ((c = in.read(buffer)) != -1) {
			for (int i = 0; i < c; i++) {
				buffer[i] ^= KEYVALUE[pos];
				out.write(buffer[i]);
				pos++;
				if (pos == keylen)
					pos = 0;
			}
		}
		in.close();
		out.close();
	}
	/**
	 * <p>Decrypt File</p>
	 * 
	 * @param oldFile
	 * @param newFile
	 * @throws Exception
	 */
	public static void decryptFile(String oldFile, String newFile) throws Exception {
		FileInputStream in = new FileInputStream(oldFile);
		File file = new File(newFile);
		if (!file.exists())
			file.createNewFile();
		FileOutputStream out = new FileOutputStream(file);
		int c, pos, keylen;
		pos = 0;
		keylen = KEYVALUE.length;
		byte buffer[] = new byte[BUFFERLEN];
		while ((c = in.read(buffer)) != -1) {
			for (int i = 0; i < c; i++) {
				buffer[i] ^= KEYVALUE[pos];
				out.write(buffer[i]);
				pos++;
				if (pos == keylen)
					pos = 0;
			}
		}
		in.close();
		out.close();
	}
	
    static { 
        try { 
            Security.addProvider((Provider)Class.forName(DEFAULT_JCE).newInstance()); 
        } catch (Exception e) { 
            log.info(e); 
            try  { 
                Security.addProvider((Provider)Class.forName(IBM_JCE).newInstance()); 
            } catch (Exception ex) { 
                log.info(ex); 
            }
        }
    } 

    /**  
     * <p>get hex string</p> 
     * 
     * @param x 
     * @return 
     */ 
    private static String hexDigit(byte x) { 
        StringBuffer sb = new StringBuffer(); 
        char c; 
        // First nibble 
        c = (char) ((x >> 4) & 0xf); 
        if (c > 9) { 
            c = (char) ((c - 10) + 'a'); 
        } else { 
            c = (char) (c + '0'); 
        } 
        sb.append(c); 
        // Second nibble 
        c = (char) (x & 0xf); 
        if (c > 9) { 
            c = (char) ((c - 10) + 'a'); 
        } else { 
            c = (char) (c + '0'); 
        }
        sb.append(c); 
        return sb.toString(); 
    } 

    /**  
     * <p>encrypt</p>
     * 
     * @param content 
     *            
     * @return  
     */ 
    public static String encryptByMD5(String content) { 
        try { 
            MessageDigest algorithm = null; 
            algorithm = MessageDigest.getInstance("MD5"); 
            algorithm.reset(); 
            if (content != null) { 
                algorithm.reset(); 
                algorithm.update(content.getBytes()); 
                byte digest[] = algorithm.digest(); 
                StringBuffer hexString = new StringBuffer(); 
                int digestLength = digest.length; 
                for (int i = 0; i < digestLength; i++) { 
                	if (i>0) {
                    	hexString.append("-");
                    }
                	hexString.append(hexDigit(digest[i])); 
                } 
                return hexString.toString(); 
            } else { 
                return ""; 
            } 
        } catch (NoSuchAlgorithmException ex) { 
            return content; 
        } 
    } 
      
    public static boolean authenticate(String attemptedPassword, byte[] encryptedPassword, byte[] salt)
       throws NoSuchAlgorithmException, InvalidKeySpecException {
	      // Encrypt the clear-text password using the same salt that was used to
	      // encrypt the original password
	      byte[] encryptedAttemptedPassword = getEncryptedPassword(attemptedPassword, salt);
	      // Authentication succeeds if encrypted password that the user entered
	      // is equal to the stored hash
	      return Arrays.equals(encryptedPassword, encryptedAttemptedPassword);
     }
     
     public static byte[] getEncryptedPassword(String password, byte[] salt)
	       throws NoSuchAlgorithmException, InvalidKeySpecException {
	      // PBKDF2 with SHA-1 as the hashing algorithm. Note that the NIST
	      // specifically names SHA-1 as an acceptable hashing algorithm for PBKDF2
	      String algorithm = "PBKDF2WithHmacSHA1";
	      // SHA-1 generates 160 bit hashes, so that's what makes sense here
	      int derivedKeyLength = 160;
	      // Pick an iteration count that works for you. The NIST recommends at
	      // least 1,000 iterations:
	      // http://csrc.nist.gov/publications/nistpubs/800-132/nist-sp800-132.pdf
	      // iOS 4.x reportedly uses 10,000:
	      // http://blog.crackpassword.com/2010/09/smartphone-forensics-cracking-blackberry-backup-passwords/
	      int iterations = 20000;
	      KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength);
	      SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);
	      return f.generateSecret(spec).getEncoded();
     }
     
     public static byte[] generateSalt() throws NoSuchAlgorithmException {
	      // VERY important to use SecureRandom instead of just Random
	      SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
	      // Generate a 8 byte (64 bit) salt as recommended by RSA PKCS5
	      byte[] salt = new byte[8];
	      random.nextBytes(salt);
	      return salt;
     }

     public static String encryptByBase64(String Data) throws Exception {
         Key key = generateKey();
         Cipher c = Cipher.getInstance(ALGO);
         c.init(Cipher.ENCRYPT_MODE, key);
         byte[] encVal = c.doFinal(Data.getBytes());
         String encryptedValue = new BASE64Encoder().encode(encVal);
         return encryptedValue;
     }

     public static String decryptByBase64(String encryptedData) throws Exception {
         Key key = generateKey();
         Cipher c = Cipher.getInstance(ALGO);
         c.init(Cipher.DECRYPT_MODE, key);
         byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
         byte[] decValue = c.doFinal(decordedValue);
         String decryptedValue = new String(decValue);
         return decryptedValue;
     }
     
     private static Key generateKey() throws Exception {
	     Key key = new SecretKeySpec(keyValue, ALGO);
	     return key;
	 }
     
	public static void main(String[] args) {
		try {
//			String originalFile = new String("C:\\test.txt");
//			String enryptFile = new String("C:\\test_en.txt");
//			String decryptFile = new String("C:\\test_de.txt");
//			encryptFile(originalFile, enryptFile);
//			decryptFile(enryptFile, decryptFile);
//			System.out.println("ok");
			 
//			byte[]  salt = EncryptUtil.generateSalt();
//			byte[] en_password = getEncryptedPassword("123", salt);
//			System.out.print(authenticate("123", en_password, salt));
			String en = encryptByBase64("1");
			String de = decryptByBase64("35rUoVxjsQipqElGUYcdkA==");
			System.out.println(en);
			System.out.println(de);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
