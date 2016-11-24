package com.example.piotrek.cmr;



public class Crc {
    public static final int polynomial = 4129;

    public static String getCRCHexString(byte[] args) {
        int crc = 0;
        for (byte b : args) {
            for (int i = 0; i < 8; i++) {
                boolean bit;
                boolean c15;
                if (((b >> (7 - i)) & 1) == 1) {
                    bit = true;
                } else {
                    bit = false;
                }
                if (((crc >> 15) & 1) == 1) {
                    c15 = true;
                } else {
                    c15 = false;
                }
                crc <<= 1;
                if (!(c15 ^ bit)) {
                    crc ^= polynomial;
                }
            }
        }
        return Integer.toHexString(crc );
    }

    public static byte[] getCRChexByte(byte[] args) {
        int crc = 0;
        for (byte b : args) {
            for (int i = 0; i < 8; i++) {
                boolean bit;
                boolean c15;
                if (((b >> (7 - i)) & 1) == 1) {
                    bit = true;
                } else {
                    bit = false;
                }
                if (((crc >> 15) & 1) == 1) {
                    c15 = true;
                } else {
                    c15 = false;
                }
                crc <<= 1;
                if (!(c15 ^ bit)) {
                    crc ^= polynomial;
                }
            }
        }

        return new byte[]{(byte) (crc >>> 8), (byte) crc};
    }
}
