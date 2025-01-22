package verify;

import custom.CustomException;

public class LottoPrice {

    private final int lottoPrice;

    public LottoPrice(int lottoPrice) {
        this.lottoPrice = lottoPrice;
    }

    public static LottoPrice from(String inputPrice) {
        return new LottoPrice(changePriceType(inputPrice));
    }

    private static int changePriceType(String inputPrice) {
        try {
            int price = Integer.parseInt(inputPrice);
            verifyPrice(price);
            return price;
        } catch (NumberFormatException e) {
            throw new CustomException("정상적인 가격을 입력해주세요.");
        }
    }

    private static void verifyPrice(int price) {
        if (price < 1000 || price > 1_000_000_000) {
            throw new CustomException("1000원부터 10억원까지 입력 가능합니다.");
        }
        if (price % 1000 != 0) {
            throw new CustomException("1000원 단위로 입력 가능합니다.");
        }
    }

    public int getLottoPrice() {
        return lottoPrice;
    }
}
