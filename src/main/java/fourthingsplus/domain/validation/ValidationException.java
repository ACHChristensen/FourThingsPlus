package fourthingsplus.domain.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationException extends Exception {

    private final List<Problem> errors = new ArrayList<Problem>();

    public ValidationException() {
    }

    public void addProblem(String field, String message) {
        errors.add(new Problem(field, message));
    }

    public void validate() throws ValidationException {
        if (!errors.isEmpty()) throw this;
    }

    public List<Problem> getProblems() {
        return errors;
    }

    @Override
    public String getMessage() {
        return errors.toString();
    }

    public class Problem {

        private final String message;
        private final String field;

        public Problem(String field, String message) {
            this.field = field;
            this.message = message;
        }

        @Override
        public String toString() {
            return "Problem{" +
                    "message='" + message + '\'' +
                    ", field='" + field + '\'' +
                    '}';
        }
    }
}
