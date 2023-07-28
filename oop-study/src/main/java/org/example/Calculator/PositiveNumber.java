package org.example.Calculator;

public class PositiveNumber {       //음수값이나 0이 나오면 안됨
    private final int value;

    public PositiveNumber(int value) {
        validate(value);
        this.value = value;
    }
    private  void validate(int value) {

        if (isNagativeNumber(value)) {
            throw new IllegalArgumentException("0또는 음수를 전달할 수 없습니다");
        }
    }

    private boolean isNagativeNumber(int value) {
        return value <= 0;
    }

    public int toInt() {
        return value;
    }
}
