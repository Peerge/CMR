package com.example.piotrek.cmr;


public class Converter {

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[(len / 2)];
        for (int i = 0; i < len - 1; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static byte[] hexStringToByteArray(String s, Integer count) {
        int len = s.length();
        if (len > count.intValue() * 2) {
            len = count.intValue() * 2;
        }
        byte[] data = new byte[count.intValue()];
        if (len == 1) {
            data[0] = (byte) Character.digit(s.charAt(0), 16);
        } else {
            int i = 0;
            while (i < len && i < len - 1) {
                data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
                i += 2;
            }
        }
        return data;
    }

    public static String convertHexToString(String hex) {
        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < hex.length() - 1; i += 2) {
            int decimal = Integer.parseInt(hex.substring(i, i + 2), 16);
            sb.append((char) decimal);
            temp.append(decimal);
        }
        return sb.toString();
    }

    public static String makeHexFromString(String addressGM) throws Exception {
        String temp = Integer.toHexString(Integer.parseInt(addressGM)).toString();
        return "0" + temp + "00";
    }

    public static String convertToHex(byte[] data) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 15;
            int two_halfs = 0;
            while (true) {
                if (halfbyte < 0 || halfbyte > 9) {
                    buf.append((char) ((halfbyte - 10) + 97));
                } else {
                    buf.append((char) (halfbyte + 48));
                }
                halfbyte = data[i] & 15;
                int two_halfs2 = two_halfs + 1;
                if (two_halfs >= 1) {
                    break;
                }
                two_halfs = two_halfs2;
            }
        }
        return buf.toString();
    }

}
