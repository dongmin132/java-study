package org.example.GradeCalculator;

import java.util.List;

public class Courses {
    //1급 컬렉션: List형태로된 클래스정보만을 가진 클래스
    private List<Course> courses;

    Courses(List<Course> courses) {
        this.courses = courses;
    }

    public double multipliedCreditAndCourseGrade() {
        double multipliedCreditAndCourseGrade=0;
        for (Course course : courses) {
            multipliedCreditAndCourseGrade += course.multipliedCreditAndCourseGrade();
        }
        return multipliedCreditAndCourseGrade;
    }

    public int calculateTotalCredit() {
        int totalCredit =0;
        for (Course course : courses) {
            totalCredit += course.getCredit();
        }
        return totalCredit;
    }
}
