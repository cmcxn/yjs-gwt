package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsFunction;

/**
 * Observer for text changes.
 */
@JsFunction
public interface TextObserver {
    void onTextChange(YTextEvent event);
}