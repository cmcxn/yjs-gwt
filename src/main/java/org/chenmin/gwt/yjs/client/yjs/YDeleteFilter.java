package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsFunction;

/**
 * Function to filter which items should be tracked by UndoManager.
 */
@JsFunction
@FunctionalInterface
public interface YDeleteFilter {
    boolean filter(Object item);
}