package lotto.controller;

import lotto.domain.*;
import lotto.service.LottoService;
import lotto.view.View;

public class LottoController {
    private final View view;
    private final LottoService lottoService;

    public LottoController(View view, LottoService lottoService) {
        this.view = view;
        this.lottoService = lottoService;
    }

    public void run() {
        PayAmount payAmount = view.readPayAmount();
        PurchasedLottos purchasedLottos = lottoService.generateLottos(payAmount);
        view.printPurchasedLottos(purchasedLottos);
        WinningCriteria winningCriteria = view.readWinningCriteria();
        view.close();
    }
}
