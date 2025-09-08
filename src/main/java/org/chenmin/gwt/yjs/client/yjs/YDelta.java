package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Delta format for Y.Array and Y.Text changes.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class YDelta {
    
    /**
     * Insert operation.
     */
    @JsProperty
    public native Object[] getInsert();
    
    /**
     * Retain operation.
     */
    @JsProperty
    public native int getRetain();
    
    /**
     * Delete operation.
     */
    @JsProperty
    public native int getDelete();
    
    /**
     * Attributes for formatting.
     */
    @JsProperty
    public native Object getAttributes();
}