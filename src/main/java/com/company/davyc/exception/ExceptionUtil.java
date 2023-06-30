package com.company.davyc.exception;

import com.company.davyc.api.APIErrors;

public class ExceptionUtil {
    public static APIErrors lidarException(Exception exception) {
        String message = exception.getMessage();
        return new APIErrors(message);
    }

}
