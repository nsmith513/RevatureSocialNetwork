package com.project.two.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCrypt.Version;

/**
 * Holds static support functions and objects for com.project.two.
 * 
 * @author Nicholas Smith
 */
public class Support {
	
	/**
	 * Checks if an object is equal to all of a group of objects.
	 * 
	 * @param <T> - Any comparable object.
	 * @param o - Object to check equality of.
	 * @param toCheck - Group of objects to check against {@code o}.
	 * @return {@code true} if {@code o} is equal to all of {@code toCheck}.
	 */
	@SafeVarargs
	public static <T> boolean eqand(T o, T... toCheck) {
		if (o == null) {
			for (T check : toCheck)
				if (check != null)
					return false;
		} else {
			for (T check : toCheck)
				if (!o.equals(check))
					return false;
		}
		return true;
	}
	
	/**
	 * Checks if an object is equal to any of a group of objects.
	 * 
	 * @param <T> - Any comparable object.
	 * @param o - Object to check equality of.
	 * @param toCheck - Group of objects to check against {@code o}.
	 * @return {@code true} if {@code o} is equal to any of {@code toCheck}.
	 */
	@SafeVarargs
	public static <T> boolean eqor(T o, T... toCheck) {
		if (o == null) {
			for (T check : toCheck)
				if (check == null)
					return true;
		} else {
			for (T check : toCheck)
				if (o.equals(check))
					return true;
		}
		return false;
	}
	
	/**
	 * Reads contents of a file into a string.
	 * 
	 * @param f - File to read.
	 * @return String containing all text in specified file.
	 * @throws IOException File could not be read.
	 */
	public static String readFile(File f) throws IOException {
		StringBuilder sb = new StringBuilder();
		
		BufferedReader reader = new BufferedReader(new FileReader(f));
		String line;
		while ((line = reader.readLine()) != null)
			sb.append(line + '\n');
		reader.close();
		
		return sb.toString();
	}
	
	private static final BCrypt.Hasher HASH = BCrypt.withDefaults();
	private static final BCrypt.Verifyer VERIFY = BCrypt.verifyer(Version.VERSION_2A);
	private static final int HASH_COST = 10;
	
	/**
	 * @param password - A password to hash.
	 * @return The hashed password.
	 */
	public static String hashpw(String password) {
		return HASH.hashToString(HASH_COST, password.toCharArray());
	}
	
	/**
	 * Checks if a password matches a hash.
	 * 
	 * @param password - Password to check.
	 * @param hashedPwd - Hash to check {@code password} against.
	 * @return {@code true} if {@code password} matches {@code hashedPwd}.
	 */
	public static boolean verifypw(String password, String hashedPwd) {
		return VERIFY.verifyStrict(password.toCharArray(), hashedPwd.toCharArray()).verified;
	}
	
}
