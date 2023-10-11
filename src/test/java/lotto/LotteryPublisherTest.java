package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @ParameterizedTest
    @CsvSource({"1,1000", "2,2000", "10,10_000", "11,11000", "100,100000"})
    @DisplayName("금액을 입력받아 로또를 발행할 수 있다.")
    void publishLotto(final int numsLotto, final double money) {
        final Lottos lottos = LotteryPublisher.publish(money);
        assertEquals(numsLotto, lottos.getNumberOfLotto());
    }
}
