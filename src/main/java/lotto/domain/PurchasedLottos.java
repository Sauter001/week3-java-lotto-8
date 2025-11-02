package lotto.domain;

import lotto.constants.ErrorMessage;
import lotto.dto.LottoDto;
import lotto.dto.PurchasedLottosDto;
import lotto.exception.LottoException;

import java.util.Collections;
import java.util.List;

public class PurchasedLottos {
    private final List<Lotto> lottos;

    public PurchasedLottos(List<Lotto> lottos, PayAmount payAmount) {
        validate(lottos, payAmount);
        this.lottos = lottos;
    }

    public int size() {
        return lottos.size();
    }

    private void validate(List<Lotto> lottos, PayAmount payAmount) {
        if (lottos.size() != payAmount.countLotto()) {
            throw new LottoException(ErrorMessage.GENERATED_LOTTO_MISMATCH);
        }
    }

    public List<Lotto> getPurchasedLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public PurchasedLottosDto toDto() {
        List<LottoDto> dtos = this.lottos.stream()
                .map(lotto -> new LottoDto(lotto.getNumbers()))
                .toList();
        return new PurchasedLottosDto(dtos);
    }
}
