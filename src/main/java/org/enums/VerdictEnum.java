package org.enums;

public enum VerdictEnum {
    /**
     * PASS - Indicates that the student has passed the quiz (i.e., scored 50% or more)
     * FAIL - Indicates that the student has failed the quiz (i.e., scored less than 50%)
     * TBD - Indicates that the student's final assessment is still pending (To Be Determined)
     * REGULAR - Represents a regular quiz attempt type
     * REVISION - Represents a revision quiz attempt type
     */
    /**
     * The student has passed the quiz (regular quiz score of 50% or more).
     */
    PASS,
    /**
     * The student has failed the quiz (score of less than 50% and regular quiz attempts are more than 2 ).
     */
    FAIL,
    /**
     * Initial verdict of student (will be determined after taking quiz)
     */
    TBD,
    /**
     * Represents quiz type that is Regular
     */
    REGULAR,
    /**
     * Represents quiz type that is Revision
     */
    REVISION
}
