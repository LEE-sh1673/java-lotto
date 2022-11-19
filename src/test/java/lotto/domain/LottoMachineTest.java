package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoMachineTest {

    @DisplayName("특정한 개수의 로또를 구매할 수 있다.")
    @Test
    void publishLottos() {
        Lottos lottos = LottoMachine.publish(8_000);
        assertThat(lottos.size()).isEqualTo(8);
    }

}
