package net.fiap.postech.fastburger.adapters.client;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class PasswordGenerator {
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*()-_=+";
    private static final String ALL_CHARS = UPPER + LOWER + DIGITS + SPECIAL;
    private static SecureRandom random = new SecureRandom();

    public static String generatePassword(int length) {
        StringBuilder password = new StringBuilder(length);
        password.append(randomCharacter(UPPER));
        password.append(randomCharacter(LOWER));
        password.append(randomCharacter(DIGITS));
        password.append(randomCharacter(SPECIAL));

        for (int i = 0; i < length - 4; i++) {
            password.append(randomCharacter(ALL_CHARS));
        }

        String shuffledPassword = shuffleString(password.toString());
        return shuffledPassword;
    }
    private static char randomCharacter(String input) {
        int randomIndex = random.nextInt(input.length());
        return input.charAt(randomIndex);
    }
    private static String shuffleString(String input) {
        char[] characters = input.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            int randomIndex = random.nextInt(characters.length);
            char temp = characters[i];
            characters[i] = characters[randomIndex];
            characters[randomIndex] = temp;
        }
        return new String(characters);
    }
}

