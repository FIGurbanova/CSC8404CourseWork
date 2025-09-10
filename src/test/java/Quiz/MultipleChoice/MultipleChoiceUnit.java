package Quiz.MultipleChoice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.quizLib.Questions.AbstractQuestion;
import org.quizLib.Questions.MultipleChoiceQuestion;

import static org.enums.QuestionType.MULTIPLE_CHOICE_QUESTION;

public final class MultipleChoiceUnit {
    MultipleChoiceQuestion question;

    @BeforeEach
    void setUp() {
        question = (MultipleChoiceQuestion) AbstractQuestion.getQuestionInstance(MULTIPLE_CHOICE_QUESTION,"Which of the following are programming languages?\n" +
                        "a) HTML\n" +
                        "b) Java\n" +
                        "c) Python\n" +
                        "d) CSS",
                "b,c");  //is it right way to use?
    }

    @Test
    public void multiple_Choice_False_Test() {
            Assertions.assertFalse(question.checkQuestionAnswer("b"));

    }
    @Test
    public void multiple_Choice_change_place_Test() { //if places are changed still true case
        Assertions.assertTrue(question.checkQuestionAnswer("c,b"));
    }

    @Test
    public void multiple_Choice_Test() { //if places are changed still true case
        Assertions.assertTrue(question.checkQuestionAnswer("b,c"));
    }

    @Test
    public void multiple_Choice_Test_False() { //if
        Assertions.assertFalse(question.checkQuestionAnswer("b,c,a"));
    }

}
