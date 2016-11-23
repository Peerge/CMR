package com.example.piotrek.cmr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NamePlateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_plate);
        setColor();

    }

    public void setColor() {
        LinearLayout l1 = (LinearLayout) findViewById(R.id.row1);
        LinearLayout l3 = (LinearLayout) findViewById(R.id.row3);
        LinearLayout l5 = (LinearLayout) findViewById(R.id.row5);

        l1.setBackgroundColor(getResources().getColor(R.color.table));
        l3.setBackgroundColor(getResources().getColor(R.color.table));
        l5.setBackgroundColor(getResources().getColor(R.color.table));
    }


}