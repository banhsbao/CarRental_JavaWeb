/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Admin
 */
public class Valids {

    private static final String EMAIL_PATTERN = "[a-z0-9-.]{4,32}@[a-z0-9-.]{5,32}(.[a-z0-9-]{2,5}){1,2}";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

    public static boolean checkEmail(String email) {
        Matcher match = pattern.matcher(email);
        return match.find();
    }

    public static String getID(String msg) {
        String RANDOM = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 5) {
            int index = (int) (rnd.nextFloat() * RANDOM.length());
            salt.append(RANDOM.charAt(index));
        }
        String randomString = salt.toString();
        return "[" + msg.toUpperCase() + "]" + randomString;
    }
}
