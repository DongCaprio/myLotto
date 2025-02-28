package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.IntSummaryStatistics;
import java.util.List;
import model.Lotto;
import model.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoTest {

    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(RuntimeException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(RuntimeException.class);
    }

    @DisplayName("로또 번호가 1~45 사이가 아니면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46, 100})
    void lottoTest1(int errorNumber) {
        assertThrows(RuntimeException.class, () -> {
            // then
            new Lotto(List.of(1, 2, 3, 4, 5, errorNumber));
        });
    }

    @DisplayName("랜덤로또가 정상적으로 생성되는지 테스트")
    @Test
    void lottoTest2() {
        for (int i = 0; i < 100; i++) {
            // when
            Lotto lotto = Lotto.makeRandomLotto(Lotto.LOTTO_START_NUM, Lotto.LOTTO_END_NUM, Lotto.LOTTO_NUM_SIZE);
            IntSummaryStatistics stats = lotto.getNumbers().stream()
                    .mapToInt(LottoNumber::getLottoNumber)
                    .summaryStatistics();
            // then
            assertAll(
                    () -> assertThat(lotto.getNumbers().size()).isEqualTo(6),
                    () -> assertThat(stats.getMin()).isGreaterThan(0),
                    () -> assertThat(stats.getMax()).isLessThanOrEqualTo(45));
        }
    }
}
