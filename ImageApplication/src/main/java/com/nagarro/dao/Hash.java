package com.nagarro.dao;

import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 

/**
 * @author mayankgangwar
 * class to calculate MD5 hash value
 */
public class Hash { 
	
	/*
	 * @param input hash of input string is generated
	 * @return hashtext MD5 hash of input string
	 */
	public static String hash(String input) 
	{ 
		try { 

			// calling Static getInstance method with MD5 hashing 
			MessageDigest md = MessageDigest.getInstance("MD5"); 

			// digest() method is called to calculate message digest 
			// of an input digest() return array of byte 
			byte[] messageDigest = md.digest(input.getBytes()); 

			// Convert byte array into signum representation 
			BigInteger no = new BigInteger(1, messageDigest); 

			// Convert message digest into hex value 
			String hashtext = no.toString(16); 
			while (hashtext.length() < 32) 
			{ 
				hashtext = "0" + hashtext; 
			} 
			return hashtext; 
		} 

		// For specifying wrong message digest algorithms 
		catch (NoSuchAlgorithmException e) 
		{ 
			throw new RuntimeException(e); 
		} 
	}
}
