package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import java.util.Set;

/**
 * Options for Y.UndoManager constructor.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class YUndoManagerOptions {
    
    /**
     * Capture timeout in milliseconds (defaults to 500ms).
     */
    @JsProperty
    public int captureTimeout;
    
    /**
     * Set of origins to track changes from.
     */
    @JsProperty
    public Set<Object> trackedOrigins;
    
    /**
     * Function to filter which items should be tracked.
     */
    @JsProperty
    public YDeleteFilter deleteFilter;
}