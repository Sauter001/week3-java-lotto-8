package lotto.constants;

public enum ErrorFormat {
    // === 로또 ===
    LOTTO_NUMBER_OUT_OF_RANGE("%s 번호가 범위를 벗어 났습니다. [%d~%d] : %d"),
    LOTTO_NUMBER_DUPLICATES("%s 번호가 중복됩니다."),
    LOTTO_LENGTH_NOT_CORRECT("%s 번호 개수는 %d개여야 합니다."),;

    private final String messageFormat;

    ErrorFormat(String messageFormat) {
        this.messageFormat = messageFormat;
    }

    public String getMessageFormat() {
        return messageFormat;
    }
}
