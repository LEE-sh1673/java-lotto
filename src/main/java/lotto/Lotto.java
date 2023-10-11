package lotto;

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
        validateDuplicated(numbers);
    }

    private void validateDuplicated(final List<Integer> numbers) {
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

    public int difference(final Lotto other) {
        return (int)numbers.stream()
            .filter(other::contains)
            .count();
    }

    public boolean contains(final LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }
}
