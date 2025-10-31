package lotto.view;

import lotto.domain.PayAmount;

public interface View {
    PayAmount readPayAmount();

    void close();
}
