package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.PayAmount;
import lotto.domain.PurchasedLottos;
import lotto.domain.WinningNumbers;

public interface View {
    PayAmount readPayAmount();
    WinningNumbers readWinningNumbers();

    void printPurchasedLottos(PurchasedLottos lottos);

    void printError(Exception e);

    void close();
}
