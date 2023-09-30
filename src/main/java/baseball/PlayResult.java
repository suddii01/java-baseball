package baseball;

public class PlayResult {
    private int ball;
    private int strike;

    public PlayResult(int ball, int strike) {
        this.ball = ball;
        this.strike = strike;
    }

    public void printPlayResult() {
        if (ball != 0) System.out.print(ball + "볼 ");
        if (strike != 0) System.out.print(strike + "스트라이크");
        if (ball == 0 && strike == 0) System.out.print("낫싱");
        System.out.println();
    }
}
