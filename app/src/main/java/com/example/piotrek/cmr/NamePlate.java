package com.example.piotrek.cmr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.widget.LinearLayout;

public class NamePlate extends AppCompatActivity {
    private String manufacturer;
    private String deviceType;
    private String deviceId;
    private String arrayDpVersion;
    private String arrayZdVersion;
    private String otherInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_plate);

        LinearLayout l1 = (LinearLayout) findViewById(R.id.row1);
        LinearLayout l3 = (LinearLayout) findViewById(R.id.row3);
        LinearLayout l5 = (LinearLayout) findViewById(R.id.row5);

        l1.setBackgroundColor(getResources().getColor(R.color.table));
        l3.setBackgroundColor(getResources().getColor(R.color.table));
        l5.setBackgroundColor(getResources().getColor(R.color.table));
    }


    public NamePlate(String manufacturer, String deviceType, String deviceId, String arrayDpVersion, String arrayZdVersion, String otherInfo) {

        this.manufacturer = new String();
        this.deviceType = new String();
        this.deviceId = new String();
        this.arrayDpVersion = new String();
        this.arrayZdVersion = new String();
        this.otherInfo = new String();

        this.manufacturer = manufacturer;
        this.deviceType = deviceType;
        this.deviceId = deviceId;
        this.arrayDpVersion = arrayDpVersion;
        this.arrayZdVersion = arrayZdVersion;
        this.otherInfo = otherInfo;

    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getArrayDpVersion() {
        return this.arrayDpVersion;
    }

    public void setArrayDpVersion(String arrayDpVersion) {
        this.arrayDpVersion = arrayDpVersion;
    }

    public String getArrayZdVersion() {
        return this.arrayZdVersion;
    }

    public void setArrayZdVersion(String arrayZdVersion) {
        this.arrayZdVersion = arrayZdVersion;
    }

    public String getOtherInfo() {
        return this.otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

}