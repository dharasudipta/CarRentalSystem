package com.dharasudipta.crs.domain;

public class CRSException extends Throwable{

    public CRSException(String message) {
        super(message);
    }

    public CRSException(String message, Throwable cause) {
        super(message, cause);
    }

    public CRSException(Throwable cause) {
        super(cause);
    }

}
