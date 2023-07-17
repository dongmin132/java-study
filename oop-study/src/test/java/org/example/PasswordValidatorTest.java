package org.example;

import org.example.password.PasswordValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

/*
요구사항
• 비밀번호는 최소 8자 이상 12자 이하여야 한다.
• 비밀번호가 8자 미만 또는 12자 초과인 경우 IllegalArgumentException 예외를 발생
시킨다.
• 경계조건에 대해 테스트 코드를 작성해야 한다
 */
public class PasswordValidatorTest {
    @DisplayName("비밀번호가 최소 8자 이상 12 이하면 예외 발생하지 않음")
    @Test
    void validatePasswordTest() {
        assertThatCode(() -> PasswordValidator.validate("serverwizard"))
                .doesNotThrowAnyException();        //해당 부분이 호출 됐을 때 예외가 발생하지 않으면 테스트가 통과
    }

    @DisplayName("비밀번호가 8자 미만 또는 12자를 초과하는 경우 IllegalArgumentException 예외 발생")
    @ParameterizedTest      //여러개의 검증 과정이 있는 테스트를 @ValueSource, @CsvSource와 같은 어노테이션을 이용해 활용할 수 있다
    @ValueSource(strings = {"1234567","1234567890121"})     //경계값 7자와 13자로 테스트를 해봄
    void validatePasswordTest2(String password) {   //매개변수 password에 위에 @ValueSource에 설정한 내용이 들어가게됨
        assertThatCode(() -> PasswordValidator.validate(password))        //예외가 발생함
                .isInstanceOf(IllegalArgumentException.class)   //해당 메서드(PasswordValidator.validate)가 호출 되면 IllegalArgumentException 타입의 예외가 발생하여야 하고
                .hasMessage("비밀번호는 최소 8자 이상 12자 이하여야 한다");      //비밀번호는 최소 8자~~ 라는 메시지의 오류가 발생해야 된다는 의미
    }
}
