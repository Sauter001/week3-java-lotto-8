package lotto.domain;

import lotto.constants.ErrorCode;
import lotto.exception.LottoException;

public record PayAmount(int amount) {
    private static final int UNIT_LOTTO_PRICE = 1000;

    public PayAmount {
        validateAmount(amount);
    }

    private void validateAmount(int amount) {
        if (amount <= 0) {
            throw new LottoException(ErrorCode.PAY_AMOUNT_NOT_POSITIVE);
        }

        if (amount % UNIT_LOTTO_PRICE > 0) {
            throw new LottoException(ErrorCode.PAY_AMOUNT_NOT_DIVIDED_IN_1000);
        }
    }

    public int getNumberOfLottos() {
        return amount / UNIT_LOTTO_PRICE;
    }
}
