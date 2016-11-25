package com.example.piotrek.cmr.Function;



public class GetNameplate extends MessageFrame{
    private static final byte MSG_NUMBER = (byte) 9;
    private static final int MSG_LENGHT = 5;

    public GetNameplate(String gmAdress, String hostAdress) throws Exception {
        this.question = new byte[7];
        this.questionFrame = new byte[11];
        prepareQuestion(gmAdress, hostAdress);
        setFunctionCode(MSG_NUMBER);
        setQuestionLenght(MSG_LENGHT);
        getCrc();
    }
}
