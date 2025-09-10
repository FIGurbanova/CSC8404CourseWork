package org.quizLib.Objects;

import org.quizLib.Questions.QuestionInterface;
import org.quizLib.Statistics;

import java.util.*;
/**
 * The {Student} class represents a student participating in the quiz system.
 * It stores the student's name, date of birth, quiz statistics, and the questions they have seen or answered incorrectly.
 */
public class Student {
    private String name;
    private Date dateOfBirth;
    private final Statistics statistics;
    private final Set<QuestionInterface> questionsSeen;
    private final Set<QuestionInterface> incorrectQuestions;
    /**
     * Constructs a new {@code Student} with the specified name and date of birth.
     * @param name         the student's name
     * @param dateOfBirth  the student's date of birth
     * other parameters will be set as default
     */
    public Student(String name, Date dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.statistics = new Statistics();
        this.questionsSeen = new HashSet<>();
        this.incorrectQuestions = new HashSet<>();

    }

    /**
     * Returns the student's name.
     * @return the student's name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the student's name.
     * @param name student name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Returns the student's date of birth in Date format.
     * @return the student's date of birth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    /**
     * Set the student's date of birth in date format.
     * @param dateOfBirth student date of birth
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    /**
     * Returns a string representation of the {Student} object.
     * @return a string representation of the student
     */
    @Override
    public String toString() {
        return "Student{" +
                "dateOfBirth=" + dateOfBirth +
                ", name='" + name + '\'' +
                ", statistics=" + statistics +
                ", questionsSeen=" + questionsSeen +
                ", incorrectQuestions=" + incorrectQuestions +
                '}';
    }
    /**
     * Compares this student to the specified object. The result is {@code true} if and only if
     * the argument is not {@code null} and is a {@code Student} object that has the same name
     * and date of birth as this student.
     * @param obj the object to compare this {@code Student} against
     * @return {@code true} if the given object represents a {@code Student} with the same name and date of birth, otherwise {@code false}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;//reflexivity
        if (!(obj instanceof Student)) return false; //non-nullity
        Student s = (Student) obj;//consistency
        return name.equalsIgnoreCase(s.name) && dateOfBirth.equals(s.dateOfBirth);
    }
    /**
     * Returns a hash code for this student.
     * @return a hash code value for this student
     */
    @Override
    public int hashCode() {
        int hc = 3; //prime numbers
        //41 prime number
        hc = 41 * hc + (name == null ? 0 : name.hashCode());
        hc = 41 * hc + (dateOfBirth == null ? 0 : dateOfBirth.hashCode());//how to override hashcode for date functions
        return hc;
    }
    /**
     * Returns the student's quiz statistics.
     * @return the student's statistics
     */
    public Statistics getStatistics() {
        return statistics;
    }
    /**
     * Adds a question to the set of questions the student has seen.
     * @param question the question the student has seen
     */
    public void addQuestionAsSeen(QuestionInterface question) {
            questionsSeen.add(question);
    }
    /**
     * Adds a question to the set of questions the student answered incorrectly.
     * @param question the question the student answered incorrectly
     */
    public void addIncorrectQuestion(QuestionInterface question) {
        incorrectQuestions.add(question);
    }
    /**
     * Returns the set of questions the student has seen.
     * @return a set of QuestionInterface type questions the student has seen
     */
    public Set<QuestionInterface> getQuestionsSeen() {
        return questionsSeen;
    }
    /**
     * Returns the set of questions the student answered incorrectly.
     * @return a set of questions the student answered incorrectly
     */
    public Set<QuestionInterface> getIncorrectQuestions() {
        return incorrectQuestions;
    }
}
