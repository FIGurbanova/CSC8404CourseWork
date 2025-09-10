package org.enums;

/**
 * Enum representing the type of questions that are included in a quiz
 * Each quiz should contain two different type of questions.
 *
 */

public enum QuestionType {
    /**
     * Represents a free response questions where student provides written answer. And only contains one answer.
     * Example : "Where were the 2024 Summer Olympics held?", Answer: "Paris"
     */
    FREE_RESPONSE_QUESTION,
    /**
     * Represents a multiple choice question where the student selects the correct answer from a set of options.
     * Which of the following are programming languages?
     * A)HTML
     * B) JAVA
     * C) PYTHON
     * D)CSS
     * ANSWER : B,C
     */
    MULTIPLE_CHOICE_QUESTION
}
