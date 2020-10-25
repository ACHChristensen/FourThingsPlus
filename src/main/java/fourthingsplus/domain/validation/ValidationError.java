package fourthingsplus.domain.validation;

public class ValidationError extends Exception {

    public ValidationError(String field, String error) {
        super("field " + field + ": " + error);
    }
}
