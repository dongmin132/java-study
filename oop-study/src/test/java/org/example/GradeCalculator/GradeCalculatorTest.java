package org.example.GradeCalculator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;



/*
 평균학점 계산 방법 = (학점수×교과목 평점)의 합계/수강신청 총학점 수
• MVC패턴(Model-View-Controller) 기반으로 구현한다.
• 일급 컬렉션 사용
*/

class GradeCalculatorTest {
    /*
        1. 도메인을 구성하는 객체에는 어떤 것들이 있는지 고민 : 학점 계산기 도메인 : 이수한 과목, 학점 계산기
        2. 객체들 간의 관계를 고민      : 학점 계산기가 이수한 과목들을 가지고 학점을 계산하면 되겠다.
                                    학점 계산기가 이수한 과목 클래스를 인스턴스 변수로 가지면 되겠다라는걸 생각함.
        3. 동적인 객체를 정적인 타입으로 추상화해서 도메인 모델링 하기    :  객체 지향 프로그래밍, 자료구조, 중국어 회화와 같은 과목들이 있을 때 --> 과목(코스) 클래스로 표현한다는 의미
        4. 협력을 설계  :  이수한 과목을 전달하여 평균학점 계산 요청 --> 학점 계산기한테 요청함
        5. 객체들을 포괄하는 타입에 적절한 책임을 할당 : (학점수×교과목 평점)의 합계와 수강신청 총학점 수를 각각 구하여 나누는 작업을 하게 됨
          학점 계산기 클래스에서 이 모든 작업을 해도 되지만 과목(코스)클래스는 학점 수, 교과목의 평점들의 내용을 가지고 있으므로 과목 클래스에서 계산 후 학점 계산기는 나누기만 하는 식으로 작성해봄
    */
    @DisplayName("평균 학점을 계산한다.")
    @Test
    void calculateGradeTest() {
        List<Course> courses = List.of(new Course("OOP", 3, "A+"),
                new Course("자료 구조", 3, "A+"));
        GradeCalculator gradeCalculator = new GradeCalculator(new Courses(courses));
        double gradeResult = gradeCalculator.calculateGrade();

        assertThat(gradeResult).isEqualTo(4.5);
    }

}