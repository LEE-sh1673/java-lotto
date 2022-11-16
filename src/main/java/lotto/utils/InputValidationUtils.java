package lotto.utils;

import static lotto.validator.ErrorMessage.DIVISIBLE_WITH_THOUSAND;
import static lotto.validator.ErrorMessage.NOT_ENOUGH_MONEY;
import static lotto.validator.ErrorMessage.INVALID_FORMAT;

public class InputValidationUtils {

    private static final int MIN_PRICE = 1000;

    public static void validatePrice(final int price) {
        validateRange(price);
        validateDivisible(price);
    }

    private static void validateDivisible(final int price)
        throws IllegalArgumentException {

        if (!isDivisibleWithThousand(price)) {
            throw new IllegalArgumentException(
                DIVISIBLE_WITH_THOUSAND.message());
        }
    }

    private static boolean isDivisibleWithThousand(final int price) {
        return price != 0 && price % MIN_PRICE == 0;
    }

    private static void validateRange(final int price)
        throws IllegalArgumentException {

        if (isOutOfLowerBound(price)) {
            throw new IllegalArgumentException(
                NOT_ENOUGH_MONEY.message());
        }
    }

    private static boolean isOutOfLowerBound(final int price) {
        return price < MIN_PRICE;
    }

    public static void validateNumbers(final String[] splitNumbers) {
        for (String splitNumber : splitNumbers) {
            validateFormat(splitNumber);
        }
    }

    public static void validateFormat(final String input)
        throws IllegalArgumentException {

        if (!isNumber(input)) {
            throw new IllegalArgumentException(
                INVALID_FORMAT.message());
        }
    }

    private static boolean isNumber(final String input) {
        return input.matches("[0-9]+");
    }
}
