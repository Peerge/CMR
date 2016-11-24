package com.example.piotrek.cmr.Function;


import com.example.piotrek.cmr.Util.Converter;
import com.example.piotrek.cmr.Util.Crc;

import java.nio.ByteBuffer;

public class MessageFrame {

    protected static final byte START_BYTE = (byte) 104;
    protected static final byte STOP_BYTE = (byte) 22;
    protected byte[] msgFrame;
    protected byte[] msgFrameAll;

    public MessageFrame() {
        this.msgFrame = null;
        this.msgFrameAll = null;
    }

    public byte[] getQuestion() {
        return this.msgFrameAll;
    }

    protected void prepareCheckSum() throws Exception {
        System.arraycopy(Crc.getCRChexByte(this.msgFrame), 0, this.msgFrameAll, this.msgFrameAll.length - 3, 2);
        System.arraycopy(this.msgFrame, 0, this.msgFrameAll, 1, this.msgFrame.length);
        this.msgFrameAll[this.msgFrameAll.length - 1] = STOP_BYTE;
    }

    protected void prepareQuestion(String gmAdress, String hostAdress) throws Exception {
        this.msgFrameAll[0] = START_BYTE;
        if (gmAdress.length() == 0) {
            System.arraycopy(Converter.hexStringToByteArray(Converter.makeHexFromString("65535")), 0, this.msgFrame, 2, 2);
        } else {
            System.arraycopy(Converter.hexStringToByteArray(Converter.makeHexFromString(gmAdress)), 0, this.msgFrame, 2, 2);
        }
        System.arraycopy(Converter.hexStringToByteArray(hostAdress), 0, this.msgFrame, 4, 2);
    }

    protected void setQuestionLenght(int sizeDataInFrame) {
        byte[] questionLenght = ByteBuffer.allocate(4).putInt(sizeDataInFrame).array();
        this.msgFrame[0] = questionLenght[3];
        this.msgFrame[1] = questionLenght[2];
    }

    protected void setFunctionCode(byte function_number) {
        this.msgFrame[6] = function_number;
    }
}
