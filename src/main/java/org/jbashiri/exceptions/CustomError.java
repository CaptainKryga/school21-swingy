package org.jbashiri.exceptions;

import org.jbashiri.utils.CustomEnums;

public class CustomError {
    public String error;
    public CustomEnums.Error type;

    public CustomError(String error, CustomEnums.Error type) {
        this.error = error;
        this.type = type;
    }

    public CustomError(CustomEnums.Error type) {
        this.type = type;
    }
}
