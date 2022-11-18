package lotto.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;
import static lotto.domain.ErrorMessage.INVALID_LOTTO_NUMBERS_SIZE;
import static lotto.domain.ErrorMessage.DUPLICATED_LOTTO_NUMBER;

class LottoTest {

    static Lotto lotto;

    @BeforeAll
    static void setUp() {
        lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
    }

    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createLottoByOverSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(INVALID_LOTTO_NUMBERS_SIZE.getMessage());
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(DUPLICATED_LOTTO_NUMBER.getMessage());
    }

    // 아래에 추가 테스트 작성 가능
    @DisplayName("로또 번호에 특정 숫자가 포함되어 있는지 구할 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void containNumberInLottoNumbers(int number) {
        assertThat(lotto.contains(new LottoNumber(number))).isTrue();
    }
}
