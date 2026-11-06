# BrowserSolver Java SDK

The official Java SDK for the [BrowserSolver](https://www.browsersolver.com) web unlocker API.

Unlock, render, crawl and screenshot any website — anti-bot bypass and technology detection through one API.

The SDK exposes **9 typed methods**, one per documented endpoint, each with a parameter builder, plus a generic `request()`. It uses `java.net.http.HttpClient` and Jackson.

## Requirements

- Java 17 or newer

## Installation

Maven:

```xml
<dependency>
  <groupId>com.browsersolver</groupId>
  <artifactId>browsersolver</artifactId>
  <version>0.1.0</version>
</dependency>
```

Gradle:

```kotlin
implementation("com.browsersolver:browsersolver:0.1.0")
```

## Get your API key

[Sign up for BrowserSolver](https://app.browsersolver.com/register) or sign in, then copy your API key from the [dashboard](https://app.browsersolver.com). `BrowserSolverClient.fromEnv()` reads the `BROWSERSOLVER_API_KEY` environment variable.

## Quick start

```java
import com.browsersolver.ApiResponse;
import com.browsersolver.BrowserSolverClient;
import com.browsersolver.params.WebsiteWebunlockerParams;

BrowserSolverClient client = BrowserSolverClient.fromEnv();
ApiResponse response = client.websiteWebunlocker(WebsiteWebunlockerParams.builder().query("https://example.com").build());
System.out.println(response.json().toPrettyString());
```

Parameter builders mirror the [API reference](https://app.browsersolver.com/openapi); `build()` checks required parameters. Use `response.as(MyType.class)` to map the JSON onto your own types.

## Binary responses

Endpoints that return a file (image, PDF…) give you the bytes; `isJson()` tells both cases apart:

```java
ApiResponse response = client.websiteScreenshot(WebsiteScreenshotParams.builder().url("https://example.com").format("png").build());
if (!response.isJson()) {
  java.nio.file.Files.write(java.nio.file.Path.of("screenshot.png"), response.bytes());
}
```

## Async jobs

Any call accepts `is_async: true` (useful past the 60-second server limit): the response is then a `job_id`. `waitForJob` polls `GET /v1/jobs/{id}` until a terminal status (`CompletedJob`, `Error`, `NotFound`, `BadRequest`):

```java
ApiResponse submitted = client.websiteWebunlocker(WebsiteWebunlockerParams.builder().query("https://example.com").isAsync(true).build());
ApiResponse job = client.waitForJob(
    submitted.json().get("job_id").asText(),
    Duration.ofSeconds(3),
    Duration.ofHours(12),
    progress -> System.out.println(progress.path("status").asText() + " " + progress.path("progress").asInt()));
```

## GET or POST

Methods use GET when the endpoint supports it (query string). POST sends the same parameters as JSON:

```java
client.websiteCrawler(WebsiteCrawlerParams.builder().query("https://books.toscrape.com/").build(), RequestOptions.method("POST"));
```

## Any endpoint

```java
ApiResponse response = client.request("GET", "/v1/usage", Map.of());
```

## Errors

```java
try {
  client.websiteWebunlocker(WebsiteWebunlockerParams.builder().query("https://example.com").build());
} catch (BrowserSolverApiException e) {
  System.err.println(e.status() + ": " + e.getMessage());
}
```

| Exception | When |
| --- | --- |
| `BrowserSolverConfigurationException` | Missing API key, invalid options or unsupported method. |
| `BrowserSolverSerializationException` | A required parameter is missing or a value can't be sent. |
| `BrowserSolverTransportException` | Network failure, timeout or interruption. |
| `BrowserSolverApiException` | Non-2xx status (`status()`, `errorMessage()`, `responseBody()`, `rawResponse()`). |
| `BrowserSolverResponseDecodingException` | A success response could not be decoded. |

All of them extend the unchecked `BrowserSolverException`.

## Retries

Requests are retried up to 3 times after a network error, a `408`, `500`, `502`, `503` or `504`, or a rate limit — which the API returns as `401` (or `429`) with a `Rate limit exceeded` message. Per-key quota errors (`…: quota daily`) are not retried, nor are `400`, `402`, `404` or client-side timeouts. The delay starts at 1 second and grows 1.5× per retry (capped at 30 seconds); a `Retry-After` header wins.

```java
BrowserSolverClient client = BrowserSolverClient.builder().maxRetries(5).retryDelay(Duration.ofMillis(500)).build();
client.websiteWebunlocker(WebsiteWebunlockerParams.builder().query("https://example.com").build(), RequestOptions.builder().maxRetries(0).build()); // one request without retries
```

## Batch requests

`batch()` runs a function per item on 10 threads (`concurrency`) and returns one `BatchResult` per item, in input order. A failing item never stops the others.

```java
List<String> inputs = List.of("https://example.com", "https://www.browsersolver.com");
List<BatchResult<ApiResponse>> results = client.batch(inputs, 5, value ->
    client.websiteWebunlocker(WebsiteWebunlockerParams.builder().query(value).build()));
for (BatchResult<ApiResponse> result : results) {
  if (result.ok()) {
    System.out.println(result.value().json());
  } else {
    System.err.println(inputs.get(result.index()) + ": " + result.error().getMessage());
  }
}
```

| Constant | Default | Description |
| --- | --- | --- |
| `BrowserSolverClient.DEFAULT_CONCURRENCY` | `10` | Tasks run at the same time by batch |
| `BrowserSolverClient.DEFAULT_TIMEOUT` | `Duration.ofSeconds(120)` | Request timeout |
| `BrowserSolverClient.MAX_RETRIES` | `3` | Retry attempts after a network error, a 408/5xx or a rate limit |
| `BrowserSolverClient.RETRY_BACKOFF_FACTOR` | `1.5` | Exponential backoff multiplier |
| `BrowserSolverClient.DEFAULT_RETRY_DELAY` | `Duration.ofSeconds(1)` | Delay before the first retry |

## Configuration

```java
BrowserSolverClient client = BrowserSolverClient.builder()
    .apiKey(System.getenv("BROWSERSOLVER_API_KEY"))
    .timeout(Duration.ofSeconds(60)) // default 120 s, null disables
    .header("X-Trace", "abc")
    .transport(BrowserSolverTransport.fromHttpClient(myHttpClient)) // optional
    .build();
```

## Endpoints

9 methods, generated from the [OpenAPI document](https://app.browsersolver.com/openapi).

| Method | HTTP | Description |
| --- | --- | --- |
| `jobs()` | GET `/v1/jobs/{id}` | Poll the status, progress (0–100) and result of a job started with is_async=true. Polling is free. Poll until status is terminal: Completed… |
| `stats()` | GET `/v1/stats` | Infrastructure health for an engine or endpoint over the last hour. Not scoped to your account or tenant. Enabled per account: HTTP 403 mea… |
| `usage()` | GET `/v1/usage` | Retrieve real-time usage statistics and credit balance for your BrowserSolver account. |
| `websiteAntibot()` | GET `/v1/website/antibot` | Single-pass scan that names the bot-protection vendor in front of a URL so you can choose the right BrowserSolver fetch tier next. |
| `websiteCrawler()` | GET / POST `/v1/website/crawler` | Fast HTTP pull of public HTML with no browser session. Ideal when the first response already contains the content you need. |
| `websiteRendering()` | GET `/v1/website/rendering` | Run a managed browser session against a public URL and collect the HTML after client-side scripts finish. Prefer Crawler for static markup;… |
| `websiteScreenshot()` | GET / POST `/v1/website/screenshot` | Turn any public URL into a PNG, JPEG, WebP, or PDF asset rendered by BrowserSolver. No local Chromium fleet to operate. |
| `websiteTechnology()` | GET / POST `/v1/website/technology` | Map CMS, frameworks, analytics, CDN, and security tooling exposed by a public URL. Choose simple lists or expert metadata with confidence s… |
| `websiteWebunlocker()` | GET / POST `/v1/website/webunlocker` | BrowserSolver's highest HTML fetch tier for retail and marketplace hosts on your account whitelist. Solves persistent bot challenges that s… |

## License

MIT — see [LICENSE](LICENSE).
