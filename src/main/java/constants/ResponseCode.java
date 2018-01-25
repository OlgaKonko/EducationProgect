package constants;

public enum ResponseCode {
    SUCCESS_CODE(200),
    NOT_FIND_CODE(404);

    private final int code;

    ResponseCode(final int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
