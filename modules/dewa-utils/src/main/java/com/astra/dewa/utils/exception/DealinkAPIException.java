package com.astra.dewa.utils.exception;

/**
 * @author psmahmad1402
 */
public class DealinkAPIException extends Exception {
    private static final long serialVersionUID = 1L;
    public DealinkAPIException() { super(); }
    public DealinkAPIException(String message) {
        super(message);
    }
    public DealinkAPIException(String message, Throwable cause) { super(message, cause); }
    public DealinkAPIException(Throwable cause) { super(cause); }
}
