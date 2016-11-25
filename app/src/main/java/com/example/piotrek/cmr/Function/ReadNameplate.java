package com.example.piotrek.cmr.Function;


import com.example.piotrek.cmr.Connection.ConnectThread;
import com.example.piotrek.cmr.Detail.NameplateDetail;
import com.example.piotrek.cmr.Util.Converter;

import java.util.ArrayList;
import java.util.Iterator;

public class ReadNameplate {


    public NameplateDetail readNameplate(ConnectThread ct) {
        NameplateDetail nameplateDetails = new NameplateDetail();
        try {
            String data = Converter.convertToHex(ct.receive(ct.is));
            String responseNameplate = "";
            ArrayList<String> responseNamePlateList = new ArrayList();
            for (int i = 0; i < data.length(); i += 2) {
                if (data.substring(i, i + 2).equals("00")) {
                    responseNamePlateList.add(responseNameplate);
                    responseNameplate = "";
                } else {
                    responseNameplate = responseNameplate + data.substring(i, i + 2);
                }
            }
            if (responseNamePlateList.size() >= 1) {
                nameplateDetails.setManufacturer(Converter.convertHexToString(responseNamePlateList.get(0)));
            } else {
                nameplateDetails.setManufacturer("Brak danych");
            }
            if (responseNamePlateList.size() >= 2) {
                nameplateDetails.setDeviceType(Converter.convertHexToString(responseNamePlateList.get(1)));
            } else {
                nameplateDetails.setDeviceType("Brak danych");
            }
            if (responseNamePlateList.size() >= 3) {
                nameplateDetails.setDeviceId(Converter.convertHexToString(responseNamePlateList.get(2)));
            } else {
                nameplateDetails.setDeviceId("Brak danych");
            }
            if (responseNamePlateList.size() >= 4) {
                nameplateDetails.setArrayDpVersion(Converter.convertHexToString(responseNamePlateList.get(3)));
            } else {
                nameplateDetails.setArrayDpVersion("Brak danych");
            }
            if (responseNamePlateList.size() >= 5) {
                nameplateDetails.setArrayZdVersion(Converter.convertHexToString(responseNamePlateList.get(4)));
            } else {
                nameplateDetails.setArrayZdVersion("Brak danych");
            }
            if (responseNamePlateList.size() >= 7) {
                nameplateDetails.setOtherInfo(Converter.convertHexToString(responseNamePlateList.get(6)));
            } else {
                nameplateDetails.setOtherInfo("Brak danych");
            }
            String allNameplateData = "";
            Iterator it = responseNamePlateList.iterator();
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
