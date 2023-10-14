package lotto.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoPublisherTest {

    private static final String ERROR_MESSAGE = "[ERROR]";

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 1_001, 2_001, 10_100})
    @DisplayName("금액이 1,000원으로 나누어 떨어지지 않으면 예외가 발생한다.")
    void publishLottoByNotDividedBy1000(final int money) {
        assertThatThrownBy(() -> LottoPublisher.publish(LottoMoney.of(money)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(ERROR_MESSAGE);
    }

    @ParameterizedTest
    @CsvSource({"1,1000", "2,2000", "10,10_000", "11,11000", "100,100000"})
    @DisplayName("금액을 입력받아 로또를 발행할 수 있다.")
    void publishLotto(final int numsLotto, final int money) {
        final List<Lotto> lottos = LottoPublisher.publish(LottoMoney.of(money));
        assertEquals(numsLotto, lottos.size());
    }
}
