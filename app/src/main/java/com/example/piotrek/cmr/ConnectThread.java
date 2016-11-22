package com.example.piotrek.cmr;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by piotrek on 21.11.16.
 */

public class ConnectThread implements Runnable {

    private Socket socket;
    private String ip = "217.153.10.141";
    private int port = 6503;
    public boolean isConnect;
    DataInputStream is;
    DataOutputStream os;


    public ConnectThread(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void run() {
        try {
            socket = new Socket(ip, port);
            is = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            os = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            isConnect = socket.isConnected();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public String getResponse() {
        String response = "";
        try {
            byte[] buffer = receive(is);
            response = new String(buffer);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }


    public static byte[] receive(DataInputStream is) throws Exception {
        try {
            byte[] inputData = new byte[1024];
            is.read(inputData);
            return inputData;
        } catch (Exception exception) {
            throw exception;
        }
    }

    public static void send(DataOutputStream os, byte[] byteData) throws Exception {
        try {
            os.write(byteData);
            os.flush();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static byte[] getMsg() {
        byte first = 0x68;
        byte frameLength = 0x5;
        byte[] receiveAdr = new byte[2];
        receiveAdr[0] = (byte) 0xff;
        receiveAdr[1] = (byte) 0xff;
        byte[] sendAdr = new byte[2];
        sendAdr[0] = 0x1;
        sendAdr[1] = 0x15;
        byte msg = 0x09;
        byte answer = 0x00;
        byte[] CRC = new byte[2];
        CRC[0] = 0x4A;
        CRC[1] = (byte) 0x98;
        byte end = 0x16;

        byte[] request = new byte[11];
        request[0] = first;
        request[1] = frameLength;
        request[2] = receiveAdr[0];
        request[3] = receiveAdr[1];
        request[4] = sendAdr[0];
        request[5] = sendAdr[1];
        request[6] = msg;
        request[7] = answer;
        request[8] = CRC[0];
        request[9] = CRC[1];
        request[10] = end;

        return request;

    }


}
