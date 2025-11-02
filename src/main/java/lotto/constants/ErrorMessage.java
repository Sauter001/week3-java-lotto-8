package lotto.constants;

public enum ErrorMessage {
    // === 구입 금액 ===
    PAY_AMOUNT_NOT_DIVIDED_IN_1000("로또 구입 금액은 1000원 단위여야 합니다."),
    PAY_AMOUNT_NOT_POSITIVE("로또 구입 금액은 0 또는 음수가 될 수 없습니다."),
    PAY_AMOUNT_NOT_INTEGER("구입 금액이 정수가 아닙니다."),
    PAY_AMOUNT_EMPTY("구입 금액이 입력되지 않았습니다."),

    // === 로또 ====
    LOTTO_NUMBER_DUPLICATES("번호가 중복됩니다."),
    GENERATED_LOTTO_MISMATCH("로또 생성 개수가 구입 개수와 일치하지 않습니다."),

    // === 당첨 번호 ===
    WINNING_NUMBER_CANNOT_SPLIT("공백은 당첨 번호가 될 수 없습니다."),
    WINNING_NUMBER_NOT_INTEGER("당첨 번호가 정수가 아닙니다."),

    // === 보너스 번호 ===
    BONUS_NUMBER_EMPTY("보너스 번호가 입력되지 않았습니다."),
    BONUS_NUMBER_NOT_INTEGER("보너스 번호가 정수가 아닙니다."),
    BONUS_NUMBER_DUPLICATED_WITH_WINNING_NUMBER("보너스 번호가 당첨 번호와 중복됩니다.");


    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
