package org.quizLib.Questions;


public class FreeResponseQuestion extends AbstractQuestion {
    /**
     * The text or formulation of the question.
     */
    private final String questionFormula;
    /**
     * The correct answer to the question.
     */
    private final String questionAnswer;

    public FreeResponseQuestion(String questionFormula, String questionAnswer) {
        this.questionFormula = questionFormula;
        this.questionAnswer = normalizeAnswer(questionAnswer);
    }

    /**
     * Checks if the provided answer matches the correct answer.
     * The provided answer is normalized before comparison. If multiple answers are provided,
     * an {@code IllegalArgumentException} is thrown.
     *
     * @param answer the answer to check against the correct answer
     * @return {@code true} if the provided answer matches the correct answer, {@code false} otherwise
     * @throws IllegalArgumentException if more than one answer is provided (indicated by commas)
     */
    @Override
    public boolean checkQuestionAnswer(String answer) {
        String normalizedAnswer = normalizeAnswer(answer);
        if (normalizedAnswer.contains(",")) {  //if one or more answer is provided return error
            throw new IllegalArgumentException("Only one answer is accepted");
        } else {
            return normalizedAnswer.equals(questionAnswer);
        }
    }
    /**
     * Returns a string representation of the {@code FreeResponseQuestion}, including its formulation and answer.
     * @return a string containing the question's formulation and correct answer
     */
    @Override
    public String toString() {
        return "FreeResponseQuestion{" +
                "questionAnswer='" + questionAnswer + '\'' +
                ", questionFormula='" + questionFormula + '\'' +
                '}';
    }
}
