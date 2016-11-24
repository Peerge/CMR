package com.example.piotrek.cmr.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.piotrek.cmr.Detail.NameplateDetail;
import com.example.piotrek.cmr.R;

public class NameplateActivity extends AppCompatActivity {
    private TextView manufacturerText;
    private TextView deviceTypeText;
    private TextView deviceIdText;
    private TextView dpVersionText;
    private TextView zdVersionText;
    private TextView otherText;
    NameplateDetail np;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_plate);
        setColor();
        setText();
    }

    public void setColor() {
        LinearLayout l1 = (LinearLayout) findViewById(R.id.row1);
        LinearLayout l3 = (LinearLayout) findViewById(R.id.row3);
        LinearLayout l5 = (LinearLayout) findViewById(R.id.row5);

        l1.setBackgroundColor(getResources().getColor(R.color.table));
        l3.setBackgroundColor(getResources().getColor(R.color.table));
        l5.setBackgroundColor(getResources().getColor(R.color.table));
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
            manufacturerText.setText("Brak danych");
            deviceTypeText.setText("Brak danych");
            deviceIdText.setText("Brak danych");
            dpVersionText.setText("Brak danych");
            zdVersionText.setText("Brak danych");
            otherText.setText("Brak danych");
        }
    }


}