package lotto.view.converter;

import camp.nextstep.edu.missionutils.Console;
import lotto.constants.PromptMessage;

import java.util.NoSuchElementException;

public abstract class AbstractInputConverter<T> implements InputConverter<T> {
    private final String prompt;

    protected AbstractInputConverter(PromptMessage promptMessage) {
        this.prompt = promptMessage.getMessage();
    }

    @Override
    public final T convert() {
        while (true) {
            try {
                System.out.println(this.prompt);
                String input = readInputSafely();
                return this.parse(input);
            } catch (IllegalArgumentException e) {
                System.out.println();
                System.out.println(e.getMessage());
            }
        }
    }

    public abstract T parse(String input);

    private String readInputSafely() {
        try {
            return Console.readLine();
        } catch (NoSuchElementException e) {
            return "";
        }
    }
}
