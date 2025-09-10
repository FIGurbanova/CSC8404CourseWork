package org.quizLib.Quiz;

import org.quizLib.Objects.Student;
import org.quizLib.Questions.QuestionInterface;
import org.quizLib.Statistics;

import java.util.List;
import java.util.Set;
/**
 * The Quiz interface defines the contract for creating and taking quizzes, as well as generating revision quizzes.
 * Implementing classes are expected to manage the process of question selection, answer validation, and scoring.
 */
public interface Quiz {
    /**
     * Generates a quiz with the specified number of questions. The questions should be randomly selected
     * from the pool of available questions.
     *
     * @param numberOfQuestions the number of questions to include in the quiz
     * @return a set of {@link QuestionInterface} objects representing the generated quiz questions
     */
    Set<QuestionInterface> generateQuiz(int numberOfQuestions);
    /**
     * Allows the student to take a quiz by providing answers to a set of questions. This method calculates the score
     * based on the number of correct answers.
     *
     * @param student the student taking the quiz
     * @param questionInterfaces the list of questions included in the quiz
     * @param answers the list of answers provided by the student
     * @return the score as a fraction between 0 and 1, representing the percentage of correct answers
     */
    double takeQuiz(Student student, List<QuestionInterface> questionInterfaces, List<String> answers);
    /**
     * Generates a revision quiz tailored for the student. The questions included should be those
     * the student answered incorrectly or has not previously encountered.
     *
     * @param student the student who is revising
     * @param numberOfQuestions the number of questions to include in the revision quiz
     * @return a set of {@link QuestionInterface} objects representing the generated revision quiz questions
     */
    Set<QuestionInterface> revise(Student student, int numberOfQuestions);
}
