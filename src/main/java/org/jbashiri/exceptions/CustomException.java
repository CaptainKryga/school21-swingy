package org.jbashiri.exceptions;

import java.io.IOException;

public class CustomException extends IOException {
    public CustomException(String error) {
        super("ERROR > " + error);
    }
}
