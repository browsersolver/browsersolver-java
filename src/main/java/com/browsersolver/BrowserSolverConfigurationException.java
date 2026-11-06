package com.browsersolver;

/** The client configuration is missing or unusable. */
public final class BrowserSolverConfigurationException extends BrowserSolverException {
  private static final long serialVersionUID = 1L;

  /** Creates the exception. */
  public BrowserSolverConfigurationException(String message) {
    super(message, null);
  }

  /** Creates the exception with a cause. */
  public BrowserSolverConfigurationException(String message, Throwable cause) {
    super(message, cause);
  }
}
