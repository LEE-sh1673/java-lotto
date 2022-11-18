package lotto;

import camp.nextstep.edu.missionutils.Console;
import lotto.domain.LottoMachine;
import lotto.domain.Lottos;
import lotto.domain.Money;

public class Application {
    public static void main(String[] args) {

        System.out.println("구입금액을 입력해주세요.");
        int amount = Integer.parseInt(Console.readLine());

        Money money = new Money(amount);
        Lottos lottos = new Lottos(LottoMachine.publish(money));
        //OutputView.printLottos(lottos);


    }
}
