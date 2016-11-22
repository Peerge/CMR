package com.example.piotrek.cmr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private String ip = "172.21.77.137";
    private int port = 4444;
    private Button btn_connect = null;
    private Button btn_send = null;
    private TextView text_receive = null;


    ConnectThread connectThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_connect = (Button) this.findViewById(R.id.btn_connect);
        btn_send = (Button) this.findViewById(R.id.btn_restart);
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
                try {
                    connectThread.send(connectThread.os, connectThread.getMsg());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                text_receive.setText(connectThread.getResponse());
            }
        });
        //TODO Input zwraca NullPointerException

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
            }
        });

    }
}
