package org.quizLib;

import org.enums.VerdictEnum;

import java.util.ArrayList;
import java.util.List;
/**
 * The {@code Statistics} class is responsible for tracking a student's quiz performance,
 * including the number of regular and revision attempts, quiz scores, and the student's verdict.
 * The verdict can be {@code PASS}, {@code FAIL}, or {@code TBD} (To Be Determined).
 */
public class Statistics {
    private int numberOfRegAttempts;
    private int numberOfRevisions;
    private List<Double> quizScores;
    private VerdictEnum verdict;

    /**
     * Returns the number of regular quiz attempts the student has made.
     *
     * @return the number of regular quiz attempts
     */
    public int getNumberOfRegAttempts() {
        return numberOfRegAttempts;
    }
    /**
     * Constructs a new {@code Statistics} object with the initial values.
     * The verdict is set to {@code TBD}, and the quiz scores list is initialized empty.
     */
    public Statistics() {
        this.numberOfRegAttempts = 0;
        this.numberOfRevisions = 0;
        this.quizScores = new ArrayList<>();
        this.verdict = VerdictEnum.TBD; // Initial verdict is TBD
    }
    /**
     * Returns the current verdict for the student, which can be {@code PASS}, {@code FAIL}, or {@code TBD}.
     * @return the current verdict for the student
     */
    public VerdictEnum getVerdict() {
        return verdict;
    }
    /**
     * Sets the student's verdict based on their performance in quizzes.
     * @param verdict the verdict to be assigned to the student
     */
    public void setVerdict(VerdictEnum verdict) {
        this.verdict = verdict;
    }

//    public void setNumberOfRegAttempts(int numberOfRegAttempts) {
//        this.numberOfRegAttempts = numberOfRegAttempts;
//    }
    /**
     * Returns the number of revision attempts the student has made.
     *
     * @return the number of revision quiz attempts
     */
    public int getNumberOfRevisions() {
        return numberOfRevisions;
    }

//    public void setNumberOfRevisions(int numberOfRevisions) {
//        this.numberOfRevisions = numberOfRevisions;
//    }

    /**
     * Returns the list of quiz scores that the student has received.
     *
     * @return the list of quiz scores
     */
    public List<Double> getQuizScores() {
        return quizScores;
    }
    /**
     * Sets the list of quiz scores for the student.
     *
     * @param quizScores the list of quiz scores to be assigned
     */
    public void setQuizScores(List<Double> quizScores) {
        this.quizScores = quizScores;
    }
    /**
     * Increments the number of regular quiz attempts.
     */
    public void incrementRegAttempts() {
        this.numberOfRegAttempts++;
    }
    /**
     * Increments the number of revision quiz attempts.
     */
    public void incrementRevisions() {
        this.numberOfRevisions++;
    }
    /**
     * Adds a quiz score to the student's statistics and updates the verdict based on the score and quiz type.
     * The verdict is updated depending on whether the quiz was a regular or revision quiz.
     *
     * @param score the score received for the quiz
     * @param v_enum the type of quiz (regular or revision)
     */
    public void addScores(double score,VerdictEnum v_enum) {
        //checkAndSetVerdict(score);
        checkAndSetVerdict(score,v_enum);
        this.quizScores.add(score);
    }
    /**
     * Checks the student's performance and updates their verdict accordingly.
     * A score of 50% or more in a regular quiz is considered a pass. If the student has attempted more than two regular
     * or revision quizzes without passing, they are assigned a fail verdict.
     *
     * @param score the score achieved by the student in the most recent quiz
     * @param v_enum the type of quiz (regular or revision)
     */
    public void checkAndSetVerdict(double score,VerdictEnum v_enum) {
        // Verdict is set to FAIL if the student fails after 2 regular or revision attempts.
        if (numberOfRegAttempts >= 2) {
            setVerdict(VerdictEnum.FAIL);
        } else if (numberOfRevisions >= 2) {
            setVerdict(VerdictEnum.FAIL);
        }
        // Verdict is set to PASS if the student scores 50% or more in a regular quiz
        else if (score >= 0.5 && v_enum.equals(VerdictEnum.REGULAR) ) {
            setVerdict(VerdictEnum.PASS);
        }
    }
    /**
     * Returns a string representation of the {@code Statistics} object.
     * The string includes the number of regular quiz attempts, revision attempts,
     * list of quiz scores, and the student's current verdict.
     *
     * @return a string representation of the {@code Statistics} object
     */
    @Override
    public String toString() {
        return "Statistics{" +
                "numberOfRegAttempts=" + numberOfRegAttempts +
                ", numberOfRevisions=" + numberOfRevisions +
                ", quizScores=" + quizScores +
                ", verdict=" + verdict +
                '}';
    }
}
