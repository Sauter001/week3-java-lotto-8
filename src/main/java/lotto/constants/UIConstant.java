package lotto.constants;

import lotto.exception.UtilClassException;

public final class UIConstant {
    // === 입력 프롬프트 ===
    public static final String PROMPT_WINNING_NUMBER = "당첨 번호를 입력해 주세요.";
    public static final String PROMPT_PAY_AMOUNT = "구입금액을 입력해 주세요.";
    public static final String PROMPT_PURCHASED_LOTTOS_FORMAT = "%d개를 구매했습니다.\n";
    public static final String PROMPT_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";

    // === 출력 내용 ===
    public static final String OUTPUT_WINNING_STATISTICS_TITLE = "당첨 통계";
    public static final String OUTPUT_MATCHED_RANK_INFO_FORMAT = "%d개 일치%s (%s원) - %d개\n";
    public static final String OUTPUT_PROFIT_RATE = "총 수익률은 %s%%입니다.\n";

    // === 에러 메시지 ===
    public static final String ERR_INPUT_UNACCEPTABLE = "입력을 더 이상 받을 수 없습니다.";

    // === 기타 출력 컴포넌트 ===
    public static final String BONUS_REQUIRED = ", 보너스 볼 일치";
    public static final String DIVISION = "---";
    public static final String ERROR_PREFIX = "[ERROR]";
    public static final String ERROR_FORMAT = "[ERROR] %s";
    public static final String LOTTO_NUMBER_DELIMITER = ", ";
    public static final String LIST_PREFIX = "[";
    public static final String LIST_SUFFIX = "]";

    public UIConstant() {
        throw new UtilClassException();
    }
}
