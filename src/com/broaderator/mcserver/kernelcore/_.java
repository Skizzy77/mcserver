package com.broaderator.mcserver.kernelcore;

import java.util.Random;

public class _ {
    // Utilities

    // Min inclusive, Max exclusive
    public static int randomInt(Random generator, int min, int max) {
        return generator.nextInt(max - min) + min;
    }

    public static String toHexString(byte[] bytes) {
        String output = "";
        for (byte b : bytes) {
            output += String.format("%02x", b);
        }
        return output;
    }
}
