package com.browsersolver;

import java.util.Map;

/** Parameters of one endpoint, in wire order. Implemented by every class in {@code com.browsersolver.params}. */
public interface BrowserSolverParams {
  /** Wire name to value; null values are omitted from requests. */
  Map<String, Object> toMap();
}
