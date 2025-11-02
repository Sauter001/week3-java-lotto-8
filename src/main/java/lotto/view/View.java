package lotto.view;

import lotto.domain.*;
import lotto.dto.PurchasedLottosDto;
import lotto.dto.WinningResultDto;

public interface View {
    PayAmount readPayAmount();
    WinningCriteria readWinningCriteria();

    void printPurchasedLottos(PurchasedLottosDto lottos);

    void printWinningResult(WinningResultDto winningResultDto);

    void printError(Exception e);

    void close();
}
