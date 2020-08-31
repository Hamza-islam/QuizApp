package com.hamza.quizapp;

public class QuizModel {
    private int mQuestion; // declare private so we can access through getters
    private boolean mAnswer;// declare private so we can access through getters

    public QuizModel(int question, boolean answer) {    // constructor for initializing  the question and answer
        mQuestion = question;
        mAnswer = answer;
    }

    public void setQuestion(int question) {
        mQuestion = question;
    }

    public int getQuestion() {  // they are getters because my instance variable is private of type int
        return mQuestion;
    }


    public boolean isAnswer() { //this is another getter of type boolean
        return mAnswer;
    }

    public void setAnswer(boolean answer) {
        mAnswer = answer;
    }
}

// i am using commects for my better understanding please ignore this if you found any mistake