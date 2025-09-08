package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Information about a specific key change in Y.Map.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class YMapKeyChange {
    
    /**
     * The action performed: 'add', 'update', or 'delete'.
     */
    @JsProperty
    public native String getAction();
    
    /**
     * The previous value before the change.
     */
    @JsProperty
    public native Object getOldValue();
}