
# Quiz Generation and Management System

This project is a **Java-based quiz library** for generating, managing, and taking quizzes.  
It demonstrates **object-oriented design principles**, **factory patterns**, **interface-driven design**, **immutability**, and **defensive programming** while focusing on clean code and testability.

> ⚡ **Note:**  
> The focus is on **library design** and **unit testing** rather than building a user interface.  
> All functionality is validated using **JUnit tests**.

---

## **Project Overview**

The system manages quizzes for a local school, allowing students to take **regular quizzes** and **revision quizzes**. It tracks statistics, enforces eligibility rules, and ensures correct scoring and verdict calculation.

### **Core Rules**
- **Passing score:** 50% or more on a regular quiz.
- **Failing condition:** Two failed regular quiz attempts.
- Students can take **up to two revision quizzes** unless a final **PASS** or **FAIL** verdict has been reached.
- Quizzes **must contain both question types**:
  - **Free response questions** (one correct normalized answer).
  - **Multiple choice questions** (2–4 options, possibly multiple correct answers).
- **No duplicate questions** within a single quiz.
- **Revision quizzes** only include:
  - Questions a student has **not seen before**.
  - Questions the student **answered incorrectly**.

---

## **Class and Interface Structure**

### **Question Layer**

#### **`QuestionInterface`**
Defines the core question behavior:
- `checkQuestionAnswer(String answer)` → validates an answer based on the question type.
- `normalizeAnswer(String answer)` → trims, lowercases, and formats answers for comparison.
- `getQuestionFormula()` → retrieves the question text.

---

#### **`AbstractQuestion`** *(implements `QuestionInterface`)*
- Holds shared logic for both question types.
- `questionFormula` → immutable question text.
- `getQuestionInstance(QuestionType type, String formula, String answer)` → **Factory Method** to create questions dynamically.
- Implements **normalization** and **getter logic**.

---

#### **`FreeResponseQuestion`** *(extends `AbstractQuestion`)*
- Designed for open-ended questions with **one correct answer**.
- `checkQuestionAnswer(String answer)` → verifies correctness after normalization.
- `toString()` → for clean debugging and logging.

---

#### **`MultipleChoiceQuestion`** *(extends `AbstractQuestion`)*
- Designed for questions with **multiple options** and possibly multiple correct answers.
- `answerSet` → set of correct normalized answers.
- `checkQuestionAnswer(String answer)` → checks that all correct options are provided in any order, separated by commas.
- `toString()` → string representation of question and possible answers.

---

### **Quiz Layer**

#### **`Quiz` (Interface)**
Core quiz behavior:
- `generateQuiz(int numberOfQuestions)` → randomizes quiz questions.
- `takeQuiz(Student student, List<QuestionInterface> questions, List<String> answers)` → lets a student take the quiz and calculates score.
- `revise(Student student, int numberOfQuestions)` → generates revision quizzes focused on weak areas.

---

#### **`AbstractQuiz` (Base Class)**
Provides shared logic for quiz types:
- Maintains separate pools for free-response and multiple-choice questions.
- `checkEligibility(Student student)` → ensures the student can attempt a quiz.
- `takeQuizGeneral(...)` → handles core quiz flow:
  - Scoring
  - Updating statistics
  - Applying pass/fail verdict rules

---

#### **`RegularQuiz`**
- Generates quizzes with **balanced question types**.
- Updates statistics after each attempt.
- `revise(...)` → not applicable (returns empty set).

---

#### **`RevisionQuiz`**
- Generates quizzes using **only unseen or incorrectly answered questions**.
- Ensures both question types are included when available.

---

### **Statistics and Tracking**

#### **`Statistics`**
Tracks student performance:
- **Attributes**:
  - `numberOfRegAttempts`
  - `numberOfRevisions`
  - `quizScores` → list of past scores
  - `verdict` → current status (PASS, FAIL, TBD)
- **Methods**:
  - `incrementRegAttempts()`, `incrementRevisions()`
  - `addScores(double score, VerdictEnum type)`
  - `checkAndSetVerdict(...)` → determines pass/fail after each attempt

---

### **Enums**

- **`QuestionType`**:
  - `FREE_RESPONSE_QUESTION`
  - `MULTIPLE_CHOICE_QUESTION`

- **`VerdictEnum`**:
  - `PASS` → score ≥ 50% on regular quizzes
  - `FAIL` → two failed regular attempts
  - `TBD` → before reaching final verdict
  - `REGULAR`, `REVISION` → for quiz type tracking

---

### **Student Management**

#### **`Student`**
- Immutable representation of a student:
  - `firstName`
  - `lastName`
  - `dateOfBirth` (via `java.util.Date`)
- **Equality rule**: Two students are the same if they share the same **full name** and **DOB**.
- Links to their `Statistics` for tracking progress.

---

## **Example Usage**

```java
// Create a student
Student john = new Student("John", "Doe", new Date(2000, Calendar.JANUARY, 15));

// Generate a quiz
RegularQuiz quiz = new RegularQuiz();
List<QuestionInterface> questions = quiz.generateQuiz(10);

// Student takes quiz
List<String> answers = Arrays.asList("Answer1", "b,c", "Answer3", "a,b,c");
double score = quiz.takeQuiz(john, questions, answers);

System.out.println("Score: " + score);
System.out.println("Final Verdict: " + john.getStatistics().getVerdict());
```

---


---

## **Testing**

- **JUnit 5** is used for testing:
  - Answer normalization rules
  - Quiz generation correctness
  - Student eligibility enforcement
  - Statistics tracking and verdict logic



---

## **Design Patterns Used**
- **Factory Pattern:**  
  - `getQuestionInstance()` creates questions dynamically.
- **Strategy Pattern:**  
  - `Quiz` interface with different implementations (`RegularQuiz`, `RevisionQuiz`).
- **Immutability and Defensive Programming:**  
  - Questions and student data are immutable.
- **Collections Framework:**  
  - `Set` for answer handling.
  - `Map` for tracking per-student statistics.

---

## **License**

Educational project for coursework.  
Use for learning and demonstration purposes only.



<img width="2800" height="1471" alt="Untitled Diagram-Question drawio (1)" src="https://github.com/user-attachments/assets/4a30dc4f-84dd-451d-b3b2-f7ce23170e6f" />
