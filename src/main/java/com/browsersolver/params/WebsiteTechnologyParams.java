package com.browsersolver.params;

import com.browsersolver.BrowserSolverParams;
import com.browsersolver.BrowserSolverSerializationException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Parameters for {@code GET / POST /v1/website/technology}.
 *
 * <p>Generated from the BrowserSolver OpenAPI document. Do not edit by hand.
 */
public final class WebsiteTechnologyParams implements BrowserSolverParams {
  private final Map<String, Object> values;

  private WebsiteTechnologyParams(Map<String, Object> values) {
    this.values = values;
  }

  /** Returns a new builder. */
  public static Builder builder() {
    return new Builder();
  }

  @Override
  public Map<String, Object> toMap() {
    return values;
  }

  /** Builder for {@link WebsiteTechnologyParams}. */
  public static final class Builder {
    private final Map<String, Object> values = new LinkedHashMap<>();

    private Builder() {}

    /** Required. Public URL to fingerprint for CMS, frameworks, analytics, CDN, and security tooling. Include http:// or https://. */
    public Builder query(String value) {
      values.put("query", value);
      return this;
    }

    /** How much detail to include in the stack report. Default: "expert". */
    public Builder mode(String value) {
      values.put("mode", value);
      return this;
    }

    /** Comma-separated list of fields to keep in the response, with dot notation for nested fields (e.g. name,location.city). Does not change the credit cost. */
    public Builder fields(String value) {
      values.put("fields", value);
      return this;
    }

    /** Process the request asynchronously: the response is a job_id to poll on /v1/jobs/{id} (see waitForJob). Default: false. */
    public Builder isAsync(Boolean value) {
      values.put("is_async", value);
      return this;
    }

    /** Return HTTP 200 even when an error occurs; the error is then in the response body. Default: false. */
    public Builder softFail(Boolean value) {
      values.put("soft_fail", value);
      return this;
    }

    /** Validates required parameters and returns the immutable {@link WebsiteTechnologyParams}. */
    public WebsiteTechnologyParams build() {
      if (values.get("query") == null) {
        throw new BrowserSolverSerializationException("The \"query\" parameter is required.", "query");
      }
      return new WebsiteTechnologyParams(java.util.Collections.unmodifiableMap(new LinkedHashMap<>(values)));
    }
  }
}
