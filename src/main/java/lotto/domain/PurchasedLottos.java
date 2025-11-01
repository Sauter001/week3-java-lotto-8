package lotto.domain;

import lotto.constants.ErrorMessage;
import lotto.exception.LottoException;

import java.util.Collections;
import java.util.List;

public class PurchasedLottos {
    private List<Lotto> lottos;

    public PurchasedLottos(List<Lotto> lottos, PayAmount payAmount) {
        validate(lottos, payAmount);
        this.lottos = lottos;
    }

    public int size() {
        return lottos.size();
    }

    private void validate(List<Lotto> lottos, PayAmount payAmount) {
        if (lottos.size() != payAmount.getNumberOfLottos()) {
            throw new LottoException(ErrorMessage.GENERATED_LOTTO_MISMATCH);
        }
    }

    public List<Lotto> getPurchasedLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
