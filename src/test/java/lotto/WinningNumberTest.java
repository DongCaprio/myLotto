package lotto;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import model.LottoNumber;
import model.WinningNumber;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WinningNumberTest {

    @DisplayName("당첨번호가 6개가 아니면 예외가 발생한다.")
    @Test
    void winningInfoTest1() {
        assertAll(
                () -> assertThrows(RuntimeException.class, () -> WinningNumber.from("1,2,3,4,5,6,7")),
                () -> assertThrows(RuntimeException.class, () -> WinningNumber.from("1,2,3,4,5")),
                () -> assertThrows(RuntimeException.class, () -> WinningNumber.from(""))
        );
    }

    @DisplayName("당첨번호가 정상적으로 들어오지 않으면 에러 발생")
    @Test
    void winningInfoTest2() {
        assertAll(
                () -> assertThrows(RuntimeException.class, () -> WinningNumber.from("aa")),
                () -> assertThrows(RuntimeException.class, () -> WinningNumber.from("1,e,2,3,4,5")),
                () -> assertThrows(RuntimeException.class, () -> WinningNumber.from(""))
        );
    }

    @DisplayName("당첨번호가 정상적으로 들어오면 그에 맞는 번호로 객체를 생성한다")
    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5,6", "11,12,13,14,15,16", "45,7,10,11,27,41"})
    void winningInfoTest3(String winningNumber) {

        // when
        List<Integer> list1 = Arrays.stream(winningNumber.split(","))
                .map(Integer::parseInt)
                .toList();

        List<LottoNumber> winningNumberList = WinningNumber.from(winningNumber).getWinningNumber();
        List<Integer> list2 = winningNumberList.stream().map(LottoNumber::getLottoNumber).toList();

        // then
        Assertions.assertThat(list1).isEqualTo(list2);
    }
}
