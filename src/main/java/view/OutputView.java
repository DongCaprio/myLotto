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

    public void printWinningPaper(String winningPaper, String rateOfReturn) {
        System.out.printf("%n당첨 통계%n---%n");
        System.out.printf(winningPaper);
        System.out.printf("총 수익률은 %s%%입니다.%n", rateOfReturn);
    }
}
