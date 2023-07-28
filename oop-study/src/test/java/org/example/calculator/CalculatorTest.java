package org.example.calculator;
/*
요구사항
• 간단한 사칙연산을 할 수 있다.
• 양수로만 계산할 수 있다.
• 나눗셈에서 0을 나누는 경우 IllegalArgument 예외를 발생시킨다.
• MVC패턴(Model-View-Controller) 기반으로 구현한다
 */

import org.example.Calculator.Calculator;
import org.example.Calculator.PositiveNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class CalculatorTest {

    // 1 + 1 ----> Calculator 의 전달      두개의 피연산자와 하나의 연산자를 받아와야 함
    //   3   <----
//    @DisplayName("덧셈 연산을 정상적으로 수행한다")
//    @Test
//    void sumTest() {
//        int result = Calculator.calculate(new PositiveNumber()1, "+", 2);
//
//        assertThat(result).isEqualTo(3);
//    }
//
//    @DisplayName("뺄셈 연산을 정상적으로 수행한다")
//    @Test
//    void minTest() {
//        int result = Calculator.calculate(1, "-", 2);
//
//        assertThat(result).isEqualTo(-1);
//    }

    @DisplayName("사칙 연산을 수행한다")
    @ParameterizedTest      //하나의 테스트 코드로 여러 번의 사칙 연산을 돌릴 수 있음
    @MethodSource("fomulaAndResult")
    void calculator(int operand1, String operator,int operand2, int result) {
        int calculatorResult = Calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));
        assertThat(calculatorResult).isEqualTo(result);
    }

    private static Stream<Arguments> fomulaAndResult() {
        return Stream.of(
                arguments(1, "+", 2, 3),
                arguments(1, "-", 2, -1),
                arguments(4,"*",2,8),
                arguments(4,"/",2,2)
        );
    }

    @DisplayName("• 나눗셈에서 0을 나누는 경우 IllegalArgument 예외를 발생시킨다.")
    @Test
    void calculatorExceptionTest() {
        assertThatCode(() -> Calculator.calculate(new PositiveNumber(10), "/", new PositiveNumber(0)))
                .isInstanceOf(IllegalArgumentException.class);

    }
}
