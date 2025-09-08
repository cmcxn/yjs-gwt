package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsFunction;

/**
 * Handler for Y.Doc events.
 */
@JsFunction
@FunctionalInterface
public interface DocEventHandler {
    void onEvent(Object... args);
}