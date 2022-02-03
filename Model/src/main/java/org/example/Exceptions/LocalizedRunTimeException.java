package org.example.Exceptions;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizedRunTimeException extends RuntimeException {
    public LocalizedRunTimeException(String messageKey) {
        super(messageKey);
    }

    @Override
    public String getMessage() {
        return this.getLocalizedMessage();
    }

    @Override
    public String getLocalizedMessage() {
        ResourceBundle bundle = ResourceBundle.getBundle("ExceptionsMessages",
                Locale.getDefault());
        return bundle.getString(super.getMessage());
    }
}
