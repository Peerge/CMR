package com.example.piotrek.cmr.Activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.piotrek.cmr.Connection.ConnectThread;
import com.example.piotrek.cmr.Detail.NameplateDetail;
import com.example.piotrek.cmr.Function.GetNameplate;
import com.example.piotrek.cmr.Function.ReadNameplate;
import com.example.piotrek.cmr.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private String ip = "217.153.10.141";
    private int port = 6503;
    private String gmAddress = "11500";
    private String hostAddress = "10.141";
    private TextView manufacturerText;
    private TextView deviceTypeText;
    private TextView deviceIdText;
    private TextView dpVersionText;
    private TextView zdVersionText;
    private TextView otherText;
    NameplateDetail np;

    GetNameplate getnameplate = new GetNameplate(this.gmAddress, this.hostAddress);
    ConnectThread connectThread;

    public MainActivity() throws Exception {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Button btn_connect = (Button) this.findViewById(R.id.btn_connect);
        Button btn_restart = (Button) this.findViewById(R.id.btn_restart);



        btn_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG, "Connection starting");
                Thread background = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            connectThread = new ConnectThread(ip, port);
                            connectThread.connectSocket();
                            connectThread.send(connectThread.os, getnameplate.getQuestion());
                            ReadNameplate rnp = new ReadNameplate();
                            rnp.readNameplate(connectThread);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
                background.start();
                showNameplate();
                setText();
            }
        });

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

    public void showNameplate() {
        FrameLayout contentFrame = (FrameLayout) findViewById(R.id.contentFrame);
        View child = getLayoutInflater().inflate(R.layout.activity_name_plate, null);
        contentFrame.addView(child);

    }

    public void setText() {
        try {
            manufacturerText = (TextView) findViewById(R.id.manufacturer);
            deviceTypeText = (TextView) findViewById(R.id.deviceType);
            deviceIdText = (TextView) findViewById(R.id.deviceId);
            dpVersionText = (TextView) findViewById(R.id.dpVersion);
            zdVersionText = (TextView) findViewById(R.id.zdVersion);
            otherText = (TextView) findViewById(R.id.other);

            manufacturerText.setText(np.getManufacturer());
            deviceTypeText.setText(np.getDeviceType());
            deviceIdText.setText(np.getDeviceId());
            dpVersionText.setText(np.getArrayDpVersion());
            zdVersionText.setText(np.getArrayZdVersion());
            otherText.setText(np.getOtherInfo());
        } catch (NullPointerException e) {
            manufacturerText.setText(R.string.NullExceptCase);
            deviceTypeText.setText(R.string.NullExceptCase);
            deviceIdText.setText(R.string.NullExceptCase);
            dpVersionText.setText(R.string.NullExceptCase);
            zdVersionText.setText(R.string.NullExceptCase);
            otherText.setText(R.string.NullExceptCase);
        }
    }
}
