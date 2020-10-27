package fourthingsplus.domain.validation;

public class ValidationException extends Exception {

    public ValidationException(String name, String msg) {
        super(name + ": " + msg);
    }
}
