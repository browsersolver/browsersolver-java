package com.browsersolver;

/** Base class for every SDK-defined exception. */
public class BrowserSolverException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  /** Creates the exception. */
  public BrowserSolverException(String message, Throwable cause) {
    super(message, cause);
  }
}
