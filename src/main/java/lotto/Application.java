package lotto;

import lotto.controller.LottoController;
import lotto.view.ConsoleView;
import lotto.view.View;

public class Application {
    public static void main(String[] args) {
        View view = new ConsoleView();
        LottoController controller = new LottoController(view);
        controller.run();
    }
}
