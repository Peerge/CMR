package com.example.piotrek.cmr.Util;



public class Crc {
    public static final int polynomial = 4129;

    public static byte[] getCRChexByte(byte[] args) {
        int crc = 0;
        for (byte b : args) {
            for (int i = 0; i < 8; i++) {
                boolean bit;
                boolean c15;
                bit = ((b >> (7 - i)) & 1) == 1;
                c15 = ((crc >> 15) & 1) == 1;
                crc <<= 1;
                if (c15 == bit) {
                    crc ^= polynomial;
                }
            }
        }

        return new byte[]{(byte) (crc >>> 8), (byte) crc};
    }
}
