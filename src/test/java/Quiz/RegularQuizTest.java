package Quiz;

import org.enums.VerdictEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.quizLib.Objects.Student;
import org.quizLib.Questions.FreeResponseQuestion;
import org.quizLib.Questions.QuestionInterface;
import org.quizLib.Quiz.RegularQuiz;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public final class RegularQuizTest {
    private RegularQuiz regularQuiz;
    private List<QuestionInterface> questions;
    private Student student;

    @BeforeEach
    void setUp() {
        regularQuiz = new RegularQuiz();
        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.set(1999, 01, 26);
        student = new Student("Fidan", dateOfBirth.getTime());
        questions = new ArrayList<>();
        questions.add(new FreeResponseQuestion("Where were the 2024 Summer Olympics held?", "Paris"));
        questions.add(new FreeResponseQuestion("What is the only mammal that can fly?", "Bat"));

    }


    @Test
    public void takeQuiz_correct_answers() {
        double studentScore = getStudentScore(student, questions, VerdictEnum.PASS);
        Assertions.assertEquals(1, studentScore);
    }

    @Test
    public void takeQuiz_pass_verdict() {
        double studentScore = getStudentScore(student, questions, VerdictEnum.PASS);
        Assertions.assertEquals(VerdictEnum.PASS, student.getStatistics().getVerdict());
    }

    @Test
    public void takeQuiz_wrong_answers() {
        double studentScore = getStudentScore(student, questions, VerdictEnum.FAIL);
        Assertions.assertEquals(0, studentScore);
    }


    @Test
    public void takeQuiz_fail_verdict() {
        //First attempt. student verdict is to be decided
        double studentScore = getStudentScore(student, questions, VerdictEnum.FAIL);
        Assertions.assertEquals(VerdictEnum.TBD, student.getStatistics().getVerdict());
        //Second attempt quiz failed and final verdict would be Fail
        studentScore = getStudentScore(student, questions, VerdictEnum.FAIL);
        Assertions.assertEquals(VerdictEnum.FAIL, student.getStatistics().getVerdict());

        //Third attempt is not accepted.Throws error
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            getStudentScore(student, questions, VerdictEnum.FAIL);
        });
        Assertions.assertEquals("The student has failed verdict And can not take another quiz.", exception.getMessage());

    }

    public double getStudentScore(Student student, List<QuestionInterface> questions, VerdictEnum verdict) {
        List<String> answers;
        if (verdict.equals(VerdictEnum.PASS)) {
            answers = List.of("Paris", "Bat");
        } else {
            answers = List.of("Tokyo", "Giraffe");
        }
        return regularQuiz.takeQuiz(student, questions, answers);
    }


}
