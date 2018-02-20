package my.edu.upm.msfapp.model;

import my.edu.upm.msfapp.util.Ln;

public class MyException extends Exception {

    private MyError mError;

    public MyException(String detailMessage) {
        super(detailMessage);
    }

    public MyException(MyError error) {
        super(error.getErrorMessage());
        mError = error;
    }

    public static MyException parse(String json) {
        MyError error = MyError.fromJson(json);
        MyException exception = new MyException(error);
        Ln.e(exception.getErrorMessage());
        return exception;
    }

    public MyError getError() {
        return mError;
    }

    String getErrorMessage() {
        if (mError != null) {
            return mError.getErrorMessage();
        }

        return getMessage();
    }
}
