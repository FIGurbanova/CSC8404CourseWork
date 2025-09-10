package org.quizLib.Quiz;

import org.enums.VerdictEnum;
import org.quizLib.Objects.Student;
import org.quizLib.Questions.QuestionInterface;

import java.util.*;

/**
 * The {@code RevisionQuiz} class represents a specialized quiz type that provides students with questions
 * focused on revision. It includes incorrect answers from previous quizzes and unseen questions.
 * This class extends the functionality of {@link AbstractQuiz} and implements {@link Quiz}.
 */
public class RevisionQuiz extends AbstractQuiz implements Quiz {
    /**
     * Generates a revision quiz for the student.
     * In the {@code RevisionQuiz} class, this method is not utilized, returning an empty set.
     *
     * @param numberOfQuestions the number of questions to include in the quiz
     * @return an empty set as this class does not use a regular question generator
     */
    @Override
    public Set<QuestionInterface> generateQuiz(int numberOfQuestions) {
        return Set.of();
    }
    /**
     * Handles the quiz-taking process for a student by evaluating their answers in a revision context.
     * The revision quiz focuses on incorrect answers from previous attempts and unseen questions.
     *
     * @param student the student taking the quiz
     * @param questionInterfaces the list of questions in the quiz
     * @param answers the list of answers provided by the student
     * @return the score of the quiz as a fraction between 0 and 1, based on the student's correct answers
     */
    @Override
    public double takeQuiz(Student student, List<QuestionInterface> questionInterfaces, List<String> answers) {
        return takeQuizGeneral(student, questionInterfaces, answers, VerdictEnum.REVISION);
    }

    /**
     * Generates a revision quiz for a student. The quiz is comprised of a mix of questions that the student
     * has previously answered incorrectly and new questions that they have not yet encountered.
     *
     * @param student the student taking the revision quiz
     * @param numberOfQuestions the number of questions to include in the revision quiz
     * @return a set of {@link QuestionInterface} objects representing the revision quiz
     */
    public Set<QuestionInterface> revise(Student student, int numberOfQuestions) {
        List<QuestionInterface> allQuestions = new ArrayList<>();
        Set<QuestionInterface> revisedQuestions = new HashSet<>();
        allQuestions.addAll(freeResponseQuestions);
        allQuestions.addAll(multiChoiceResponseQuestions);
        Collections.shuffle(allQuestions);

        Set<QuestionInterface> questionsSeen = student.getQuestionsSeen();
        Set<QuestionInterface> incorrectQuestions = student.getIncorrectQuestions();

        for (QuestionInterface question : incorrectQuestions) {
            if (revisedQuestions.size() <= numberOfQuestions) {
                revisedQuestions.add(question);
            } else {
                break;
            }
        }

        // Then add unseen questions (which haven't been answered yet)
        for (QuestionInterface allQuestion : allQuestions) {
            if (revisedQuestions.size() < numberOfQuestions && !questionsSeen.contains(allQuestion)) {
                revisedQuestions.add(allQuestion);
            }
            if (revisedQuestions.size() == numberOfQuestions) {
                break;
            }
        }
        return revisedQuestions;
    }

}
