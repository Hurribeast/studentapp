package be.leeroy.studentapp.models.errors;

import android.content.res.Resources;

import be.leeroy.studentapp.App;
import be.leeroy.studentapp.R;

public enum Errors {

    /* Connection errors */
    NO_CONNECTION(R.string.http_no_connection),
    REQUEST_ERROR(R.string.request_error),
    TECHNICAL_ERROR(R.string.technical_error),

    /* Password errors */
    PASSWORD_INCORRECT(R.string.invalid_password);

    private final int errorMessage;

    Errors(int errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getMessage() {
        return App.getRes().getString(errorMessage);
    }
}
