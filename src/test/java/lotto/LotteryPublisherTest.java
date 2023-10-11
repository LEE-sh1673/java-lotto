package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LotteryPublisherTest {

    private static final String ERROR_MESSAGE = "[ERROR]";

    @ParameterizedTest
    @ValueSource(doubles = {0, 1, 1_001, 2_001, 10_100})
    @DisplayName("금액이 1,000원으로 나누어 떨어지지 않으면 예외가 발생한다.")
    void publishLottoByNotDividedBy1000(final double money) {
        assertThatThrownBy(() -> LotteryPublisher.publish(money))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(ERROR_MESSAGE);
    }
}
