package model;

import exception.CustomException;

public class LottoNumber {

    private final int lottoNumber;

    private LottoNumber(int lottoNumber) {
        this.lottoNumber = lottoNumber;
    }

    public static LottoNumber from(int lottoNumber) {
        verifyNumber(lottoNumber);
        return new LottoNumber(lottoNumber);
    }

    public static LottoNumber from(String lottoNumber) {
        try {
            return from(Integer.parseInt(lottoNumber));
        } catch (NumberFormatException e) {
            throw new CustomException(e.getMessage());
        }
    }

    private static void verifyNumber(int number) {
        if (number < 1 || number > 45) {
            throw new CustomException("1 ~ 45 사이값을 입력해주세요");
        }
    }

    public boolean isSameNumber(int number) {
        return number == lottoNumber;
    }

    public int getLottoNumber() {
        return lottoNumber;
    }
}
