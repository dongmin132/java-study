package org.example.GradeCalculator;

import org.assertj.core.api.Assertions;
import org.example.Calculator.Calculator;
import org.example.Calculator.PositiveNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class GradeCalculatorTest {

    @DisplayName("평균 학점을 계산한다.")
    @Test
    void calculateGradeTest() {
        List<Course> courses = List.of(new Course("OOP", 3, "A+"),
                new Course("자료 구조", 3, "A+"));
        GradeCalculator gradeCalculator = new GradeCalculator(new Courses(courses));
        double gradeResult = gradeCalculator.calculateGrade();

        assertThat(gradeResult).isEqualTo(4.5);
    }

    @DisplayName("학점이 없을 경우 IllegalArgument 예외를 발생 시킨다")
    @Test
    void calculateExceptionTest() {
        List<Course> courses = List.of(new Course("OOP", 3, "C"),
                new Course("자료 구조", 3, "B"));
        GradeCalculator gradeCalculator = new GradeCalculator(new Courses(courses));
        //double gradeResult = gradeCalculator.calculateGrade();
        assertThatCode(() -> gradeCalculator.calculateGrade())
                .isInstanceOf(IllegalArgumentException.class);
    }
}