package com.browsersolver.examples;

import com.browsersolver.ApiResponse;
import com.browsersolver.BrowserSolverClient;
import com.browsersolver.params.WebsiteScreenshotParams;
import java.nio.file.Files;
import java.nio.file.Path;

/** Run with BROWSERSOLVER_API_KEY set. */
public final class BinaryExample {
  private BinaryExample() {}

  public static void main(String[] args) throws Exception {
    BrowserSolverClient client = BrowserSolverClient.fromEnv();
    ApiResponse response = client.websiteScreenshot(WebsiteScreenshotParams.builder().url("https://example.com").format("png").build());
    if (response.isJson()) {
      System.out.println(response.json().toPrettyString());
    } else {
      Files.write(Path.of("screenshot.png"), response.bytes());
      System.out.println("Saved screenshot.png (" + response.contentType() + ")");
    }
  }
}
