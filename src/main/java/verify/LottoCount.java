package verify;

import custom.CustomException;

public class LottoCount {

    private final int lottoCount;

    public static final int ONE_LOTTO_PRICE = 1000;

    private LottoCount(int lottoPrice) {
        this.lottoCount = lottoPrice;
    }

    public static LottoCount from(String inputPrice) {
        return new LottoCount(calculateLottoCount(inputPrice));
    }

    private static int calculateLottoCount(String inputPrice) {
        try {
            int price = Integer.parseInt(inputPrice);
            verifyPrice(price);
            return price / ONE_LOTTO_PRICE;
        } catch (NumberFormatException e) {
            throw new CustomException("정상적인 가격을 입력해주세요.");
        }
    }

    private static void verifyPrice(int price) {
        if (price < ONE_LOTTO_PRICE || price > 1_000_000_000) {
            throw new CustomException("1000원부터 10억원까지 입력 가능합니다.");
        }
        if (price % ONE_LOTTO_PRICE != 0) {
            throw new CustomException("1000원 단위로 입력 가능합니다.");
        }
    }

    public int getLottoCount() {
        return lottoCount;
    }
}
