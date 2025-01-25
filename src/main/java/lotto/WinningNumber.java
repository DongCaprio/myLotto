package lotto;

import custom.CustomException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningNumber {

    private List<Integer> winningNumber;

    private WinningNumber(List<Integer> winningNumber) {
        this.winningNumber = winningNumber;
    }

    private static List<Integer> changeInputToNumber(String numbers) {
        try {
            return Arrays.stream(Arrays.stream(numbers.split(","))
                            .map(String::trim)
                            .toArray(String[]::new))
                    .map(Integer::parseInt)
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (NumberFormatException e) {
            throw new CustomException(e.getMessage());
        }
    }

    public static WinningNumber from(String inputNumbers) {
        List<Integer> numbers = changeInputToNumber(inputNumbers);
        Lotto.validate(numbers);
        return new WinningNumber(numbers);
    }

    public List<Integer> getWinningNumber() {
        return List.copyOf(winningNumber);
    }
}
