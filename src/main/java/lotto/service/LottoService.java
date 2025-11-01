package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.constants.LottoConstant;
import lotto.domain.Lotto;
import lotto.domain.PurchasedLottos;
import lotto.domain.PayAmount;

import java.util.ArrayList;
import java.util.List;

public class LottoService {
    public PurchasedLottos generateLottos(PayAmount payAmount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < payAmount.countLotto(); i++) {
            Lotto lotto = new Lotto(generateLottoNumbers());
            lottos.add(lotto);
        }

        return new PurchasedLottos(lottos, payAmount);
    }

    private List<Integer> generateLottoNumbers() {
        List<Integer> lottoNumbers = Randoms.pickUniqueNumbersInRange(
                LottoConstant.LOTTO_MIN_NUMBER,
                LottoConstant.LOTTO_MAX_NUMBER,
                LottoConstant.LOTTO_NUMBERS_LENGTH);
        return lottoNumbers.stream().sorted().toList();
    }
}
