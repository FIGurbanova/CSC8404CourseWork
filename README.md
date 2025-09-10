Quiz Library (Coursework)

A small Java library for generating and taking quizzes used within a larger student assessment system.
The focus is on good OO design, interfaces, factories, defensive programming, and unit testing, not on building a UI.


Aim

Practice the design principles covered in lectures by implementing interfaces and classes that demonstrate:

Proper overriding of Object methods (equals, hashCode, toString) and providing static valueOf(...) where appropriate.

Interface-based design and late binding.

Factory patterns to control object creation (including guaranteeing unique instances when required).

Defensive programming and immutability.

Appropriate use of Java Collections Framework.

Comprehensive Javadoc documentation.

JUnit unit testing.

System Overview

The library supports regular and revision quizzes for a local school setting.

A student passes the assessment with ≥ 50% on a regular quiz.

If a student fails two regular quizzes, they fail the assessment.

A student can take up to two revision quizzes unless they already have a final verdict (PASS/FAIL).

After receiving a final verdict, students cannot take more quizzes (regular or revision).

Quiz Generation Rules

Each quiz must contain both question types:

Free-response (exactly one correct answer).

Multiple-choice (2–4 options; correct answer may include multiple options).

No duplicate questions in a quiz.

Regular quiz: random selection from the question pool.

Revision quiz: only questions the student has not seen or previously answered incorrectly (still must contain both types if available).

Answer Validation Rules

Case-insensitive comparison.

Leading/trailing whitespace ignored.

Free-response: collapse multiple internal whitespaces (e.g., "My answer" ≡ "My answer").

Multiple-choice: answers separated by commas; order does not matter (e.g., "a,b,c" ≡ "c,b,a").
