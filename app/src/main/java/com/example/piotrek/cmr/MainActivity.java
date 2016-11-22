package com.example.piotrek.cmr;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private String ip = "217.153.10.141";
    private int port = 6503;
    private Button btn_connect = null;
    private Button btn_restart = null;
    private TextView text_receive = null;


    ConnectThread connectThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btn_connect = (Button) this.findViewById(R.id.btn_connect);
        btn_restart = (Button) this.findViewById(R.id.btn_restart);
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

        btn_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);//Menu Resource, Menu
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.restart:
                recreate();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
