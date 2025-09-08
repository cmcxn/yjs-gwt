package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsFunction;

/**
 * Observer for deep changes in Y types.
 */
@JsFunction
@FunctionalInterface
public interface YDeepObserver {
    void onDeepChange(YEvent[] events, YTransaction transaction);
}