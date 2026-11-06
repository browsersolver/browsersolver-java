package com.browsersolver.params;

import com.browsersolver.BrowserSolverParams;
import com.browsersolver.BrowserSolverSerializationException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Parameters for {@code GET /v1/website/rendering}.
 *
 * <p>Generated from the BrowserSolver OpenAPI document. Do not edit by hand.
 */
public final class WebsiteRenderingParams implements BrowserSolverParams {
  private final Map<String, Object> values;

  private WebsiteRenderingParams(Map<String, Object> values) {
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

  /** Builder for {@link WebsiteRenderingParams}. */
  public static final class Builder {
    private final Map<String, Object> values = new LinkedHashMap<>();

    private Builder() {}

    /** Required. Public URL to load inside BrowserSolver's headless browser. Include http:// or https://. */
    public Builder query(String value) {
      values.put("query", value);
      return this;
    }

    /** Extra pause, in seconds, after the page load event before BrowserSolver returns HTML. Helpful when widgets animate or hydrate late. Default: 0. */
    public Builder waitInSeconds(Number value) {
      values.put("wait_in_seconds", value);
      return this;
    }

    /** CSS or XPath selector that must appear in the DOM before HTML is returned. The managed browser waits until the node exists (or until timeout). */
    public Builder waitFor(String value) {
      values.put("wait_for", value);
      return this;
    }

    /** Skip ad-network requests while the page renders. Default: false. */
    public Builder blockAds(Boolean value) {
      values.put("block_ads", value);
      return this;
    }

    /** Hard ceiling, in seconds, for the render session. When reached, BrowserSolver returns whatever HTML is available. Default: 30. */
    public Builder timeout(Number value) {
      values.put("timeout", value);
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

    /** Validates required parameters and returns the immutable {@link WebsiteRenderingParams}. */
    public WebsiteRenderingParams build() {
      if (values.get("query") == null) {
        throw new BrowserSolverSerializationException("The \"query\" parameter is required.", "query");
      }
      return new WebsiteRenderingParams(java.util.Collections.unmodifiableMap(new LinkedHashMap<>(values)));
    }
  }
}
