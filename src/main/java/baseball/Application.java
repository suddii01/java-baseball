package baseball;

import java.util.*;
import java.util.stream.Collectors;

import static baseball.BaseballExceptionType.*;
import static baseball.BaseballMessage.*;
import static baseball.BaseballScore.*;
import static baseball.BaseballConstant.*;
import static camp.nextstep.edu.missionutils.Console.*;
import static camp.nextstep.edu.missionutils.Randoms.*;



public class Application {
    public static void main(String[] args) {
        runBaseballGame();
    }

    public static void validateNumbers(List<Integer> numbers) {
        Set<Integer> set = new HashSet<>(numbers);
        if (set.size() != BASEBALL_COUNT) throw new IllegalArgumentException(DUPLICATE_NUMBER.getMessage());
        if (numbers.size() != BASEBALL_COUNT) throw new IllegalArgumentException(INVALID_NUMBER_COUNT.getMessage());
    }

    public static void runBaseballGame() {
        while (true) {
            System.out.println(START_GAME.getMessage());
            List<Integer> computer = generateUniqueRandomNumbers();
            boolean shouldContinue = playBaseballGame(computer);
            if (!shouldContinue) break;
        }
    }

    public static boolean playBaseballGame(List<Integer> computer) {
        while (true) {
            System.out.print(ENTER_NUMBER.getMessage());
            List<Integer> player = readInputNumbers();
            validateNumbers(player);

            PlayResult playResult = calculatePlayResult(computer, player);
            boolean is3Strike = playResult.printPlayResult();
            if (!is3Strike) continue;

            System.out.println(GAME_WIN.getMessage());
            System.out.println(PLAY_AGAIN.getMessage());

            String order = readLine();
            if (order.equals(PLAY_AGAIN_INPUT)) return true;
            if (order.equals(END_GAME_INPUT)) return false;
            throw new IllegalArgumentException(INVALID_INPUT_VALUE.getMessage());
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
        while (randomNumbers.size() < BASEBALL_COUNT) {
            int randomNumber = pickNumberInRange(RANGE_START, RANGE_END);
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
