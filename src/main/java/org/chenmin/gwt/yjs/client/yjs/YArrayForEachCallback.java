package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsFunction;

/**
 * Callback for YArray forEach operation.
 */
@JsFunction
@FunctionalInterface
public interface YArrayForEachCallback {
    void forEach(Object value, int index, YArray yarray);
}