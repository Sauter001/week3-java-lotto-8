package lotto.domain;

import lotto.constants.ErrorMessage;
import lotto.exception.LottoException;

public record PayAmount(int amount) {
    private static final int UNIT_LOTTO_PRICE = 1000;

    public PayAmount {
        validate(amount);
    }

    private static void validateUnit(int amount) {
        if (amount % UNIT_LOTTO_PRICE > 0) {
            throw new LottoException(ErrorMessage.PAY_AMOUNT_NOT_DIVIDED_IN_1000);
        }
    }

    private static void validatePositive(int amount) {
        if (amount <= 0) {
            throw new LottoException(ErrorMessage.PAY_AMOUNT_NOT_POSITIVE);
        }
    }

    private void validate(int amount) {
        validatePositive(amount);
        validateUnit(amount);
    }

    public int countLotto() {
        return amount / UNIT_LOTTO_PRICE;
    }
}
