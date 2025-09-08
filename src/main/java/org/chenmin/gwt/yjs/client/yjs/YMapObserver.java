package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsFunction;

/**
 * Observer for Y.Map changes.
 */
@JsFunction
@FunctionalInterface
public interface YMapObserver {
    void onMapChange(YMapEvent event, YTransaction transaction);
}