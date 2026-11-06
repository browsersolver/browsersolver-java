package com.browsersolver.examples;

import com.browsersolver.ApiResponse;
import com.browsersolver.BrowserSolverClient;
import com.browsersolver.params.WebsiteWebunlockerParams;

/** Run with BROWSERSOLVER_API_KEY set. */
public final class JsonExample {
  private JsonExample() {}

  public static void main(String[] args) throws Exception {
    BrowserSolverClient client = BrowserSolverClient.fromEnv();
    ApiResponse response = client.websiteWebunlocker(WebsiteWebunlockerParams.builder().query("https://example.com").build());
    System.out.println(response.json().toPrettyString());
  }
}
