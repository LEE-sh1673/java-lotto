package lotto.model;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {

    public static final int MAXIMUM_LOTTO_SIZE = 6;

    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = mapNumbers(numbers);
    }

    private static List<LottoNumber> mapNumbers(final List<Integer> numbers) {
        return numbers.stream()
            .map(LottoNumber::new)
            .collect(Collectors.toList());
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
    }

    private void validateDuplicate(final List<Integer> numbers) {
        HashSet<Integer> uniqueNumbers = new HashSet<>(numbers);

        if (numbers.size() != uniqueNumbers.size()) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateSize(List<Integer> numbers) {
        if (numbers.size() != MAXIMUM_LOTTO_SIZE) {
            throw new IllegalArgumentException();
        }
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
