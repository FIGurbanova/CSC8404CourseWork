package Quiz;

import org.enums.VerdictEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.quizLib.Objects.Student;
import org.quizLib.Questions.FreeResponseQuestion;
import org.quizLib.Questions.QuestionInterface;
import org.quizLib.Quiz.RegularQuiz;

import java.util.*;

public final class StatisticsTest {
    private RegularQuiz regularQuiz;
    private List<QuestionInterface> questions;
    private Student student;
    @BeforeEach
    void setUp() {
        regularQuiz = new RegularQuiz();
        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.set(1999,01,26);
        student = new Student("Fidan",dateOfBirth.getTime());
        questions = new ArrayList<>();
        questions.add(new FreeResponseQuestion("Where were the 2024 Summer Olympics held?", "Paris"));
        questions.add(new FreeResponseQuestion("What is the only mammal that can fly?", "Bat"));

    }
    @Test
    public void test_Pass_Verdict() {
        int numberOfQuestions = 4;
        List<String> answers = List.of("Paris", "Bat");
        double studentScore = regularQuiz.takeQuiz(student,questions,answers);
        Assertions.assertEquals(1.0,studentScore);
        Assertions.assertEquals(VerdictEnum.PASS, student.getStatistics().getVerdict());
    }

    @Test
    public void test_Same_Student() {
        Calendar dateOfBirth = Calendar.getInstance();
        Calendar dateOfBirth1 = Calendar.getInstance();
        dateOfBirth.set(1999,01,26);
        dateOfBirth1.set(1999,02,26);
        Student student1 = new Student("Fidan",dateOfBirth.getTime());
        Student student2 = new Student("fidan",dateOfBirth.getTime());
        Assertions.assertEquals(student1,student2);
        Student student3 = new Student("fidan",dateOfBirth1.getTime());
        Assertions.assertTrue(student2!=student3);
    }
}
