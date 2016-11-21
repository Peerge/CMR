package com.example.piotrek.cmr;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private String ip = "217.153.10.141";
    private int port = 6503;
    private Button btn_connect = null;
    private Button btn_send = null;
    private TextView text_receive = null;


    ConnectThread connectThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_connect = (Button) this.findViewById(R.id.btn_connect);
        btn_send = (Button) this.findViewById(R.id.btn_send);
        text_receive = (TextView) this.findViewById(R.id.text_receive);


        btn_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectThread = new ConnectThread(ip, port);
                new Thread(connectThread).start();
                Log.d(TAG, "Connection starting");
                if (connectThread.isConnect) {
                    btn_connect.setText("Disconnect");
                }
            }
        });
        //TODO Input nie zwraca NIC!!!!!!!!!
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            connectThread.send(connectThread.os, connectThread.getMsg());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        text_receive.setText(connectThread.getResponse());
                    }

                };



            }
        });

    }
}
