package data;

import constants.DefaultLength;
import org.codehaus.groovy.runtime.DateGroovyMethods;

import java.util.Date;
import java.util.Random;

public class RandomGenerator {

    private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static Random random = new Random();

    public static String randomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
        return sb.toString();
    }

    public static String randomString() {
        return randomString(DefaultLength.DEFAULT_STRING_LENGTH);
    }

    public static long randomNumber(long maxNumber) {
        return Math.abs(random.nextLong() % maxNumber) + 1;
    }

    public static long randomNumber() {
        return randomNumber(DefaultLength.DEFAULT_NUMBER_LENGTH);
    }

    public static String randomDate() {
        Date date = new Date(+randomNumber(DefaultLength.DEFAULT_DATE_LENGTH));
        return DateGroovyMethods.format(date, "yyyy-MM-dd'T'HH:mm:ss.SSS+0000");
    }

    public static boolean randomBoolean() {
        return new Random().nextBoolean();
    }

}
