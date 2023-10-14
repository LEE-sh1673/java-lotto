package lotto.model;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import lotto.exception.ErrorType;

public class Lotto {

    public static final int MAXIMUM_LOTTO_SIZE = 6;

    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = mapNumbers(numbers);
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
    }

    private static void validateSize(List<Integer> numbers) {
        if (numbers.size() != MAXIMUM_LOTTO_SIZE) {
            throw new IllegalArgumentException(
                ErrorType.INVALID_LOTTO_SIZE.getMessage()
            );
        }
    }

    private void validateDuplicate(final List<Integer> numbers) {
        HashSet<Integer> uniqueNumbers = new HashSet<>(numbers);

        if (numbers.size() != uniqueNumbers.size()) {
            throw new IllegalArgumentException(
                ErrorType.LOTTO_NUMBER_DUPLICATE.getMessage()
            );
        }
    }

    private static List<LottoNumber> mapNumbers(final List<Integer> numbers) {
        return numbers.stream()
            .map(LottoNumber::new)
            .collect(Collectors.toList());
    }

    public int countNumberOfContains(final Lotto other) {
        return (int)numbers.stream()
            .filter(other::containsNumber)
            .count();
    }

    public boolean containsNumber(final LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }

    @Override
    public String toString() {
        return this.numbers.toString();
    }
}
