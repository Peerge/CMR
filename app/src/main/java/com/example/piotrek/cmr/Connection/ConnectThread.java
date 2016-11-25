package com.example.piotrek.cmr.Connection;

import android.util.Log;

import com.example.piotrek.cmr.Util.Crc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;


public class ConnectThread implements Runnable {

    private Socket socket;
    private String ip = "217.153.10.141";
    private int port = 6503;
    public boolean isConnect;
    public BufferedInputStream is;
    public BufferedOutputStream os;


    public ConnectThread(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void run() {
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(this.ip, this.port));
            is = new BufferedInputStream(socket.getInputStream());
            os = new BufferedOutputStream(socket.getOutputStream());
            isConnect = socket.isConnected();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

//    public byte[] getResponse() {
//        byte[] buffer = null;
//        try {
//            while (is != null) {
//                buffer = receive(is);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return buffer;
//    }


    public static byte[] receive(BufferedInputStream is) throws Exception {
        try {
            byte[] inputData = null;
            while(is!=null) {
                is.read(inputData);

            }
            return inputData;
        } catch (Exception exception) {
            throw exception;
        }
    }

    public static void send(BufferedOutputStream os, byte[] byteData) throws Exception {
        try {
            os.write(byteData);
            os.flush();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

//    public static byte[] getMsg() {
//
//        int crcInt[] = new int[4];
//        crcInt[0] = 5;
//        crcInt[1] = 65535;
//        crcInt[2] = 277;
//        crcInt[3] = 9;
//
//        int crc = GenerateChecksumCRC16(crcInt);
//        String textCrc = String.valueOf(crc);
//        Log.d("CRC", textCrc );
//
//        byte first = (byte) 104;
//        byte frameLength = 0x5;
//        byte[] receiveAdr = new byte[2];
//        receiveAdr[0] = (byte) 0xaa ;
//        receiveAdr[1] = (byte) 0xaa ;
//        byte[] sendAdr = new byte[2];
//        sendAdr[0] = 0x011;
//        sendAdr[1] = (byte) 0x500 ;
//        byte msg = 0x9;
//        byte[] CRC = new byte[2];
//        CRC[0] = (byte) 0x222;
//        CRC[1] = (byte) 0x8fae;
//        byte end = (byte) 22;
//
//        byte[] CRCC = new byte[] {frameLength, receiveAdr[0], receiveAdr[1], sendAdr[0], sendAdr[1], msg };
//
//        byte[] CRCCC = Crc.getCRChexByte(CRCC);
//        String cos = Crc.getCRCHexString(CRCCC);
//        Log.d("Nowe CRC", cos);
//
//
//        byte[] request = new byte[10];
//        request[0] = first;
//        request[1] = frameLength;
//        request[2] = receiveAdr[0];
//        request[3] = receiveAdr[1];
//        request[4] = sendAdr[0];
//        request[5] = sendAdr[1];
//        request[6] = msg;
//        request[7] = CRC[0];
//        request[8] = CRC[1];
//        request[9] = end;
//
//        return request;
//
//    }
//
//    static public int GenerateChecksumCRC16(int bytes[]) {
//
//        int crc = 0xFFFF;
//        int temp;
//        int crc_byte;
//
//        for (int byte_index = 0; byte_index < bytes.length; byte_index++) {
//
//            crc_byte = bytes[byte_index];
//
//            for (int bit_index = 0; bit_index < 8; bit_index++) {
//
//                temp = ((crc >> 15)) ^ ((crc_byte >> 7));
//
//                crc <<= 1;
//                crc &= 0xFFFF;
//
//                if (temp > 0) {
//                    crc ^= 0x1021;
//                    crc &= 0xFFFF;
//                }
//
//                crc_byte <<=1;
//                crc_byte &= 0xFF;
//
//            }
//        }
//
//        return crc;
//    }


}
