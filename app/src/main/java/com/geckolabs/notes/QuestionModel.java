package com.geckolabs.notes;

public class QuestionModel {

    private Integer qId;
    private String qType;
    private String qText;
    private Integer quizId;

    public QuestionModel(Integer qId, String qType, String qText, Integer quizId) {
        this.qId = qId;
        this.qType = qType;
        this.qText = qText;
        this.quizId = quizId;
    }

    public Integer getqId() {
        return qId;
    }

    public void setqId(Integer qId) {
        this.qId = qId;
    }

    public String getqType() {
        return qType;
    }

    public void setqType(String qType) {
        this.qType = qType;
    }

    public String getqText() {
        return qText;
    }

    public void setqText(String qText) {
        this.qText = qText;
    }

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }
}
