package lotto.view;

import lotto.domain.*;

public interface View {
    PayAmount readPayAmount();
    WinningCriteria readWinningCriteria();

    void printPurchasedLottos(PurchasedLottos lottos);

    void printError(Exception e);

    void close();
}
