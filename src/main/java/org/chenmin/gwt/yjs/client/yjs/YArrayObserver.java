package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsFunction;

/**
 * Observer for Y.Array changes.
 */
@JsFunction
@FunctionalInterface
public interface YArrayObserver {
    void onArrayChange(YArrayEvent event, YTransaction transaction);
}