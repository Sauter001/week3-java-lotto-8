package lotto.exception;

import lotto.constants.ErrorCode;

public class LottoException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE_FORMAT = "[ERROR] %s";
    private static final String DEFAULT_ERROR_MESSAGE = "에러가 발생했습니다.";

    public LottoException() {
        super(String.format(ERROR_MESSAGE_FORMAT, DEFAULT_ERROR_MESSAGE));
    }

    public LottoException(ErrorCode errorCode) {
        super(String.format(ERROR_MESSAGE_FORMAT, errorCode.getMessage()));
    }
}
