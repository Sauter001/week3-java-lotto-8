package lotto.constants;

public enum ErrorMessage {
    // === 구입 금액 ===
    PAY_AMOUNT_NOT_DIVIDED_IN_1000("로또 구입 금액은 1000원 단위여야 합니다."),
    PAY_AMOUNT_NOT_POSITIVE("로또 구입 금액은 0 또는 음수가 될 수 없습니다."),
    PAY_AMOUNT_NOT_INTEGER("구입 금액이 정수가 아닙니다."),
    PAY_AMOUNT_EMPTY("구입 금액이 입력되지 않았습니다."),

    // === 로또 ====
    LOTTO_NUMBER_DUPLICATES("번호가 중복됩니다."),
    GENERATED_LOTTO_MISMATCH("로또 생성 개수가 구입 개수와 일치하지 않습니다.");

    // === 당첨 번호 ===


    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
