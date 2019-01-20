package com.vanhack.api.shorturl;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;

public class NewURL{
	
	public static final String FIXED_HOST = "van.hack";
	
	//Zero(0) and One(1) removed to not confuse with O or L
	private static final String LETTERS_AND_DIGITS = "23456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final int FIXED_URL_LENGTH = 6;
	
	public static String getShorter(String urlAddress) throws IllegalArgumentException {
		if(!validate(urlAddress))
			throw new IllegalArgumentException("URL address is not valid. Please verify.");
		
		return shorten(urlAddress, NewURL.FIXED_HOST);
	}
	
	public static String getShorter(String urlAddress, String host) throws IllegalArgumentException {
		if(!validate(urlAddress))
			throw new IllegalArgumentException("URL address is not valid. Please verify.");
		
		return shorten(urlAddress, host);
	}	

	private static String shorten(String urlAddress, String host){
		try {
			URL url = new URL(urlAddress);

			URL newUrl = new URL(url.getProtocol(), host, url.getPort(), getNewPath(url.getPath()));
		
			return newUrl.toString();
			
		} catch (MalformedURLException e) {
			return null;
		}		
	}

	private static String getNewPath(String path) {
		Random random = new Random(path.length());
		
		StringBuffer sb = new StringBuffer(NewURL.FIXED_URL_LENGTH);
		sb.append("/");
		
		for (int i = 0; i < NewURL.FIXED_URL_LENGTH; i++) {
			int nextInt = random.nextInt(NewURL.LETTERS_AND_DIGITS.length());
			
			sb.append(NewURL.LETTERS_AND_DIGITS.substring(nextInt, nextInt+1));
		}
		
		return sb.toString();
	}

	private static boolean validate(String urlAddress) {		
        try {
			new URL(urlAddress).toURI();
		} catch (MalformedURLException | URISyntaxException e) {
			return false;
		} 
		
		return true; 
	}

}
