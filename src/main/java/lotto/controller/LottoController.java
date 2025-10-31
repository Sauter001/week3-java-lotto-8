package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.PayAmount;
import lotto.service.LottoService;
import lotto.view.View;

import java.util.List;

public class LottoController {
    private final View view;
    private final LottoService lottoService;

    public LottoController(View view, LottoService lottoService) {
        this.view = view;
        this.lottoService = lottoService;
    }

    public void run() {
        PayAmount payAmount = view.readPayAmount();
        List<Lotto> lottos = lottoService.generateLottos(payAmount);
        view.close();
    }
}
