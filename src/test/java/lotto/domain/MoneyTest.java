package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {

    @DisplayName("천원 미만의 금액에 대한 예외 처리")
    @ParameterizedTest
    @ValueSource(ints = {999, 10, 0})
    void shouldThrowException_underPrice(int amount) {
        assertThatThrownBy(
            () -> new Money(amount)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("1,000원으로 나누어 떨어지지 않는 경우 대한 예외 처리")
    @ParameterizedTest
    @ValueSource(ints = {1_001, 2_100, -1_001})
    void shouldThrowException_NotDivisibleWithThousand(int amount) {
        assertThatThrownBy(
            () -> new Money(amount)
        ).isInstanceOf(IllegalArgumentException.class);
    }

}
