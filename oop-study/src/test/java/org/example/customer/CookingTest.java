package org.example.customer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CookingTest {
    //요리사 객체를 만들고 내가원하는 메뉴를 만들고 요리사에게 음식을 만들어달라고 요청하는 형태
    @DisplayName("메뉴에 해당하는 음식을 만든다.")
    @Test
    void makeCookTest() {
        Cooking cooking = new Cooking();
        MenuItem menuItem = new MenuItem("돈까스", 5000);

        Cook cook = cooking.makeCook(menuItem);

        System.out.println(cook);
        assertThat(cook).isEqualTo(new Cook("돈까스", 5000));

    }
}
