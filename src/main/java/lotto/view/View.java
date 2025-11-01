package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.PayAmount;
import lotto.domain.PurchasedLottos;

public interface View {
    PayAmount readPayAmount();

    void printPurchasedLottos(PurchasedLottos lottos);

    void printError(Exception e);

    void close();
}
