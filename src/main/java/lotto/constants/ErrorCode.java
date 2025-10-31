package lotto.constants;

public enum ErrorCode {
    // 로또 구입 금액 (1XX)
    PAY_AMOUNT_NOT_DIVIDED_IN_1000("로또 구입 금액은 1000원 단위여야 합니다."),
    PAY_AMOUNT_NOT_POSITIVE("로또 구입 금액은 0 또는 음수가 될 수 없습니다."),
    PAY_AMOUNT_NOT_INTEGER("구입 금액이 정수가 아닙니다.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
