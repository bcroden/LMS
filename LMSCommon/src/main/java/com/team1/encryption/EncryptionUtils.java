package com.team1.encryption;

public class EncryptionUtils {
    public static byte[] concatenateArrays(byte[] a, byte[] b) {
        if (a == null || a.length == 0)
            return b;
        if (b == null || b.length == 0)
            return a;

        byte[] total = new byte[a.length + b.length];
        System.arraycopy(a, 0, total, 0, a.length);
        System.arraycopy(b, 0, total, a.length, b.length);
        return total;
    }
    
    public static byte[] getSubArray(byte[] a, int start, int amount) {
        if (start > a.length)
            return null;
        if (start + amount > a.length)
            amount = a.length - start;

        byte[] bytes = new byte[amount];
        System.arraycopy(a, start, bytes, 0, amount);
        return bytes;
    }
}