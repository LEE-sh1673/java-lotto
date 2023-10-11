package lotto;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.exception.ErrorType;

public class LotteryPublisher {

    private static final int LOTTO_PRICE = 1_000;

    public static Lottos publish(final double money) {
        validate(money);
        return new Lottos(publishLottos(money));
    }

    private static void validate(final double money) {
        if (!isDividedBy1000(money)) {
            throw new IllegalArgumentException(
                ErrorType.MONEY_CANT_DIVIDED_BY_1000.getMessage()
            );
        }
    }

    private static boolean isDividedBy1000(final double money) {
        return money != 0 && money % LOTTO_PRICE == 0;
    }

    private static List<Lotto> publishLottos(final double money) {
        return Stream.generate(LotteryPublisher::publishLotto)
            .limit(getNumberOfLotto(money))
            .collect(Collectors.toList());
    }

    private static Lotto publishLotto() {
        final List<Integer> numbers = createNonDuplicatedNumbers().stream()
            .sorted(Comparator.comparingInt(Integer::intValue))
            .collect(Collectors.toList());
        return new Lotto(numbers);
    }

    private static List<Integer> createNonDuplicatedNumbers() {
        return Randoms.pickUniqueNumbersInRange(
            LottoNumber.MIN_LOTTO_NUMBER,
            LottoNumber.MAX_LOTTO_NUMBER,
            Lotto.MAXIMUM_LOTTO_SIZE
        );
    }

    private static int getNumberOfLotto(double money) {
        return (int)(money / LOTTO_PRICE);
    }
}
