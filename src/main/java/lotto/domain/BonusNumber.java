package lotto.domain;

import lotto.util.LottoNumberValidator;

public record BonusNumber(int value) {
    public BonusNumber {
        validate(value);
    }

    private void validate(int number) {
        LottoNumberValidator.validateRange(number);
    }
}
