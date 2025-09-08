package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsFunction;

/**
 * Callback for YMap forEach operation.
 */
@JsFunction
@FunctionalInterface
public interface YMapForEachCallback {
    void forEach(Object value, String key, YMap map);
}