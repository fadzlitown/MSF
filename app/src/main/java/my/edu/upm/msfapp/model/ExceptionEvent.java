package my.edu.upm.msfapp.model;

import android.support.annotation.Nullable;

public class ExceptionEvent {

    private final Exception mException;

    public ExceptionEvent() {
        mException = null;
    }

    public ExceptionEvent(Exception ex) {
        mException = ex;
    }

    public ExceptionEvent(Throwable throwable) {
        if (throwable instanceof Exception) {
            mException = (Exception) throwable;
        } else {
            mException = null;
        }
    }

    @Nullable
    public Exception getException() {
        return mException;
    }
}
