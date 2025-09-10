package Quiz.FreeResponse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.quizLib.Questions.FreeResponseQuestion;

public final class QuizUnitTest {
    private FreeResponseQuestion freeResponseQuestion;
    @BeforeEach
    public void setUp() {
        freeResponseQuestion = new FreeResponseQuestion("Where were the 2024 Summer Olympics held?", "Paris");

    }

    @Test
    public void testCorrectAnswer() { //checking the correct answeer of question
        Assertions.assertTrue(freeResponseQuestion.checkQuestionAnswer("paris"));
    }

    @Test
    public void testWrongAnswer() { //checking wrong answer of question
        Assertions.assertFalse(freeResponseQuestion.checkQuestionAnswer("tokyo"));
    }


    @Test
    public void testMultipleAnswer() { //checking multiple answer not accepted  case
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> freeResponseQuestion.checkQuestionAnswer("tokyo,paris,baku"));
        Assertions.assertEquals("Only one answer is accepted", thrown.getMessage());
    }

    @Test
    public void testTrailWhiteSpace() { //leading and trailing whitespaces is ignored
        Assertions.assertTrue(freeResponseQuestion.checkQuestionAnswer("   paris   "));
    }

    @Test
    public void testCorrectQuestion() {
        Assertions.assertFalse(freeResponseQuestion.checkQuestionAnswer("pariss"));
    }

}
