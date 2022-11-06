package com.geckolabs.notes;

public class AnswerModel {

    private Integer ansID;
    private String qAns;
    private  Integer correct;
    private  Integer quesId;

    public AnswerModel(Integer ansID, String qAns, Integer correct, Integer quesId) {
        this.ansID = ansID;
        this.qAns = qAns;
        this.correct = correct;
        this.quesId = quesId;
    }

    public Integer getAnsID() {
        return ansID;
    }

    public void setAnsID(Integer ansID) {
        this.ansID = ansID;
    }

    public String getqAns() {
        return qAns;
    }

    public void setqAns(String qAns) {
        this.qAns = qAns;
    }

    public Integer getCorrect() {
        return correct;
    }

    public void setCorrect(Integer correct) {
        this.correct = correct;
    }

    public Integer getQuesId() {
        return quesId;
    }

    public void setQuesId(Integer quesId) {
        this.quesId = quesId;
    }
}
