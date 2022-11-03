package com.geckolabs.notes;

public class QuizModel {

    private String name;
    private Integer quizId;

    public QuizModel(Integer quizId, String name) {
        this.quizId = quizId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }
}
