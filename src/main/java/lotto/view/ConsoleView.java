package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.constants.UIConstant;
import lotto.domain.PayAmount;
import lotto.view.converter.InputConverter;
import lotto.view.converter.PayAmountConverter;

public class ConsoleView implements View {
    private static final String PROMPT_PAY_AMOUNT = "구입금액을 입력해 주세요.";

    @Override
    public PayAmount readPayAmount() {
        InputConverter<PayAmount> converter = new PayAmountConverter(PROMPT_PAY_AMOUNT);
        return converter.convert();
    }

    @Override
    public void printError(Exception e) {
        if (!e.getMessage().startsWith(UIConstant.ERROR_PREFIX)) {
            System.out.printf(UIConstant.FORMAT_ERROR + "\n", e.getMessage());
            return;
        }

        System.out.println(e.getMessage());
    }

    @Override
    public void close() {
        Console.close();
    }
}
