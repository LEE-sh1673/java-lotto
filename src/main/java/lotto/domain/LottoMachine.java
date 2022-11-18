package lotto.domain;

import static lotto.domain.LottoAttributes.LOTTO_PRICE;
import static lotto.domain.LottoAttributes.MIN_LOTTO_NUMBER;
import static lotto.domain.LottoAttributes.MAX_LOTTO_NUMBER;
import static lotto.domain.LottoAttributes.LOTTO_NUMBERS_SIZE;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoMachine {

    public static List<Lotto> publish(final Money money) {
        int numberOfLotto = money.getAmount() / LOTTO_PRICE;
        return Stream.generate(() -> new Lotto(createNonDuplicateNumbers()))
            .limit(numberOfLotto)
            .collect(Collectors.toList());
    }

    private static List<Integer> createNonDuplicateNumbers() {
        return Randoms.pickUniqueNumbersInRange(
            MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER, LOTTO_NUMBERS_SIZE
        );
    }
}
