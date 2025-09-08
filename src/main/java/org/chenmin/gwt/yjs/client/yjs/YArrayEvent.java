package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Event for Y.Array changes.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class YArrayEvent extends YEvent {
    
    /**
     * Changes in the array-delta format.
     */
    @JsProperty
    public native YArrayChanges getChanges();
}