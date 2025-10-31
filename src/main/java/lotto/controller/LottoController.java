package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.PayAmount;
import lotto.view.View;

public class LottoController {
    private final View view;

    public LottoController(View view) {
        this.view = view;
    }

    public void run() {
        PayAmount payAmount = view.readPayAmount();
        view.close();
    }
}
