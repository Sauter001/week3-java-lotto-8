package lotto;

import lotto.controller.LottoController;
import lotto.view.ConsoleView;
import lotto.view.View;

public class Application {
    public static void main(String[] args) {
        View view = new ConsoleView();
        try {
            LottoController controller = new LottoController(view);
            controller.run();
        } catch (IllegalArgumentException | IllegalStateException e) {
            view.printError(e);
        }
    }
}
