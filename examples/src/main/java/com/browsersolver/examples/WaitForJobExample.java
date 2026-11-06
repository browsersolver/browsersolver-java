package com.browsersolver.examples;

import com.browsersolver.ApiResponse;
import com.browsersolver.BrowserSolverClient;
import com.browsersolver.params.WebsiteWebunlockerParams;
import java.time.Duration;

/** Run with BROWSERSOLVER_API_KEY set. */
public final class WaitForJobExample {
  private WaitForJobExample() {}

  public static void main(String[] args) throws Exception {
    BrowserSolverClient client = BrowserSolverClient.fromEnv();
    ApiResponse submitted = client.websiteWebunlocker(WebsiteWebunlockerParams.builder().query("https://example.com").isAsync(true).build());
    ApiResponse job = client.waitForJob(
        submitted.json().get("job_id").asText(),
        Duration.ofSeconds(3),
        Duration.ofHours(12),
        progress -> System.out.println(progress.path("status").asText() + " " + progress.path("progress").asInt()));
    System.out.println(job.json().toPrettyString());
  }
}
