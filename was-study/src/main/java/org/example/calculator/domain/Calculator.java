package org.example.calculator.domain;

import org.example.calculator.tobe.*;

import java.util.List;

public class Calculator {
    private static final List<NewArithmeticOperator> arithmeticOperators = List.of(new AddOperator(), new MinOperator(), new DivOperator(), new MulOperator());
    public static int calculate(PositiveNumber operand1, String operation, PositiveNumber operand2) {
//        return ArithmeticOperator.calculate(operand1, operation, operand2);       //enum 방식
        return arithmeticOperators.stream()
                .filter(arithmeticOperator -> arithmeticOperator.supports(operation))
                .map(arithmeticOperator -> arithmeticOperator.calculate(operand1, operand2))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 사칙연산이 아닙니다"));
    }

}
