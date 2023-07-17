package org.example.password;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class UserTest {

    @DisplayName("password를 Random하게 초기화한다")
    @Test
    void passwordTest() {
        User user = new User();

        user.initPassword(new FixedPasswordGenerator());

        assertThat(user.getPassword()).isNotNull();
    }

    @DisplayName("password가 요구사항에 맞지 않아 null값이 나온다")
    @Test
    void passwordTest2() {
        User user = new User();

        user.initPassword(new unFixedPasswordGenerator());

        assertThat(user.getPassword()).isNull();
    }
}