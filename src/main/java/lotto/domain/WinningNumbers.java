package lotto.domain;

import lotto.util.LottoNumberValidator;

import java.util.HashSet;
import java.util.List;


public class WinningNumbers {
    private final List<Integer> numbers;

    public WinningNumbers(List<Integer> numbers) {
        validate(numbers);
        this.numbers = sortNumbers(numbers);
    }

    private List<Integer> sortNumbers(List<Integer> numbers) {
        return numbers.stream().sorted().toList();
    }

    public int countMatchedNumber(Lotto lotto) {
        HashSet<Integer> lottoSet = new HashSet<>(lotto.getNumbers());
        int count = 0;

        for (int number : this.numbers) {
            if (lottoSet.contains(number)) {
                count++;
            }
        }

        return count;
    }

    private void validate(List<Integer> numbers) {
        LottoNumberValidator.validateList(numbers);
    }
}
