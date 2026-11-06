package com.browsersolver.examples;

import com.browsersolver.ApiResponse;
import com.browsersolver.BrowserSolverApiException;
import com.browsersolver.BrowserSolverClient;
import java.util.Map;

/** Run with BROWSERSOLVER_API_KEY set. */
public final class RequestExample {
  private RequestExample() {}

  public static void main(String[] args) throws Exception {
    BrowserSolverClient client = BrowserSolverClient.fromEnv();
    try {
      ApiResponse response = client.request("GET", "/v1/usage", Map.of());
      System.out.println(response.text());
    } catch (BrowserSolverApiException e) {
      System.err.println("HTTP " + e.status() + ": " + e.getMessage());
    }
  }
}
