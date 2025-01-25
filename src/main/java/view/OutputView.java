package view;

public class OutputView {

    public void println(String message) {
        System.out.println(message);
    }

    public void printf(String message) {
        System.out.printf(message);
    }

    public void printLottoCount(int count) {
        println("\n" + count + "개를 구매했습니다.");
    }
}
