# Examples

Install the SDK locally, then run an example:

```sh
mvn -q install -DskipTests
cd examples
export BROWSERSOLVER_API_KEY="YOUR_API_KEY"
mvn -q compile exec:java -Dexec.mainClass=com.browsersolver.examples.JsonExample
```

- `JsonExample` — call the featured endpoint and print the JSON result.
- `BinaryExample` — save a binary response to a file.
- `RequestExample` — call any path with `request()` and handle API errors.
- `WaitForJobExample` — start an async job and wait for its result.
