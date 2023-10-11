package lotto;

import java.util.List;

import lotto.exception.ErrorType;

public class LotteryPublisher {

    public static List<Lotto> publish(final double money) {
        validate(money);
        return List.of();
    }

    private static void validate(final double money) {
        if (!isDividedBy1000(money)) {
            throw new IllegalArgumentException(
                ErrorType.MONEY_CANT_DIVIDED_BY_1000.getMessage()
            );
        }
    }

    private static boolean isDividedBy1000(double money) {
        return money != 0 && money % 1000 == 0;
    }
}
