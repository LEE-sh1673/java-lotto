package lotto.domain;

import static lotto.domain.ErrorMessage.INVALID_LOTTO_NUMBERS_SIZE;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {

    private static final int MAX_LOTTO_NUMBERS_SIZE = 6;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
    }

    // TODO: 추가 기능 구현
    private void validateSize(final List<Integer> numbers) {
        if (numbers.size() != MAX_LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException(
                INVALID_LOTTO_NUMBERS_SIZE.getMessage()
            );
        }
    }

    private void validateDuplicate(final List<Integer> numbers) {
        Set<Integer> nonDuplicatedNumbers = new HashSet<>(numbers);
        if (nonDuplicatedNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(
                ErrorMessage.DUPLICATED_LOTTO_NUMBER.getMessage()
            );
        }
    }
}
