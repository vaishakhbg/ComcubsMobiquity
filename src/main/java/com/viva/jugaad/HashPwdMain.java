package com.viva.jugaad;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class HashPwdMain {

	public static final String salt = "mobiquity-comviva";

	public static void main(String[] args) throws NoSuchAlgorithmException {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter string: \n");
		String s1 = scan.nextLine();
		System.out.println(HashingFunction(s1));

	}

	private static String HashingFunction(String s1) throws NoSuchAlgorithmException {
		MessageDigest md= MessageDigest.getInstance("SHA-256");
		s1=s1+salt;
		byte[] hashpwd=md.digest(s1.getBytes(StandardCharsets.UTF_8));
		
		return toHexString(hashpwd);
		
	}

	private static String toHexString(byte[] hashpwd) {
        BigInteger number = new BigInteger(1, hashpwd);  
        StringBuilder hexString = new StringBuilder(number.toString(16));  
        while (hexString.length() < 32)  
        {  
            hexString.insert(0, '0');  
        }  
        return hexString.toString();
	}

}
