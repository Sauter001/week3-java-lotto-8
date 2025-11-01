package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.constants.UIConstant;
import lotto.domain.Lotto;
import lotto.domain.PayAmount;
import lotto.domain.PurchasedLottos;
import lotto.view.converter.InputConverter;
import lotto.view.converter.PayAmountConverter;

import java.util.stream.Collectors;

public class ConsoleView implements View {
    private static final String PROMPT_PAY_AMOUNT = "구입금액을 입력해 주세요.";
    private static final String PROMPT_PURCHASED_LOTTOS_FORMAT = "%d개를 구매했습니다.\n";
    private static final String LOTTO_NUMBER_DELIMITER = ", ";
    private static final String LIST_PREFIX = "[";
    private static final String LIST_SUFFIX = "]";

    @Override
    public PayAmount readPayAmount() {
        InputConverter<PayAmount> converter = new PayAmountConverter(PROMPT_PAY_AMOUNT);
        return converter.convert();
    }

    @Override
    public void printPurchasedLottos(PurchasedLottos lottos) {
        int lottoCount = lottos.size();

        System.out.println();
        System.out.printf(PROMPT_PURCHASED_LOTTOS_FORMAT, lottoCount);
        for (Lotto lotto : lottos.getPurchasedLottos()) {
            printLotto(lotto);
        }
        System.out.println();
    }

    private void printLotto(Lotto lotto) {
        String lottoContent = lotto.getNumbers().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(LOTTO_NUMBER_DELIMITER, LIST_PREFIX, LIST_SUFFIX));
        System.out.println(lottoContent);
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
