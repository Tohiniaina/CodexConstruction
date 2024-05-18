package codex.evaluation.model;

import org.springframework.stereotype.Component;

@Component
public class Error {
    private int lineNumber;
    private String error;

    public Error(int n, String error) {
        this.lineNumber = n;
        this.error = error;
    }

    public Error() {
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
