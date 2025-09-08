package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsFunction;

/**
 * Event handler for Y.UndoManager events.
 */
@JsFunction
@FunctionalInterface
public interface YUndoManagerEventHandler {
    void onEvent(YUndoManagerEvent event, YUndoManager undoManager);
}