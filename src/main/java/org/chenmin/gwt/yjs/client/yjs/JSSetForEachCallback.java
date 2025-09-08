package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsFunction;

/**
 * Functional interface for JavaScript Set forEach callback.
 */
@JsFunction
public interface JSSetForEachCallback<T> {
    void call(T value, T value2, JSSet<T> set);
}