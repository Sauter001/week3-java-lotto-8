package lotto;

import java.util.List;

/*
 * 제공된 Lotto 클래스를 사용해야 합니다.
 * Lotto에 numbers 이외의 필드(인스턴스 변수)를 추가할 수 없습니다.
 * numbers의 접근 제어자인 private은 변경할 수 없습니다.
 * Lotto의 패키지를 변경할 수 있습니다.
 * Lotto 생성 시 6개가 아닌 번호가 들어오면 IllegalArgumentException을 발생시켜야 합니다.
 */
public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }
}
