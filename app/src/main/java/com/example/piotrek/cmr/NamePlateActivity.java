package com.example.piotrek.cmr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class NamePlateActivity extends AppCompatActivity {
    private TextView manufacturerText;
    private TextView deviceTypeText;
    private TextView deviceIdText;
    private TextView dpVersionText;
    private TextView zdVersionText;
    private TextView otherText;
    NamePlateDetail np;
    public byte[] responseInByte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_plate);
        setColor();
        setText();
        readNameplate();
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
    public byte[] getReadedDataByteWithoutCRC() {
        return Arrays.copyOfRange(this.responseInByte, 8, this.responseInByte.length - 3);
    }

    public NamePlateDetail readNameplate() {
        NamePlateDetail nameplateDetails = new NamePlateDetail();
        try {
            String data = Converter.convertToHex(getReadedDataByteWithoutCRC());
            String splitData = "";
            ArrayList<String> splitDataList = new ArrayList();
            for (int i = 0; i < data.length(); i += 2) {
                if (data.substring(i, i + 2).equals("00")) {
                    splitDataList.add(splitData);
                    splitData = BuildConfig.FLAVOR;
                } else {
                    splitData = splitData + data.substring(i, i + 2);
                }
            }
            if (splitDataList.size() >= 1) {
                nameplateDetails.setManufacturer(Converter.convertHexToString((String) splitDataList.get(0)));
            } else {
                nameplateDetails.setManufacturer("Brak danych");
            }
            if (splitDataList.size() >= 2) {
                nameplateDetails.setDeviceType(Converter.convertHexToString((String) splitDataList.get(1)));
            } else {
                nameplateDetails.setDeviceType("Brak danych");
            }
            if (splitDataList.size() >= 3) {
                nameplateDetails.setDeviceId(Converter.convertHexToString((String) splitDataList.get(2)));
            } else {
                nameplateDetails.setDeviceId("Brak danych");
            }
            if (splitDataList.size() >= 4) {
                nameplateDetails.setArrayDpVersion(Converter.convertHexToString((String) splitDataList.get(3)));
            } else {
                nameplateDetails.setArrayDpVersion("Brak danych");
            }
            if (splitDataList.size() >= 5) {
                nameplateDetails.setArrayZdVersion(Converter.convertHexToString((String) splitDataList.get(4)));
            } else {
                nameplateDetails.setArrayZdVersion("Brak danych");
            }
            if (splitDataList.size() >= 7) {
                nameplateDetails.setOtherInfo(Converter.convertHexToString((String) splitDataList.get(6)));
            } else {
                nameplateDetails.setOtherInfo("Brak danych");
            }
            String allNameplateData = "";
            Iterator it = splitDataList.iterator();
            while (it.hasNext()) {
                allNameplateData = allNameplateData + Converter.convertHexToString((String) it.next()) + " ";
            }
            nameplateDetails.setAllInformation(allNameplateData);
            return nameplateDetails;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}