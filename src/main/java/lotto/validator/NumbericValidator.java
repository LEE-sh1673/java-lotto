package lotto.validator;

import java.util.Arrays;
import java.util.regex.Pattern;

import lotto.exception.ErrorType;

public class NumbericValidator {

    private static final Pattern NUMBER_REGEX = Pattern.compile("^[0-9]*$");

    public static void validateNumbers(final String input) {
        Arrays.stream(input.split(","))
            .forEach(NumbericValidator::validateNumeric);
    }

    public static void validateNumber(final String input) {
        validateNumeric(input);
    }

    private static void validateNumeric(final String input) {
        if (isNullOrBlank(input) || !isNumeric(input)) {
            throw new IllegalArgumentException(
                ErrorType.INVALID_NUMBER_FORMAT.getMessage()
            );
        }
    }

    private static boolean isNullOrBlank(final String input) {
        return input == null || input.isBlank();
    }

    private static boolean isNumeric(final String input) {
        return NUMBER_REGEX.matcher(input).matches();
    }
}
