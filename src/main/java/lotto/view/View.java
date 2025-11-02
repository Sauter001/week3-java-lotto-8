package lotto.view;

import lotto.domain.*;
import lotto.dto.PurchasedLottosDto;

public interface View {
    PayAmount readPayAmount();
    WinningCriteria readWinningCriteria();

    void printPurchasedLottos(PurchasedLottosDto lottos);

    void printError(Exception e);

    void close();
}
