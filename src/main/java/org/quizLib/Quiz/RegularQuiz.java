package org.quizLib.Quiz;

import org.enums.VerdictEnum;
import org.quizLib.Objects.Student;
import org.quizLib.Questions.QuestionInterface;
import org.quizLib.Statistics;

import java.util.*;
/**
 * The RegularQuiz class represents a standard quiz session where students answer a set of questions.
 * It generates quizzes with a balanced mix of free-response and multiple-choice questions, and handles
 * the quiz-taking process by calculating scores based on correct answers.
 */
public class RegularQuiz extends AbstractQuiz implements Quiz{
    /**
     * Constructs a new RegularQuiz with associated statistics tracking.
     */
    private Statistics statistics;
    public RegularQuiz() {
        statistics  = new Statistics();
    }
    /**
     * Generates a regular quiz with the specified number of questions. Half of the questions will be
     * free-response, and the other half will be multiple-choice, based on the question pool.
     *
     * @param numberOfQuestions the number of questions to include in the quiz
     * @return a set of {@link QuestionInterface} objects representing the generated quiz questions
     */
    @Override
    public Set<QuestionInterface> generateQuiz(int numberOfQuestions) {
        List<QuestionInterface> freeQuestions = new ArrayList<>(freeResponseQuestions);
        List<QuestionInterface> multiQuestions = new ArrayList<>(multiChoiceResponseQuestions);
        Collections.shuffle(freeQuestions);
        Collections.shuffle(multiQuestions);
        int numOfFreeQuestions = numberOfQuestions/2;
        Set<QuestionInterface> finalSelectedQuestions = new HashSet<>(freeQuestions.subList(0, Math.min(numOfFreeQuestions, freeQuestions.size())));
        finalSelectedQuestions.addAll(multiQuestions.subList(0, Math.min(numberOfQuestions-numOfFreeQuestions, multiQuestions.size())));
        return finalSelectedQuestions;
    }
    /**
     * Handles the quiz-taking process for a student by evaluating their answers and calculating the score.
     * The method ensures eligibility and uses {@link VerdictEnum#REGULAR} as the verdict type for regular quizzes.
     *
     * @param student the student taking the quiz
     * @param questionInterfaces the list of questions in the quiz
     * @param answers the list of answers provided by the student
     * @return the score as a fraction between 0 and 1, representing the percentage of correct answers
     */
    @Override
    public double takeQuiz(Student student, List<QuestionInterface> questionInterfaces, List<String> answers) {
        return takeQuizGeneral(student, questionInterfaces, new ArrayList<>(answers),VerdictEnum.REGULAR);

    }
    /**
     * Provides a revision quiz. In the RegularQuiz class, this method is not implemented and returns an empty set.
     *
     * @param student the student requesting the revision quiz
     * @param numberOfQuestions the number of questions for the revision quiz
     * @return an empty set, as this class does not support revision quizzes
     */
    @Override
    public Set<QuestionInterface> revise(Student student, int numberOfQuestions) {
        return Set.of(); // RegularQuiz does not handle revisions
    }


}
