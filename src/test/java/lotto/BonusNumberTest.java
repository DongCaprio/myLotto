package lotto;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BonusNumberTest {

    @DisplayName("보너스 번호가 정상적이면 그에 맞는 BonusNumber 객체를 생성한다")
    @ParameterizedTest
    @ValueSource(strings = {"1", "16", "45"})
    void bonusNumberTest1(String inputCount) {
        // when
        BonusNumber bonusNumber = BonusNumber.from(inputCount);

        // then
        Assertions.assertThat(bonusNumber.getBonusNumber()).isEqualTo(Integer.parseInt(inputCount));
    }

    @DisplayName("보너스 번호가 정상적이지 않을 경우 에러를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"-1", "0", "abc", " ", "", "3333333333333333", "46"})
    void bonusNumberTest2(String inputCount) {
        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            BonusNumber.from(inputCount);
        });
    }
}
