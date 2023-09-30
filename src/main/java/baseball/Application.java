package baseball;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static baseball.BaseballScore.*;
import static camp.nextstep.edu.missionutils.Console.*;
import static camp.nextstep.edu.missionutils.Randoms.*;

public class Application {
    public static void main(String[] args) {
        runBaseballGame();
    }

    public static void runBaseballGame() {
        while (true) {
            System.out.println("숫자 야구 게임을 시작합니다.");
            List<Integer> computer = generateUniqueRandomNumbers();
            playBaseballGame(computer);
        }
    }

    public static void playBaseballGame(List<Integer> computer) {
        while (true) {
            System.out.print("숫자를 입력주세요 : ");
            List<Integer> player = readInputNumbers();

            PlayResult playResult = calculatePlayResult(computer, player);
            playResult.printPlayResult();
        }
    }

    public static PlayResult calculatePlayResult(List<Integer> computer, List<Integer> player) {
        int ball = 0;
        int strike = 0;
        for (int i = 0; i < computer.size(); i++) {
            BaseballScore baseballScore = checkBaseball(i, computer.get(i), player);
            if (baseballScore == BALL) ball++;
            if (baseballScore == STRIKE) strike++;
        }
        return new PlayResult(ball, strike);
    }

    public static BaseballScore checkBaseball(int computerIndex, int computerNum, List<Integer> player) {
        for (int i = 0; i < player.size(); i++) {
            if (player.get(i) != computerNum) continue;
            if (i != computerIndex) return BALL;
            if (i == computerIndex) return STRIKE;
        }
        return NOTHING;
    }

    public static List<Integer> generateUniqueRandomNumbers() {
        List<Integer> randomNumbers = new ArrayList<>();
        while (randomNumbers.size() < 3) {
            int randomNumber = pickNumberInRange(1, 9);
            if (!randomNumbers.contains(randomNumber)) {
                randomNumbers.add(randomNumber);
            }
        }
        return randomNumbers;
    }

    public static List<Integer> readInputNumbers() {
        String s = readLine();
        String[] strings = s.split("");
        return Arrays.stream(strings)
                .map(string -> Integer.parseInt(string))
                .collect(Collectors.toList());
    }
}
