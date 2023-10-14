package lotto.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    private static final String ERROR_MESSAGE = "[ERROR]";

    @ParameterizedTest
    @ValueSource(ints = {1, 45})
    @DisplayName("로또 번호는 1부터 45까지의 입력할 수 있다.")
    void createLottoNumberInRange(final int number) {
        final LottoNumber lottoNumber = new LottoNumber(number);
        assertEquals(number, lottoNumber.getNumber());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46})
    @DisplayName("로또 번호가 1부터 45 사이의 숫자가 아니면 오류가 발생한다.")
    void createLottoNumberByOutOfRange(final int number) {
        assertThatThrownBy(() -> new LottoNumber(number))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(ERROR_MESSAGE);
    }

    @Test
    @DisplayName("로또 번호를 서로 비교할 수 있다.")
    void compareLottoNumbers() {
        for (int number = 1; number <= 45; number++) {
            assertEquals(
                new LottoNumber(number),
                new LottoNumber(number)
            );
        }
    }
}
