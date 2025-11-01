package lotto.constants;

public enum ErrorFormat {
    // === 로또 ===
    LOTTO_NUMBER_OUT_OF_RANGE("로또 번호가 될 수 없는 수입니다: %d");

    private final String messageFormat;

    ErrorFormat(String messageFormat) {
        this.messageFormat = messageFormat;
    }

    public String getMessageFormat() {
        return messageFormat;
    }
}
