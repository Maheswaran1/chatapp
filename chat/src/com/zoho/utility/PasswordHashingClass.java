package com.zoho.utility;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;


public class PasswordHashingClass {
		

	  public static synchronized String getEncryptedPassword(String password, String salt) throws Exception {
	        String algorithm = "PBKDF2WithHmacSHA1";
	        int derivedKeyLength = 160; 
	        int iterations = 20000;
	 
	        byte[] saltBytes = Base64.getDecoder().decode(salt);
	        KeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, iterations, derivedKeyLength);
	        SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);
	 
	        byte[] encBytes = f.generateSecret(spec).getEncoded();
	        return Base64.getEncoder().encodeToString(encBytes);
	    }

	public static String getNewEncryptedPassword(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
		String algorithm = "PBKDF2WithHmacSHA1";
        int derivedKeyLength = 160; 
        int iterations = 20000; 
 
        byte[] saltBytes = Base64.getDecoder().decode(salt);
        KeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, iterations, derivedKeyLength);
        SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);
 
        byte[] encBytes = f.generateSecret(spec).getEncoded();
        return Base64.getEncoder().encodeToString(encBytes);
	}
	
	 public static String getNewSalt() throws Exception {
	        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
	        byte[] salt = new byte[8];
	        random.nextBytes(salt);
	        return Base64.getEncoder().encodeToString(salt);
	    }
}


