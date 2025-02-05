package model;

public enum LottoPrice {

    PRICE(1000);

    private final int onePrice;

    LottoPrice(int onePrice) {
        this.onePrice = onePrice;
    }

    public int getOnePrice() {
        return onePrice;
    }
}
