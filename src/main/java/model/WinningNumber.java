package model;

import exception.CustomException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningNumber {

    private final List<LottoNumber> winningNumber;

    private WinningNumber(List<LottoNumber> winningNumber) {
        this.winningNumber = winningNumber;
    }

    public static WinningNumber from(String inputNumbers) {
        List<LottoNumber> numbers = changeInputToNumber(inputNumbers);
        Lotto.validate(numbers);
        return new WinningNumber(numbers);
    }

    private static List<LottoNumber> changeInputToNumber(String numbers) {
        try {
            return Arrays.stream(numbers.split(","))
                    .map(stringNumber -> LottoNumber.from(Integer.parseInt(stringNumber.trim())))
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new CustomException(e.getMessage());
        }
    }

    public List<LottoNumber> getWinningNumber() {
        return List.copyOf(winningNumber);
    }
}
