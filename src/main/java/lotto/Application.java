package lotto;

import lotto.controller.LottoController;
import lotto.service.LottoService;
import lotto.view.ConsoleView;
import lotto.view.View;

public class Application {
    public static void main(String[] args) {
        View view = new ConsoleView();
        try {
            LottoService lottoService = new LottoService();
            LottoController controller = new LottoController(view, lottoService);
            controller.run();
        } catch (IllegalArgumentException | IllegalStateException e) {
            view.printError(e);
        }
    }
}
