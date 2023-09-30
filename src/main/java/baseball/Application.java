package baseball;

import java.util.*;
import java.util.stream.Collectors;

import static baseball.BaseballScore.*;
import static camp.nextstep.edu.missionutils.Console.*;
import static camp.nextstep.edu.missionutils.Randoms.*;

public class Application {
    public static void main(String[] args) {
        runBaseballGame();
    }

    public static void validateNumbers(List<Integer> numbers) throws IllegalArgumentException {
        Set<Integer> set = new HashSet<>(numbers);
        if (set.size() != 3) throw new IllegalArgumentException("중복된 숫자는 존재할 수 없습니다.");
        if (numbers.size() != 3) throw new IllegalArgumentException("숫자는 3개 입력되어야 합니다.");
    }

    public static void runBaseballGame() {
        while (true) {
            System.out.println("숫자 야구 게임을 시작합니다.");
            List<Integer> computer = generateUniqueRandomNumbers();
            boolean shouldContinue = playBaseballGame(computer);
            if (!shouldContinue) break;
        }
    }

    public static boolean playBaseballGame(List<Integer> computer) {
        while (true) {
            System.out.print("숫자를 입력주세요 : ");
            List<Integer> player = readInputNumbers();
            validateNumbers(player);

            PlayResult playResult = calculatePlayResult(computer, player);
            boolean is3Strike = playResult.printPlayResult();
            if (!is3Strike) continue;

            System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");

            String order = readLine();
            if (order.equals("1")) return true;
            if (order.equals("2")) return false;
            throw new IllegalArgumentException("1 또는 2를 입력해 주세요.");
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
