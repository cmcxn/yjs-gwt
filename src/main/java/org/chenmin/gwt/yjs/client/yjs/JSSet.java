package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * JSInterop wrapper for JavaScript Set to provide Java-compatible interface.
 * This bridges the gap between JavaScript Set API and Java Set expectations.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Set")
public class JSSet<T> {
    
    /**
     * The number of elements in the Set (JavaScript property).
     */
    @JsProperty(name = "size")
    public native int getSize();
    
    /**
     * Checks if the Set contains the specified value.
     */
    @JsMethod(name = "has")
    public native boolean contains(T value);
    
    /**
     * Adds a value to the Set.
     */
    @JsMethod
    public native JSSet<T> add(T value);
    
    /**
     * Removes a value from the Set.
     */
    @JsMethod(name = "delete")
    public native boolean remove(T value);
    
    /**
     * Removes all elements from the Set.
     */
    @JsMethod
    public native void clear();
    
    /**
     * Returns an iterator for the Set values.
     */
    @JsMethod
    public native JSSetIterator<T> values();
    
    /**
     * Returns an iterator for the Set (same as values() in JavaScript Set).
     */
    @JsMethod(name = "Symbol.iterator")
    public native JSSetIterator<T> iterator();
    
    /**
     * Executes a function for each Set element.
     */
    @JsMethod
    public native void forEach(JSSetForEachCallback<T> callback);
}