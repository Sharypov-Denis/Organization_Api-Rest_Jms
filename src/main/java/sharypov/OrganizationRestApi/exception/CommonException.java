package sharypov.OrganizationRestApi.exception;

public class CommonException extends RuntimeException {

    public CommonException() {
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonException(String message) {
        super(message);
    }
}
