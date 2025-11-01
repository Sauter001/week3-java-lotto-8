package lotto.constants;

import lotto.exception.LottoException;

public enum LottoNumberType {
    LOTTO("로또"),
    WINNING("당첨"),
    BONUS("보너스");

    private final String typeName;

    LottoNumberType(String typeName) {
        this.typeName = typeName;
    }

    public LottoException createRangeException(int number) {
        return new LottoException(ErrorFormat.LOTTO_NUMBER_OUT_OF_RANGE,
                typeName,
                LottoConstant.LOTTO_MIN_NUMBER,
                LottoConstant.LOTTO_MAX_NUMBER, number);
    }

    public LottoException createDuplicationException() {
        return new LottoException(ErrorFormat.LOTTO_NUMBER_DUPLICATES, typeName);
    }

    public LottoException createInvalidLengthException() {
        return new LottoException(ErrorFormat.LOTTO_LENGTH_NOT_CORRECT, typeName, LottoConstant.LOTTO_NUMBERS_LENGTH);
    }
}
