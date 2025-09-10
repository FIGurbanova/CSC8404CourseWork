package org.quizLib.Questions;
import org.enums.QuestionType;

public abstract class AbstractQuestion implements QuestionInterface {
    /**
     * The formulation or text of the question.
     */
    String questionFormula;
    /**
     * Factory method to generate an instance of a specific type of question.
     * Based on the {@code QuestionType}, this method will return an instance of either
     * {@code FreeResponseQuestion} or {@code MultipleChoiceQuestion}.
     * @param questionType    the type of question to create (either FREE_RESPONSE_QUESTION or MULTIPLE_CHOICE_QUESTION)
     * @param questionFormula the text or formulation of the question
     * @param answer          the correct answer for the question
     * @return a specific question instance based on the given {@code QuestionType}
     * @throws IllegalArgumentException if the provided {@code QuestionType} is not recognized
     */
    public static QuestionInterface getQuestionInstance(QuestionType questionType, String questionFormula, String answer) {

        switch (questionType) {
            case FREE_RESPONSE_QUESTION:
                return new FreeResponseQuestion(questionFormula, answer);
            case MULTIPLE_CHOICE_QUESTION:
                return new MultipleChoiceQuestion(questionFormula, answer);
            default :
                throw new IllegalArgumentException("Question type not recognized");
        }
    }

    /**
     * Normalizes the given answer by trimming spaces, converting it to lowercase, and reducing multiple spaces to a single space.
     * This ensures that minor formatting differences do not affect answer comparisons.
     * @param answer the answer to normalize
     * @return the normalized version of the answer
     */
    @Override
    public String normalizeAnswer(String answer) {
        return answer.trim().toLowerCase().replaceAll("\\s+", " ");

    }
    /**
     * Returns the formulation or text of the question.
     * @return the question's formulation
     */
    @Override
    public String getQuestionFormula() {
        return this.questionFormula;
    }

}
