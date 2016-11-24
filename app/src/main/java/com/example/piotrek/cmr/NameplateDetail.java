package com.example.piotrek.cmr;


import android.widget.TextView;

public class NameplateDetail {
    private String allInformation;
    private String manufacturer;
    private String deviceType;
    private String deviceId;
    private String arrayDpVersion;
    private String arrayZdVersion;
    private String otherInfo;

    public NameplateDetail() {
        this.manufacturer = new String();
        this.deviceType = new String();
        this.deviceId = new String();
        this.arrayDpVersion = new String();
        this.arrayZdVersion = new String();
        this.otherInfo = new String();
        this.allInformation = new String();
    }

    public NameplateDetail(String manufacturer, String deviceType, String deviceId, String arrayDpVersion, String arrayZdVersion, String otherInfo) {

        this.manufacturer = new String();
        this.deviceType = new String();
        this.deviceId = new String();
        this.arrayDpVersion = new String();
        this.arrayZdVersion = new String();
        this.otherInfo = new String();
        this.allInformation = new String();

        this.manufacturer = manufacturer;
        this.deviceType = deviceType;
        this.deviceId = deviceId;
        this.arrayDpVersion = arrayDpVersion;
        this.arrayZdVersion = arrayZdVersion;
        this.otherInfo = otherInfo;
        this.allInformation = allInformation;

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

    public String getAllInformation() {
        return this.allInformation;
    }

    public void setAllInformation(String otherInfo) {
        this.allInformation = allInformation;
    }


}
