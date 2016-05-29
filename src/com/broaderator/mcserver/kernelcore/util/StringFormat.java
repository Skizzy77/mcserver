package com.broaderator.mcserver.kernelcore.util;

public class StringFormat {
    public static String f(String expr, Object... repl) {
        // Use {0}, {1}, {2}...
        for (int i = 0; i < repl.length; i++) {
            expr = expr.replace("{" + String.valueOf(i) + "}", repl[i].toString());
        }
        return expr;
    }
}
