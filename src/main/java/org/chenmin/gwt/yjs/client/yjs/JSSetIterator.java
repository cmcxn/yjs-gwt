package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * JSInterop wrapper for JavaScript Set Iterator.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class JSSetIterator<T> {
    
    /**
     * Gets the next value from the iterator.
     */
    @JsMethod
    public native JSSetIteratorResult<T> next();
}

/**
 * Result from JavaScript Set Iterator.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
class JSSetIteratorResult<T> {
    
    /**
     * The value from the iterator.
     */
    @JsProperty
    public native T getValue();
    
    /**
     * Whether the iterator is done.
     */
    @JsProperty
    public native boolean getDone();
}