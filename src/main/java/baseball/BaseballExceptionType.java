package baseball;

public enum BaseballExceptionType {
    DUPLICATE_NUMBER("중복된 숫자는 존재할 수 없습니다."),
    INVALID_NUMBER_COUNT("숫자는 3개 입력되어야 합니다."),
    INVALID_INPUT_VALUE("1 또는 2를 입력해 주세요.");

    private final String message;

    BaseballExceptionType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
