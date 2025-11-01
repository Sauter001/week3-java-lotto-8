package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.constants.UIConstant;
import lotto.domain.Lotto;
import lotto.domain.PayAmount;
import lotto.domain.PurchasedLottos;
import lotto.parser.InputParser;
import lotto.parser.PayAmountParser;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ConsoleView implements View {
    private static final String PROMPT_PAY_AMOUNT = "구입금액을 입력해 주세요.";
    private static final String PROMPT_PURCHASED_LOTTOS_FORMAT = "%d개를 구매했습니다.\n";
    private static final String INPUT_UNACCEPTABLE = "입력을 더 이상 받을 수 없습니다.";

    private static final String LOTTO_NUMBER_DELIMITER = ", ";
    private static final String LIST_PREFIX = "[";
    private static final String LIST_SUFFIX = "]";

    @Override
    public PayAmount readPayAmount() {
        PayAmountParser parser = new PayAmountParser();
        return readWithRetry(PROMPT_PAY_AMOUNT, parser);
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

    private <T> T readWithRetry(String prompt, InputParser<T> parser) {
        while (true) {
            try {
                System.out.println(prompt);
                String input = Console.readLine();
                return parser.parse(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (NoSuchElementException e) {
                throw new IllegalStateException(INPUT_UNACCEPTABLE, e);
            }
        }
    }
}
