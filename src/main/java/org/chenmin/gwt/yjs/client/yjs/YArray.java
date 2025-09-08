package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Wrapper for Y.Array - a shared type to store data in a sequence-like data structure.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Y.Array")
public class YArray extends YAbstractType {
    
    @JsConstructor
    public YArray() {}
    
    /**
     * Create a Y.Array from existing content.
     */
    @JsMethod
    public static native YArray from(Object[] content);
    
    /**
     * The Yjs document that this type is bound to.
     */
    @JsProperty
    public native Doc getDoc();
    
    /**
     * The parent that holds this type.
     */
    @JsProperty
    public native YAbstractType getParent();
    
    /**
     * The number of elements that this Y.Array holds.
     */
    @JsProperty
    public native int getLength();
    
    /**
     * Insert content at a specified index.
     */
    @JsMethod
    public native void insert(int index, Object[] content);
    
    /**
     * Delete length Y.Array elements starting from index.
     */
    @JsMethod
    public native void delete(int index, int length);
    
    /**
     * Append content at the end of the Y.Array.
     */
    @JsMethod
    public native void push(Object[] content);
    
    /**
     * Prepend content to the beginning of the Y.Array.
     */
    @JsMethod
    public native void unshift(Object[] content);
    
    /**
     * Retrieve the n-th element.
     */
    @JsMethod
    public native Object get(int index);
    
    /**
     * Retrieve a range of content.
     */
    @JsMethod
    public native Object[] slice();
    
    /**
     * Retrieve a range of content from start to end.
     */
    @JsMethod
    public native Object[] slice(int start);
    
    /**
     * Retrieve a range of content from start to end.
     */
    @JsMethod
    public native Object[] slice(int start, int end);
    
    /**
     * Copies the content of the Y.Array to a new Array.
     */
    @JsMethod
    public native Object[] toArray();
    
    /**
     * Retrieve the JSON representation of this type.
     */
    @JsMethod
    public native Object[] toJSON();
    
    /**
     * Execute the provided function once on every element.
     */
    @JsMethod
    public native void forEach(YArrayForEachCallback callback);
    
    /**
     * Creates a new Array filled with the results of calling the provided function.
     */
    @JsMethod
    public native <T> T[] map(YArrayMapCallback<T> callback);
    
    /**
     * Clone all values into a fresh Y.Array instance.
     */
    @JsMethod
    public native YArray clone();
    
    /**
     * Registers a change observer.
     */
    @JsMethod
    public native void observe(YArrayObserver observer);
    
    /**
     * Unregisters a change observer.
     */
    @JsMethod
    public native void unobserve(YArrayObserver observer);
    
    /**
     * Registers a deep change observer.
     */
    @JsMethod
    public native void observeDeep(YDeepObserver observer);
    
    /**
     * Unregisters a deep change observer.
     */
    @JsMethod
    public native void unobserveDeep(YDeepObserver observer);
}