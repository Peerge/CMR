package com.example.piotrek.cmr;



public class GetNameplate extends MessageFrame{
    private static final byte MSG_NUMBER = (byte) 9;
    private static final int MSG_LENGHT = 5;

    public GetNameplate(String gmAdress, String hostAdress) throws Exception {
        this.msgFrame = new byte[7];
        this.msgFrameAll = new byte[11];
        prepareQuestion(gmAdress, hostAdress);
        setFunctionCode(MSG_NUMBER);
        setQuestionLenght(MSG_LENGHT);
        prepareCheckSum();
    }
}
