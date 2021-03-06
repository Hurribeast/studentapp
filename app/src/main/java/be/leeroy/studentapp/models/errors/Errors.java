package be.leeroy.studentapp.models.errors;

import be.leeroy.studentapp.App;
import be.leeroy.studentapp.R;

public enum Errors {

    /* Connection errors */
    NO_CONNECTION(R.string.http_no_connection),
    REQUEST_ERROR(R.string.request_error),
    TECHNICAL_ERROR(R.string.technical_error),
    EMAIL_EXIST(R.string.email_exist),

    /* Password errors */
    PASSWORD_INCORRECT(R.string.invalid_password),

    /* Token error */
    TOKEN_EXPIRED(null);

    private final Integer errorMessage;

    Errors(Integer errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getMessage() {
        return App.getRes().getString(errorMessage);
    }
}
