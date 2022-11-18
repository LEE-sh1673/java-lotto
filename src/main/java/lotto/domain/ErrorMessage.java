package lotto.domain;

public enum ErrorMessage {
    LESS_THEN_MINIMUM_AMOUNT("금액은 최소 1,000원입니다."),
    NOT_DIVISIBLE_WITH_THOUSAND("금액은 1,000원으로 나누어 떨어지는 수여야 합니다.");

    private static final String MESSAGE_FORMAT
        = "[ERROR] %s";

    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return String.format(MESSAGE_FORMAT, message);
    }
}
