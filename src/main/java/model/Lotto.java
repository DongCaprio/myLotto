package model;

import camp.nextstep.edu.missionutils.Randoms;
import exception.CustomException;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {

    public static final int LOTTO_START_NUM = 1;
    public static final int LOTTO_END_NUM = 45;
    public static final int LOTTO_NUM_SIZE = 6;

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

    public static Lotto makeRandomLotto(int startNumber, int endNumber, int numberSize) {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(startNumber, endNumber, numberSize);
        return new Lotto(numbers);
    }

    public List<Integer> getNumbers() {
        return List.copyOf(numbers);
    }

    public String makeStringLottoNumber() {
        return numbers.toString();
    }
}
