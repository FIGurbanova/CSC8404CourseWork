package org.quizLib.Quiz;
import org.enums.VerdictEnum;
import org.quizLib.Objects.Student;
import org.quizLib.Questions.AbstractQuestion;
import org.quizLib.Questions.QuestionInterface;
import org.quizLib.Statistics;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.enums.QuestionType.FREE_RESPONSE_QUESTION;
import static org.enums.QuestionType.MULTIPLE_CHOICE_QUESTION;
/**
 * The {@code AbstractQuiz} class serves as a base class for different types of quizzes,
 * such as regular and revision quizzes. It provides common functionality for handling
 * questions and checking student eligibility.
 */
public abstract class AbstractQuiz implements Quiz {
    /** Stores all free response questions available in the application. */
    protected static final Set<QuestionInterface> freeResponseQuestions = new HashSet<>();
    /** Stores all multiple choice questions available in the application. */
    protected static final Set<QuestionInterface> multiChoiceResponseQuestions = new HashSet<>();
    // Static block to initialize question sets with sample questions
    static {
        //Free response question samples
        freeResponseQuestions.add(AbstractQuestion.getQuestionInstance(FREE_RESPONSE_QUESTION, "Where were the 2024 Summer Olympics held?", "Paris"));
        freeResponseQuestions.add(AbstractQuestion.getQuestionInstance(FREE_RESPONSE_QUESTION, "What is the only mammal that can fly?", "Bat"));
        freeResponseQuestions.add(AbstractQuestion.getQuestionInstance(FREE_RESPONSE_QUESTION, "What is the tallest animal in the world?", "Giraffe"));
        //Multiple choice question samples
        multiChoiceResponseQuestions.add(AbstractQuestion.getQuestionInstance(MULTIPLE_CHOICE_QUESTION, "Which of the following are programming languages?\n" +
                        "a) HTML\n" +
                        "b) Java\n" +
                        "c) Python\n" +
                        "d) CSS",
                "b,c"));
        multiChoiceResponseQuestions.add(AbstractQuestion.getQuestionInstance(MULTIPLE_CHOICE_QUESTION, "Which of the following are primary colors?\n" +
                        "a) Red\n" +
                        "b) Green\n" +
                        "c) Blue\n" +
                        "d) Yellow",
                "a,c,d"));
        multiChoiceResponseQuestions.add(AbstractQuestion.getQuestionInstance(MULTIPLE_CHOICE_QUESTION, "Which of the following are fruits?\n" +
                        "a) Apple\n" +
                        "b) Tomato\n" +
                        "c) Carrot\n" +
                        "d) Banana",
                "c,d,a"));
    }
    /**
     * Checks if the given student is eligible to take the quiz. The method throws an exception if
     * the student has already passed or failed as a final verdict and is no longer eligible to take another quiz.
     *
     * @param student the student whose eligibility is being checked
     * @throws IllegalArgumentException if the student has either passed or failed as a final verdict
     */
    public void checkEligibility(Student student) {
        Statistics studentStatistics = student.getStatistics();
        if (studentStatistics.getVerdict() == VerdictEnum.PASS) {
            throw new IllegalArgumentException("The student has passed verdict And does not have another quiz.");
        } else if (studentStatistics.getVerdict() == VerdictEnum.FAIL) {
            throw new IllegalArgumentException("The student has failed verdict And can not take another quiz.");
        }
    }
    /**
     * Generalized method to handle quiz-taking. This method processes the student's answers,
     * calculates the score based on the number of correct answers, and updates the student's statistics.
     *
     * @param student the student taking the quiz
     * @param questionInterfaces the list of questions in the quiz.
     *                           <ul>
     *                             <li>For a <strong>Regular Quiz</strong>, it should contain all the questions from the question pool.</li>
     *                             <li>For a <strong>Revision Quiz</strong>, the questions should only include those the student answered incorrectly or has not seen before.</li>
     *                           </ul>
     * @param answers the list of answers provided by the student
     * @param v_enum the type of quiz being taken (regular or revision)
     * @return the score as a fraction between 0 and 1, representing the percentage of correct answers
     */
    public double takeQuizGeneral(Student student, List<QuestionInterface> questionInterfaces, List<String> answers,VerdictEnum v_enum) {
        checkEligibility(student);
        Statistics studentStatistics = student.getStatistics();
        int totalQuestions = questionInterfaces.size();
        int correctAnswers = 0;
        int index = 0;
        double score;
        for (QuestionInterface question : questionInterfaces) {
            if (question.checkQuestionAnswer(answers.get(index))) {
                correctAnswers++;
                student.addQuestionAsSeen(question);
            }
            index++;
        }
        if (correctAnswers == 0) {
            score = 0.0;
        } else {
            score = (double) (totalQuestions) / correctAnswers;
        }
        studentStatistics.incrementRegAttempts();
        studentStatistics.addScores(score,v_enum);
        return score;
    }

}

