package com.example.piotrek.cmr.Function;


import com.example.piotrek.cmr.Util.Converter;
import com.example.piotrek.cmr.Util.Crc;

import java.nio.ByteBuffer;

public class MessageFrame {

    protected static final byte START_BYTE = (byte) 104;
    protected static final byte STOP_BYTE = (byte) 22;
    protected byte[] question;
    protected byte[] questionFrame;

    public MessageFrame() {
        this.question = null;
        this.questionFrame = null;
    }

    public byte[] getQuestion() {
        return this.questionFrame;
    }

    protected void getCrc() throws Exception {
        System.arraycopy(Crc.getCRChexByte(this.question), 0, this.questionFrame, this.questionFrame.length - 3, 2);
        System.arraycopy(this.question, 0, this.questionFrame, 1, this.question.length);
        this.questionFrame[this.questionFrame.length - 1] = STOP_BYTE;
    }

    protected void prepareQuestion(String gmAddress, String hostAddress) throws Exception {
        this.questionFrame[0] = START_BYTE;
        System.arraycopy(Converter.hexStringToByteArray(Converter.makeHexFromString(gmAddress)), 0, this.question, 2, 2);
        System.arraycopy(Converter.hexStringToByteArray(hostAddress), 0, this.question, 4, 2);
    }

    protected void setQuestionLenght(int sizeDataInFrame) {
        byte[] questionLenght = ByteBuffer.allocate(4).putInt(sizeDataInFrame).array();
        this.question[0] = questionLenght[3];
        this.question[1] = questionLenght[2];
    }

    protected void setFunctionCode(byte function_number) {
        this.question[6] = function_number;
    }
}
