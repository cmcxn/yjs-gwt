package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsFunction;

/**
 * Observer for Y.Text changes.
 */
@JsFunction
@FunctionalInterface
public interface YTextObserver {
    void onTextChange(YTextEvent event, YTransaction transaction);
}