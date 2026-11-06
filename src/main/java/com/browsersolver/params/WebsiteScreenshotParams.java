package com.browsersolver.params;

import com.browsersolver.BrowserSolverParams;
import com.browsersolver.BrowserSolverSerializationException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Parameters for {@code GET / POST /v1/website/screenshot}.
 *
 * <p>Generated from the BrowserSolver OpenAPI document. Do not edit by hand.
 */
public final class WebsiteScreenshotParams implements BrowserSolverParams {
  private final Map<String, Object> values;

  private WebsiteScreenshotParams(Map<String, Object> values) {
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

  /** Builder for {@link WebsiteScreenshotParams}. */
  public static final class Builder {
    private final Map<String, Object> values = new LinkedHashMap<>();

    private Builder() {}

    /** Required. Page address to render into an image or PDF. Must include a protocol. */
    public Builder url(String value) {
      values.put("url", value);
      return this;
    }

    /** File type returned in the response body. Allowed values: jpeg, jpg, pdf, png, webp. Default: "png". */
    public Builder format(String value) {
      values.put("format", value);
      return this;
    }

    /** Swap the desktop viewport for a preset handset or tablet fingerprint. Allowed values: galaxy_fold4, galaxy_s23, galaxy_s23_ultra, galaxy_s8, huawei_mate_50_pro, huawei_p60_pro, ipad, iphone_12, iphone_13_mini, iphone_13_pro_max, iphone_14_pro, iphone_14_pro_max, iphone_x, pixel_5, pixel_6a, pixel_7_pro, redmi_note_11, redmi_note_12_pro. */
    public Builder device(String value) {
      values.put("device", value);
      return this;
    }

    /** Extend the capture beyond the viewport so the entire scrollable document is included. Default: false. */
    public Builder fullPage(Boolean value) {
      values.put("full_page", value);
      return this;
    }

    /** Drive a scroll pass first so deferred content mounts before a tall capture. Default: false. */
    public Builder fullPageScroll(Boolean value) {
      values.put("full_page_scroll", value);
      return this;
    }

    /** Milliseconds spent scrolling prior to a full-height capture. Default: 400. */
    public Builder fullPageScrollDuration(Number value) {
      values.put("full_page_scroll_duration", value);
      return this;
    }

    /** Width of the synthetic desktop window, in pixels. Default: 1280. */
    public Builder viewportWidth(Number value) {
      values.put("viewport_width", value);
      return this;
    }

    /** Height of the synthetic desktop window, in pixels. Default: 1024. */
    public Builder viewportHeight(Number value) {
      values.put("viewport_height", value);
      return this;
    }

    /** Pixel density multiplier. Raise it for retina-quality output; files grow accordingly. Default: 1. */
    public Builder scaleFactor(Number value) {
      values.put("scale_factor", value);
      return this;
    }

    /** Lossy compression level for soft formats. Default: "80". */
    public Builder imageQuality(String value) {
      values.put("image_quality", value);
      return this;
    }

    /** Limit the shot to one DOM node matched by this CSS selector. */
    public Builder selector(String value) {
      values.put("selector", value);
      return this;
    }

    /** Serve a stored capture on repeat requests when caching is enabled. Default: false. */
    public Builder cache(Boolean value) {
      values.put("cache", value);
      return this;
    }

    /** Lifetime of cached captures in seconds. Default: 2592000. */
    public Builder cacheTtl(Number value) {
      values.put("cache_ttl", value);
      return this;
    }

    /** Hide matching CSS selectors (modals, promo bars, etc.) before shooting. */
    public Builder removeSelectors(String value) {
      values.put("remove_selectors", value);
      return this;
    }

    /** Best-effort removal of cookie consent layers before capture. Default: false. */
    public Builder removeCookieBanners(Boolean value) {
      values.put("remove_cookie_banners", value);
      return this;
    }

    /** Best-effort stripping of ad slots before capture. Default: false. */
    public Builder removeAds(Boolean value) {
      values.put("remove_ads", value);
      return this;
    }

    /** Resource kinds to discard during load, as a comma list (example: image,stylesheet,font). Allowed values: document, eventsource, fetch, font, image, manifest, media, other, script, stylesheet, texttrack, websocket, xhr. */
    public Builder blockResources(String value) {
      values.put("block_resources", value);
      return this;
    }

    /** Substring patterns that should never be fetched (example: analytics,tracking,advertisement). Hosts, paths, or fragments such as .example.com/ work. */
    public Builder blockUrls(String value) {
      values.put("block_urls", value);
      return this;
    }

    /** Additional settle time in seconds after the readiness event. Default: 0. */
    public Builder delay(Number value) {
      values.put("delay", value);
      return this;
    }

    /** Which browser lifecycle event marks the page ready. Allowed values: domcontentloaded, load, networkidle0, networkidle2. */
    public Builder waitUntil(List<String> value) {
      values.put("wait_until", value);
      return this;
    }

    /** Hold capture until this CSS node is present. */
    public Builder waitForSelector(String value) {
      values.put("wait_for_selector", value);
      return this;
    }

    /** Route the browser through an intermediate proxy. */
    public Builder proxy(String value) {
      values.put("proxy", value);
      return this;
    }

    /** Secret key used to authenticate uploads. */
    public Builder s3SecretKey(String value) {
      values.put("s3_secret_key", value);
      return this;
    }

    /** Access key used to authenticate uploads. */
    public Builder s3AccessKeyId(String value) {
      values.put("s3_access_key_id", value);
      return this;
    }

    /** Destination bucket for the binary asset. */
    public Builder s3Bucket(String value) {
      values.put("s3_bucket", value);
      return this;
    }

    /** Key under which the object is stored (generated automatically if blank). */
    public Builder s3ObjectKey(String value) {
      values.put("s3_object_key", value);
      return this;
    }

    /** Cloud region of the bucket. */
    public Builder s3Region(String value) {
      values.put("s3_region", value);
      return this;
    }

    /** Override the default AWS endpoint for S3-compatible backends. Leave blank for AWS. R2 example: https://&lt;accountId&gt;.r2.cloudflarestorage.com. */
    public Builder storageEndpoint(String value) {
      values.put("storage_endpoint", value);
      return this;
    }

    /** Upload the binary and include the public object URL in the API response. Default: false. */
    public Builder s3Url(Boolean value) {
      values.put("s3_url", value);
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

    /** Validates required parameters and returns the immutable {@link WebsiteScreenshotParams}. */
    public WebsiteScreenshotParams build() {
      if (values.get("url") == null) {
        throw new BrowserSolverSerializationException("The \"url\" parameter is required.", "url");
      }
      return new WebsiteScreenshotParams(java.util.Collections.unmodifiableMap(new LinkedHashMap<>(values)));
    }
  }
}
