package org.example.calculator.tobe;

import org.example.calculator.domain.PositiveNumber;

public class DivOperator implements NewArithmeticOperator {

    @Override
    public boolean supports(String operator) {
        return "/".equals(operator);
    }

    @Override
    public int calculate(PositiveNumber operand1, PositiveNumber operand2) {
//        if (operand2.toInt() == 0) {
//            throw new IllegalArgumentException("0으로 나눌 수 없습니다");
//        }                                // PositiveNumber에 ValidationCheck에서 0이면 걸리기 때문에 여기서 체크할 필요가 없다

        return operand1.toInt()/operand2.toInt();
    }
}
