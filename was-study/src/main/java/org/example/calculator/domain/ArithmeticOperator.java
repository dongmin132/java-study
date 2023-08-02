package org.example.calculator.domain;

import java.util.Arrays;

public enum ArithmeticOperator {
    SUM("+") {
        @Override
        public int arithmeticCalculator(int operand1, int operand2) {
            return operand1 + operand2;
        }
    },MIN("-") {
        @Override
        public int arithmeticCalculator(int operand1, int operand2) {
            return operand1-operand2;
        }
    }, MUL("*") {
        @Override
        public int arithmeticCalculator(int operand1, int operand2) {
            return operand1 * operand2;
        }
    }, DIV("/") {
        @Override
        public int arithmeticCalculator(int operand1, int operand2) {
            return operand1/operand2;
        }
    };

    private final String operator;

    ArithmeticOperator(String operator) {
        this.operator = operator;
    }

    public abstract int arithmeticCalculator(final int operand1, final int operand2);


    public static int calculate(int operand1, String operation, int operand2) {        //외부에 노출되는 public interface
        ArithmeticOperator operator = Arrays.stream(values())
                .filter(v -> v.operator.equals(operation))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 사칙연산이 아닙니다"));
        return operator.arithmeticCalculator(operand1, operand2);
    }
}
