package lotto.controller;

import lotto.domain.PayAmount;
import lotto.domain.PurchasedLottos;
import lotto.domain.WinningCriteria;
import lotto.dto.WinningResultDto;
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
        view.printPurchasedLottos(purchasedLottos.toDto());

        WinningCriteria winningCriteria = view.readWinningCriteria();
        WinningResultDto winningResultDto = lottoService.aggregateWinningResult(payAmount, purchasedLottos, winningCriteria);
        view.printWinningResult(winningResultDto);
        view.close();
    }
}
