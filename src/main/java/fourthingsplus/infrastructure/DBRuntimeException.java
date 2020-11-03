package fourthingsplus.infrastructure;

public class DBRuntimeException extends RuntimeException {

    public DBRuntimeException(Exception e) {
        super(e);
    }

    public DBRuntimeException(String msg) {
        super(msg);
    }
}
