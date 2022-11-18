package lotto.domain;

import static lotto.domain.ErrorMessage.INVALID_LOTTO_NUMBER_BOUND;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    @DisplayName("당첨 번호에 1~45의 범위를 벗어나는 숫자가 있으면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 46, -1})
    void createLottoNumberByOutOfBound(int number) {
        assertThatThrownBy(() -> new LottoNumber(number))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(INVALID_LOTTO_NUMBER_BOUND.getMessage());
    }
}
