package com.viva.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPwd {
	
	public static final String salt = "mobiquity-comviva";
	
	
	public static String HashingFunction(String s1) throws NoSuchAlgorithmException {
		MessageDigest md= MessageDigest.getInstance("SHA-256");
		s1=s1+salt;
		byte[] hashpwd=md.digest(s1.getBytes(StandardCharsets.UTF_8));
		
		return toHexString(hashpwd);
		
	}

	private static String toHexString(byte[] hashpwd) {
        BigInteger number = new BigInteger(1, hashpwd);  
        StringBuilder hexString = new StringBuilder(number.toString(16)); 
        System.out.println(hexString);
        while (hexString.length() < 32)  
        {  
            hexString.insert(0, '0');  
        }  
        return hexString.toString();
	}

}
