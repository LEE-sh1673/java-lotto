package lotto.domain;

public enum ErrorMessage {
    LESS_THEN_MINIMUM_AMOUNT("금액은 최소 1,000원입니다."),
    NOT_DIVISIBLE_WITH_THOUSAND("금액은 1,000원으로 나누어 떨어지는 수여야 합니다."),
    INVALID_LOTTO_NUMBERS_SIZE("로또번호는 6개를 입력해야 합니다."),
    DUPLICATED_LOTTO_NUMBER("로또번호는 중복될 수 없습니다."),
    INVALID_LOTTO_NUMBER_BOUND("로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    DUPLICATED_BONUS_NUMBER("보너스 번호는 로또 번호와 중복될 수 없습니다."),
    INVALID_NUMBER_FORMAT("잘못된 형식의 입력입니다.");

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
