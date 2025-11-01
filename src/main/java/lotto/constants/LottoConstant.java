package lotto.constants;

import lotto.exception.UtilClassException;

public final class LottoConstant {
    public static final int LOTTO_MIN_NUMBER = 1;
    public static final int LOTTO_MAX_NUMBER = 45;
    public static final int LOTTO_NUMBERS_LENGTH = 6;

    private LottoConstant() {
        // reflection 조작으로 생성 방지용
        throw new UtilClassException();
    }
}
