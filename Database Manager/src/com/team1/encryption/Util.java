package com.team1.encryption;

public class Util {
    public static byte[] concatenateArrays(byte[] a, byte[] b) {
        if (a == null || a.length == 0)
            return b;
        if (b == null || b.length == 0)
            return a;

        byte[] total = new byte[a.length + b.length];
        for (int i = 0; i < a.length; i++)
            total[i] = a[i];
        for (int i = 0; i < b.length; i++)
            total[i + a.length] = b[i];
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