package Quiz;

import org.enums.QuestionType;
import org.enums.VerdictEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.quizLib.Objects.Student;
import org.quizLib.Questions.AbstractQuestion;
import org.quizLib.Questions.QuestionInterface;
import org.quizLib.Quiz.RegularQuiz;
import org.quizLib.Quiz.RevisionQuiz;

import java.util.*;

public final class RevisionQuizTest {
    private Student student;
    private List<QuestionInterface> questions;
    private RevisionQuiz revisionQuiz;
    @BeforeEach
    public void setUp() {
        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.set(1999, 01, 26);
        student = new Student("Fidan", dateOfBirth.getTime());
        revisionQuiz = new RevisionQuiz();

        // Simulate questions already seen by the student
        QuestionInterface q1 = AbstractQuestion.getQuestionInstance(QuestionType.FREE_RESPONSE_QUESTION, "Where were the 2024 Summer Olympics held?", "Paris");
        QuestionInterface q2 = AbstractQuestion.getQuestionInstance(QuestionType.MULTIPLE_CHOICE_QUESTION, "Which of the following are programming languages?\n" +
                "a) HTML\n" +
                "b) Java\n" +
                "c) Python\n" +
                "d) CSS", "b,c");
        QuestionInterface q3 = AbstractQuestion.getQuestionInstance(QuestionType.FREE_RESPONSE_QUESTION,"What is the tallest animal in the world?", "Giraffe");
        student.addQuestionAsSeen(q1);
        student.addIncorrectQuestion(q2);  // Incorrect question
        questions = new ArrayList<>();
        questions.add(q2);
        questions.add(q3);
    }

    @Test
    public void test_DublicateQuestion() {
        Set<QuestionInterface> revisedQuestions = new HashSet<>();
        revisedQuestions = revisionQuiz.revise(student, 10);
        // Convert to a list to check duplicates (since Set can't have duplicates by definition)
        List<QuestionInterface> questionList = new ArrayList<>(revisedQuestions);
        // Create a Set from the list
        Set<QuestionInterface> questionSet = new HashSet<>(questionList);

        Assertions.assertTrue(questionSet.size()==revisedQuestions.size());
    }

    @Test
    public void take_Revision_Quiz_Pass(){
        List<String> answers =  List.of("b,c", "giraffe ");
        Assertions.assertEquals(1,revisionQuiz.takeQuiz(student,questions,answers));
    }


    @Test
    public void take_Revision_Quiz_Fail(){
        List<String> answers =  List.of("b,c,d", "paris");
        //first attempt TBD
        revisionQuiz.takeQuiz(student,questions,answers);
        Assertions.assertEquals(VerdictEnum.TBD,student.getStatistics().getVerdict());
        //Second revision attempt
        revisionQuiz.takeQuiz(student,questions,answers);
        Assertions.assertEquals(VerdictEnum.FAIL,student.getStatistics().getVerdict());
    }

//After taking regular quiz and  have the verdict  student can not take revision quiz
    @Test
    public void take_Regular_and_Revision_Quiz_(){
        RegularQuiz regularQuiz = new RegularQuiz();
        List<String> answers =  List.of("b,c", "paris");
        //first attempt to regular quiz and pass
        regularQuiz.takeQuiz(student,questions,answers);
        Assertions.assertEquals(VerdictEnum.PASS,student.getStatistics().getVerdict());
        //Attempt to take revision quiz
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            revisionQuiz.takeQuiz(student,questions,answers);
        });
        Assertions.assertEquals("The student has passed verdict And does not have another quiz.",exception.getMessage());
    }


    //After taking regular quiz and  have the verdict  student can not take revision quiz
    @Test
    public void take_Regular_and_Revision_Quiz_Failed(){
        RegularQuiz regularQuiz = new RegularQuiz();
        List<String> answers =  List.of("b,c,d", "paris");
        double score;
        //first attempt to regular quiz and pass
        score = regularQuiz.takeQuiz(student,questions,answers);
        Assertions.assertEquals(VerdictEnum.TBD,student.getStatistics().getVerdict());
        //Second attempt regular quiz Failed
        score = regularQuiz.takeQuiz(student,questions,answers);
        Assertions.assertEquals(VerdictEnum.FAIL,student.getStatistics().getVerdict());

        //Student can not take revision quiz after having the final verdict
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            revisionQuiz.takeQuiz(student,questions,answers);
        });
        Assertions.assertEquals("The student has failed verdict And can not take another quiz.",exception.getMessage());
    }
}
