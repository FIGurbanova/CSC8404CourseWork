package org.quizLib.Questions;

import java.util.*;
/**
 * The {@code MultipleChoiceQuestion} class represents a question where multiple correct answers are allowed.
 * It extends the {@code AbstractQuestion} class and handles validation of multiple correct answers.
 */
public class MultipleChoiceQuestion extends AbstractQuestion{
    /**
     * The text or formulation of the question.
     */
    private final String questionFormula;
    /**
     * A set of correct answers for the multiple-choice question.
     */
    private final Set<String> answerSet ;
    /**
     * Constructs a {@code MultipleChoiceQuestion} with the given question formulation and correct answers.
     * The answers are expected to be provided as a comma-separated string.
     *
     * @param questionFormula the text or formulation of the question
     * @param answer a comma-separated string of correct answers
     */
    public MultipleChoiceQuestion(String questionFormula, String answer) {
        String[] studentAnswerArray = answer.split(",");
        this.questionFormula = questionFormula;
        this.answerSet = new HashSet<>(Arrays.asList(studentAnswerArray));
    }

    /**
     * Checks if the provided answer matches the set of correct answers.
     * The provided answer is expected to be a comma-separated string. It splits the input string,
     * creates a set from the provided answers, and compares it with the correct answer set.
     * @param answer the answer to check, provided as a comma-separated string
     * @return {@code true} if the provided answers match the correct answers exactly, {@code false} otherwise
     */
    @Override
    public boolean checkQuestionAnswer(String answer) {
        String[] studentAnswerArray = answer.split(",");
        if(studentAnswerArray.length != answerSet.size()) return false;
        Set<String> studentAnswerSet = new HashSet<>(Arrays.asList(studentAnswerArray));
        return studentAnswerSet.equals(answerSet);
    }
    /**
     * Returns a string representation of the {@code MultipleChoiceQuestion}, including its formulation and answer set.
     * @return a string containing the question's formulation and correct answer set
     */
    @Override
    public String toString() {
        return "MultipleChoiceQuestion{" +
                "answerSet=" + answerSet +
                ", questionFormula='" + questionFormula + '\'' +
                '}';
    }
}
