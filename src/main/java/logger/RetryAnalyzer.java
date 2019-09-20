package logger;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.util.regex.Pattern;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private int retryMaxCount = 3;

    @Override
    public boolean retry(ITestResult testResult) {
        if (hasServerError(testResult) && (retryCount < retryMaxCount)) {
            retryCount++;
            return true;
        }
        return false;
    }

    private boolean hasServerError(ITestResult testResult) {
        Throwable throwable = testResult.getThrowable();
        Pattern pattern = Pattern.compile("^.*(4|5)\\d\\d.*$", Pattern.DOTALL);
        while (throwable != null) {
            if (pattern.matcher(throwable.getMessage()).find()) {
                return true;
            }
            throwable = throwable.getCause();
        }
        return false;
    }
}
