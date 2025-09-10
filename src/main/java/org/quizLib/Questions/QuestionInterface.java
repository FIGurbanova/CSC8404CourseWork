package org.quizLib.Questions;

/**
 * The {@code QuestionInterface} defines the essential behavior for all question types in the quiz system.
 * Any class implementing this interface should be able to check the correctness of an answer, normalize an answer,
 * and return the formulation of the question.
 */
public  interface QuestionInterface {
    /**
     * Checks if the provided answer is correct.
     * Implementing classes should define how to determine if an answer is correct based on the question type.
     *
     * @param answer the answer to check
     * @return {@code true} if the provided answer is correct
     *
     * */
    boolean checkQuestionAnswer(String answer);
    /**
     * Normalizes the given answer by performing actions such as trimming whitespace and converting the text to lowercase.
     * This helps ensure consistency when comparing answers.
     * @param answer the answer to normalize
     * @return a normalized version of the answer
     */
    String normalizeAnswer(String answer);
    /**
     * Retrieves the formulation (text) of the question.
     * @return the formulation of the question
     */
    String getQuestionFormula();

}
