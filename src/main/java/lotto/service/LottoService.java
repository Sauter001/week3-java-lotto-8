package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.constants.LottoConstant;
import lotto.constants.Rank;
import lotto.domain.*;
import lotto.dto.WinningResultDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LottoService {
    public PurchasedLottos generateLottos(PayAmount payAmount) {
        final List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < payAmount.countLotto(); i++) {
            Lotto lotto = new Lotto(generateLottoNumbers());
            lottos.add(lotto);
        }

        return new PurchasedLottos(lottos, payAmount);
    }

    public WinningResultDto aggregateWinningResult(
            PayAmount payAmount,
            PurchasedLottos purchasedLottos,
            WinningCriteria winningCriteria) {
        List<Rank> determinedRanks = purchasedLottos.determineRanksBy(winningCriteria);
        WinningCounter counter = WinningCounter.from(determinedRanks);
        BigDecimal rateOfProfit = counter.calculateRateOfProfit(payAmount);
        return new WinningResultDto(counter.getMap(), rateOfProfit);
    }

    private List<Integer> generateLottoNumbers() {
        List<Integer> lottoNumbers = Randoms.pickUniqueNumbersInRange(
                LottoConstant.LOTTO_MIN_NUMBER,
                LottoConstant.LOTTO_MAX_NUMBER,
                LottoConstant.LOTTO_NUMBERS_LENGTH);
        return lottoNumbers.stream().sorted().toList();
    }
}
