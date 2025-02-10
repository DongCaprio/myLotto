package lotto;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import model.LottoNumber;
import model.WinningInformation;
import model.WinningNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningInfoTest {

    @Test
    @DisplayName("당첨번호와 보너스 번호에 중복된 숫자가 있으면 예외를 발생시킨다")
    void winningInfoTest1() {
        assertAll(
                () -> assertThrows(RuntimeException.class,
                        () -> WinningInformation.from(WinningNumber.from("1,2,3,4,5,6"), LottoNumber.from("1"))),
                () -> assertThrows(RuntimeException.class,
                        () -> WinningInformation.from(WinningNumber.from("1,2,3,4,5,6"), LottoNumber.from("6"))),
                () -> assertThrows(RuntimeException.class,
                        () -> WinningInformation.from(WinningNumber.from("11,22,33,44,15,16"), LottoNumber.from("33")))
        );

    }
}
