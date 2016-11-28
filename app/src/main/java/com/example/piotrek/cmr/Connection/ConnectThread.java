package com.example.piotrek.cmr.Connection;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;


public class ConnectThread implements Runnable {

    private Socket socket;
    private String ip = "217.153.10.141";
    private int port = 6503;
    private int timeOut = 5000;
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
            socket.connect(new InetSocketAddress(this.ip, this.port), this.timeOut);
            os = new BufferedOutputStream(socket.getOutputStream());
            is = new BufferedInputStream(socket.getInputStream());
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
            byte[] inputData = new byte[16];
            is.read(inputData, 0, inputData.length);
            return inputData;
        } catch (Exception exception) {
            throw exception;
        }
    }

    public static void send(BufferedOutputStream os, byte[] byteData) throws Exception {
        try {
            os.write(byteData, 0, byteData.length);
            os.flush();
            os.close();
            Log.d("OutputStream", String.valueOf(os));

        } catch (Exception e) {
            e.getStackTrace();
        }
    }

}
