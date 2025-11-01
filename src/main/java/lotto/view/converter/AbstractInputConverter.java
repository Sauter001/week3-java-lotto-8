package lotto.view.converter;

import camp.nextstep.edu.missionutils.Console;

import java.util.NoSuchElementException;

public abstract class AbstractInputConverter<T> implements InputConverter<T> {
    private static final String INPUT_UNACCEPTABLE = "입력을 더 이상 받을 수 없습니다.";

    private final String prompt;

    protected AbstractInputConverter(String prompt) {
        this.prompt = prompt;
    }

    @Override
    public final T convert() {
        while (true) {
            try {
                System.out.println(this.prompt);
                String input = Console.readLine();
                return this.parse(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (NoSuchElementException e) {
                throw new IllegalStateException(INPUT_UNACCEPTABLE, e);
            }
        }
    }

    protected abstract T parse(String input);
}
