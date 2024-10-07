package me.skypro.coursework2.utilities;

import java.util.Random;

public class RandomGenerator {
    private static final Random random = new Random();

    public static int getRandomNumber(int bound) {
        return random.nextInt(bound);
    }
}
