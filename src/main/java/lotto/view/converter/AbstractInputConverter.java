package lotto.view.converter;

import camp.nextstep.edu.missionutils.Console;

import java.util.NoSuchElementException;

public abstract class AbstractInputConverter<T> implements InputConverter<T> {
    private final String prompt;

    protected AbstractInputConverter(String prompt) {
        this.prompt = prompt;
    }

    @Override
    public final T convert() {
        while (true) {
            try {
                System.out.println(this.prompt);
                String input = readInputSafely();
                return this.parse(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    protected abstract T parse(String input);

    private String readInputSafely() {
        try {
            return Console.readLine();
        } catch (NoSuchElementException e) {
            return "";
        }
    }
}
