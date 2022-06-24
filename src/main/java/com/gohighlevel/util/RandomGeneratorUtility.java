package com.gohighlevel.util;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomGeneratorUtility {

	public int randomNumber(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	public String getRandomPhoneNumber() {
		Random random = new Random();
		List<String> givenListCode = Arrays.asList("2529439195", "3126329211", "9492066501", "402-373-7043",
				"703-610-6386");
		String randomPhoneNumber = "";
		int randomIndex = random.nextInt(givenListCode.size());
		randomPhoneNumber = givenListCode.get(randomIndex);
		return randomPhoneNumber;
	}

	public String randomAlphaNumericString(int length) {
		String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" + "123456789";
		SecureRandom rnd = new SecureRandom();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}

}