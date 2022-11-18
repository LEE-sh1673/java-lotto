package lotto.domain;

import static lotto.domain.ErrorMessage.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class WinningLottoTest {

    static Lotto lotto;

    @BeforeAll
    static void setUp() {
        lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
    }

    @DisplayName("당첨 번호와 중복된 보너스 번호가 있으면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void createWinningLottoNumbersContainBonus(int number) {
        assertThatThrownBy(
            () -> new WinningLotto(lotto, new LottoNumber(number)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(DUPLICATED_BONUS_NUMBER.getMessage());
    }
}
