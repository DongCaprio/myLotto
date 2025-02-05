package model;

import exception.CustomException;

public class LottoCount {
    
    private static final int MAX_LOTTO_PRICE = 1_000_000_000;

    private final int lottoCount;

    private LottoCount(int lottoCount) {
        this.lottoCount = lottoCount;
    }

    public static LottoCount from(String inputPrice) {
        return new LottoCount(calculateLottoCount(inputPrice));
    }

    private static int calculateLottoCount(String inputPrice) {
        try {
            int price = Integer.parseInt(inputPrice);
            verifyPrice(price);
            return price / LottoPrice.PRICE.getOnePrice();

        } catch (NumberFormatException e) {
            throw new CustomException("정상적인 가격을 입력해주세요.");
        }
    }

    private static void verifyPrice(int price) {
        if (price < LottoPrice.PRICE.getOnePrice() || price > MAX_LOTTO_PRICE) {
            throw new CustomException("1000원부터 10억원까지 입력 가능합니다.");
        }
        if (price % LottoPrice.PRICE.getOnePrice() != 0) {
            throw new CustomException("1000원 단위로 입력 가능합니다.");
        }
    }

    public int getLottoCount() {
        return lottoCount;
    }
}
