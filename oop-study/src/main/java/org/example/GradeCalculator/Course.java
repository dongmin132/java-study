package org.example.GradeCalculator;

public class Course {
    private final String subject;
    private final int credit;
    private final String grade;
    public Course(String subject, int credit, String grade) {       //과목, 학점 수, 학점
        this.subject = subject;
        this.credit = credit;
        this.grade = grade;
    }

    public int getCredit() {
        return credit;
    }

    public double getGradeToNumber() {
        switch (this.grade) {
            case "A+":
                return 4.5;
            case "A":
                return 4.0;
            case "B+":
                return 3.5;
            default:
                throw new IllegalArgumentException("올바르지 않은 학점입니다.");
        }
    }

    public double multipliedCreditAndCourseGrade() {
        return credit * getGradeToNumber();
    }
}
