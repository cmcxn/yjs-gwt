package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Base class for all Y events.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class YEvent {
    
    /**
     * The shared type that this event was created on.
     */
    @JsProperty
    public native YAbstractType getTarget();
    
    /**
     * The current target of the event as it traverses through the observer callbacks.
     */
    @JsProperty
    public native YAbstractType getCurrentTarget();
    
    /**
     * The transaction in which this event was created.
     */
    @JsProperty
    public native YTransaction getTransaction();
    
    /**
     * Computes the path from the Y.Doc to the changed type.
     */
    @JsProperty
    public native String[] getPath();
}