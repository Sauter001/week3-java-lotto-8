package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.constants.Rank;
import lotto.constants.UIConstant;
import lotto.domain.PayAmount;
import lotto.domain.WinningCriteria;
import lotto.domain.WinningNumbers;
import lotto.dto.LottoDto;
import lotto.dto.PurchasedLottosDto;
import lotto.dto.WinningResultDto;
import lotto.parser.InputParser;
import lotto.parser.PayAmountParser;
import lotto.parser.WinningCriteriaParser;
import lotto.parser.WinningNumbersParser;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static lotto.constants.UIConstant.*;

public class ConsoleView implements View {
    @Override
    public PayAmount readPayAmount() {
        InputParser<PayAmount> parser = new PayAmountParser();
        return readWithRetry(PROMPT_PAY_AMOUNT, parser);
    }

    @Override
    public WinningCriteria readWinningCriteria() {
        WinningNumbers winningNumbers = readWinningNumbers();
        InputParser<WinningCriteria> parser = new WinningCriteriaParser(winningNumbers);
        return readWithRetry(PROMPT_BONUS_NUMBER, parser);
    }

    private WinningNumbers readWinningNumbers() {
        InputParser<WinningNumbers> parser = new WinningNumbersParser();
        return readWithRetry(PROMPT_WINNING_NUMBER, parser);
    }

    @Override
    public void printPurchasedLottos(PurchasedLottosDto lottosDto) {
        int lottoCount = lottosDto.size();

        System.out.println();
        System.out.printf(PROMPT_PURCHASED_LOTTOS_FORMAT, lottoCount);
        for (LottoDto lotto : lottosDto.lottos()) {
            printLotto(lotto);
        }
        System.out.println();
    }

    @Override
    public void printWinningResult(WinningResultDto winningResultDto) {
        List<Rank> ranksToPrint = List.of(Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST);
        Map<Rank, Integer> counter = winningResultDto.counter();

        System.out.println();
        System.out.println(OUTPUT_WINNING_STATISTICS_TITLE);
        System.out.println(DIVISION);
        for (Rank rank : ranksToPrint) {
            printRankInfo(rank, counter.get(rank));
        }
        printProfitRate(winningResultDto.rateOfProfit());
    }

    private void printProfitRate(BigDecimal bigDecimal) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.0");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        System.out.printf(OUTPUT_PROFIT_RATE, decimalFormat.format(bigDecimal));
    }

    private void printRankInfo(Rank rank, Integer matchedCount) {
        String bonusRequirement = "";
        if (rank.isRequireBonus()) {
            bonusRequirement = BONUS_REQUIRED;
        }

        DecimalFormat prizeFormat =  new DecimalFormat("#,##0");
        String prizeOutput = prizeFormat.format(rank.getPrize());

        System.out.printf(OUTPUT_MATCHED_RANK_INFO_FORMAT, rank.getNumberToMatch(), bonusRequirement, prizeOutput, matchedCount);
    }

    private void printLotto(LottoDto lotto) {
        String lottoContent = lotto.numbers().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(LOTTO_NUMBER_DELIMITER, LIST_PREFIX, LIST_SUFFIX));
        System.out.println(lottoContent);
    }

    @Override
    public void printError(Exception e) {
        if (!e.getMessage().startsWith(UIConstant.ERROR_PREFIX)) {
            System.out.printf(UIConstant.ERROR_FORMAT + "\n", e.getMessage());
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
                throw new IllegalStateException(ERR_INPUT_UNACCEPTABLE, e);
            }
        }
    }
}
