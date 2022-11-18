package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoMachineTest {

    @DisplayName("특정한 개수의 로또를 구매할 수 있다.")
    @Test
    void publishLottos() {
        Money money = new Money(8_000);
        Lottos lottos = new Lottos(LottoMachine.publish(money));
        assertThat(lottos.size()).isEqualTo(8);
    }

}
