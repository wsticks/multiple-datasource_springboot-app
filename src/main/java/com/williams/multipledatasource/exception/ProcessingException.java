package com.williams.multipledatasource.exception;

/**
 * @author Kolawole
 */
public class ProcessingException extends AppException {

  public ProcessingException(String message) {
    super(message);
  }

  public ProcessingException(String message, Throwable cause) {
    super(message, cause);
  }
}
