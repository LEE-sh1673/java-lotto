package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {

    @DisplayName("천원 미만의 금액에 대한 예외 처리")
    @ParameterizedTest
    @ValueSource(ints = {999, 10, 0})
    void shouldThrowException_underPrice(Integer amount) {
        assertThatThrownBy(
            () -> new Money(amount)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
