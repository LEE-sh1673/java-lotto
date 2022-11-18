package lotto;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoNumber;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {

        System.out.println("구입금액을 입력해주세요.");
        int amount = Integer.parseInt(Console.readLine());

        Money money = new Money(amount);
        Lottos lottos = new Lottos(LottoMachine.publish(money));
        OutputView.printLottos(lottos);

        System.out.println("\n당첨 번호를 입력해 주세요.");
        List<Integer> numbers = Arrays.stream(Console.readLine().split(","))
            .map(Integer::parseInt)
            .collect(Collectors.toList());

        System.out.println("\n보너스 번호를 입력해 주세요.");
        int number = Integer.parseInt(Console.readLine());
        LottoNumber bonus = new LottoNumber(number);

        WinningLotto winningLotto = new WinningLotto(new Lotto(numbers), bonus);
        EnumMap<Rank, Integer> lottoRanks = new EnumMap<>(Rank.class);

        for (Lotto lotto : lottos) {
            lottoRanks.merge(winningLotto.compare(lotto), 1, Integer::sum);
        }
        LottoResult lottoResult = new LottoResult(lottoRanks);
        OutputView.printLottoResult(lottoResult);
        OutputView.printProfitRate(lottoResult.calculateProfitRate());
    }
}
