package verify;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import model.LottoCount;
import model.LottoPrice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoCountTest {

    @DisplayName("구입 금액이 잘못입력된 경우 에러가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"1001", "", "100000000000", "999", "e"})
    void lottoCountTest1(String errorNumber) {
        assertThrows(RuntimeException.class, () -> {
            // then
            LottoCount.from(errorNumber);
        });
    }

    @DisplayName("구입 금액이 젇상적으로 입력된 경우 해당 금액에 맞는 객체를 만든다")
    @ParameterizedTest
    @ValueSource(strings = {"1000", "10000", "380000"})
    void lottoCountTest2(String price) {
        assertEquals(Integer.parseInt(price) / LottoPrice.PRICE.getOnePrice(),
                LottoCount.from(price).getLottoCount());
    }
}
