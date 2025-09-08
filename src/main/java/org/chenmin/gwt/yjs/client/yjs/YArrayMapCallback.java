package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsFunction;

/**
 * Callback for YArray map operation.
 */
@JsFunction
@FunctionalInterface
public interface YArrayMapCallback<T> {
    T map(Object value, int index, YArray yarray);
}