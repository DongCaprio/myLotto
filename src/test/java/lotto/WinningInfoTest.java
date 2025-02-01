package lotto;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import model.BonusNumber;
import model.Lotto;
import model.LottoRank;
import model.WinningInformation;
import model.WinningNumber;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningInfoTest {

    @Test
    @DisplayName("당첨번호와 보너스 번호에 중복된 숫자가 있으면 예외를 발생시킨다")
    void winningInfoTest1() {
        assertAll(
                () -> assertThrows(RuntimeException.class,
                        () -> WinningInformation.from(WinningNumber.from("1,2,3,4,5,6"), BonusNumber.from("1"))),
                () -> assertThrows(RuntimeException.class,
                        () -> WinningInformation.from(WinningNumber.from("1,2,3,4,5,6"), BonusNumber.from("6"))),
                () -> assertThrows(RuntimeException.class,
                        () -> WinningInformation.from(WinningNumber.from("11,22,33,44,15,16"), BonusNumber.from("33")))
        );

    }


    @Test
    @DisplayName("로또 당첨에 맞는 LottoRank를 반환한다")
    void winningInfoTest2() {
        // given
        WinningInformation winningInfo = WinningInformation.from(WinningNumber.from("1,2,3,4,5,6"),
                BonusNumber.from("7"));

        // when & then
        assertAll(() -> Assertions.assertThat(winningInfo.specifyLottoRank(new Lotto(List.of(1, 2, 3, 4, 5, 6))))
                        .isEqualTo(LottoRank.FIRST),
                () -> Assertions.assertThat(winningInfo.specifyLottoRank(new Lotto(List.of(1, 2, 3, 4, 5, 7))))
                        .isEqualTo(LottoRank.SECOND),
                () -> Assertions.assertThat(winningInfo.specifyLottoRank(new Lotto(List.of(1, 2, 3, 4, 5, 8))))
                        .isEqualTo(LottoRank.THIRD),
                () -> Assertions.assertThat(winningInfo.specifyLottoRank(new Lotto(List.of(11, 12, 13, 14, 15, 16))))
                        .isEqualTo(null));

    }
}
