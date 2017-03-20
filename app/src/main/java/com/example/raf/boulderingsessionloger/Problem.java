package com.example.raf.boulderingsessionloger;

/**
 * Created by raf on 17/03/2017.
 */

class Problem {

    int grade;
    int attemptsNumber;
    int problemNumber;
    boolean isTraining;
    boolean problemCompleted;
    String circuitName;


    Problem(int grade, int attemptsNumber, int problemNumber,boolean isTraining,boolean problemCompleted, String circuitName) {
        this.grade = grade;
        this.attemptsNumber = attemptsNumber;
        this.problemNumber = problemNumber;
        this.isTraining = isTraining;
        this.problemCompleted = problemCompleted;
        this.circuitName = circuitName;
    }
}
