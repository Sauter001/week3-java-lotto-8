package lotto.constants;

import lotto.exception.UtilClassException;

public final class UIConstant {
    public static final String ERROR_PREFIX = "[ERROR]";
    public static final String FORMAT_ERROR = "[ERROR] %s";

    private UIConstant() {
        throw new UtilClassException();
    }
}
