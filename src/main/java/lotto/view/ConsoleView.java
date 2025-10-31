package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.constants.PromptMessage;
import lotto.domain.PayAmount;
import lotto.view.converter.InputConverter;
import lotto.view.converter.PayAmountConverter;

public class ConsoleView implements View {
    @Override
    public PayAmount readPayAmount() {
        InputConverter<PayAmount> converter = new PayAmountConverter(PromptMessage.PROMPT_PAY_AMOUNT);
        return converter.convert();
    }

    @Override
    public void close() {
        Console.close();
    }
}
