package DTO;

public class LogInResult {
    private final boolean success;
    private final String message;

    public LogInResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
