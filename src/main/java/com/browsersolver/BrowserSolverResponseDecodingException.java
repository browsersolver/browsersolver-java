package com.browsersolver;

/** A successful response could not be decoded as the documented representation. */
public final class BrowserSolverResponseDecodingException extends BrowserSolverException {
  private static final long serialVersionUID = 1L;
  private final transient RawResponse rawResponse;

  /** Creates the exception. */
  public BrowserSolverResponseDecodingException(String message, RawResponse rawResponse, Throwable cause) {
    super(message, cause);
    this.rawResponse = rawResponse;
  }

  /** The buffered response. */
  public RawResponse rawResponse() {
    return rawResponse;
  }
}
