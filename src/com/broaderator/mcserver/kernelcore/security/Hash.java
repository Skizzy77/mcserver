package com.broaderator.mcserver.kernelcore.security;

import com.broaderator.mcserver.kernel._;

import java.util.Random;

public class Hash {
    private static final Random RAND = new Random();

    public static final int LONG = 64;
    public static final int MEDIUM = 32;
    public static final int SHORT = 16;

    // -----

    public static Hash randomHash(int size) {
        byte[] bytes = new byte[size];
        RAND.nextBytes(bytes);
        return new Hash(bytes);
    }

    // -----

    private byte[] hCode;

    Hash(byte[] hcode) {
        hCode = hcode;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Hash && ((Hash) obj).hCode == this.hCode;
    }

    @Override
    public String toString() {
        return _.toHexString(hCode);
    }
}
