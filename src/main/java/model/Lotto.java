package model;

import camp.nextstep.edu.missionutils.Randoms;
import exception.CustomException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {

    public static final int LOTTO_START_NUM = 1;
    public static final int LOTTO_END_NUM = 45;
    public static final int LOTTO_NUM_SIZE = 6;

    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) { //수동로또를 위해 public 으로 선언
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::from)
                .sorted(Comparator.comparingInt(LottoNumber::getLottoNumber))
                .toList();
        validate(lottoNumbers);
        this.numbers = lottoNumbers;
    }

    public static void validate(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_NUM_SIZE) {
            throw new CustomException("로또 번호는 6개여야 합니다.");
        }
        long uniqueCount = numbers.stream()
                .map(LottoNumber::getLottoNumber) // int 값만 추출
                .distinct()
                .count();
        if (uniqueCount != numbers.size()) {
            throw new CustomException("로또번호의 숫자는 중복될 수 없습니다.");
        }
    }

    public static Lotto makeRandomLotto(int startNumber, int endNumber, int numberSize) {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(startNumber, endNumber, numberSize);
        return new Lotto(numbers);
    }

    public List<LottoNumber> getNumbers() {
        return List.copyOf(numbers);
    }

    public String makeStringLottoNumber() {
        return numbers.stream()
                .map(lotto -> String.valueOf(lotto.getLottoNumber()))
                .collect(Collectors.joining(", ", "[", "]"));
    }
}
