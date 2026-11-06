package com.browsersolver;

/** The request failed before a complete HTTP response was received (network error, timeout). */
public final class BrowserSolverTransportException extends BrowserSolverException {
  private static final long serialVersionUID = 1L;

  /** Creates the exception. */
  public BrowserSolverTransportException(String message, Throwable cause) {
    super(message, cause);
  }
}
