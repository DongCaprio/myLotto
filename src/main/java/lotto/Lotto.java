package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import custom.CustomException;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {

    private static final int LOTTO_START_NUM = 1;
    private static final int LOTTO_END_NUM = 45;
    private static final int LOTTO_NUM_SIZE = 6;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) { //수동로또를 위해 public 으로 선언
        validate(numbers);
        this.numbers = numbers.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public static void validate(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUM_SIZE) {
            throw new CustomException("로또 번호는 6개여야 합니다.");
        }
        if (!numbers.stream()
                .allMatch(num -> num >= LOTTO_START_NUM && num <= LOTTO_END_NUM)) {
            throw new CustomException("로또번호는 1 ~ 45만 입력 가능합니다.");
        }
        if (numbers.stream().distinct().count() != numbers.size()) {
            throw new CustomException("로또번호의 숫자는 중복될 수 없습니다.");
        }
    }

    public static Lotto makeRandomLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(LOTTO_START_NUM, LOTTO_END_NUM, LOTTO_NUM_SIZE);
        return new Lotto(numbers);
    }

    public List<Integer> getNumbers() {
        return List.copyOf(numbers);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
