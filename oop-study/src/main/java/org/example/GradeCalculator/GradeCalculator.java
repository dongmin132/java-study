package org.example.GradeCalculator;

import java.util.List;

public class GradeCalculator {

    private Courses courses;
    public GradeCalculator(Courses courses) {
        this.courses = courses;
    }

    //평균 학점 계산 방법 = (학점수 * 교과목 평점)의 합계 / 수강 신청 총학점 수
    public double calculateGrade() {
        //(학점수 * 교과목 평점)의 합계 이 부분을 계산한다.
        double multipliedCreditAndCourseGrade= courses.multipliedCreditAndCourseGrade();

        //수강 신청 총학점 수 부분을 계산한다.
        int totalCredit=courses.calculateTotalCredit();

        return multipliedCreditAndCourseGrade/totalCredit;
    }
}
