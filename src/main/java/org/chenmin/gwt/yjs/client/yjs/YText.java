package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Wrapper for Y.Text - shared text type for collaborative editing.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class YText {

    @JsMethod
    public native String toString();
    
    @JsMethod
    public native void delete(int index, int length);
    
    @JsMethod
    public native void insert(int index, String content);
    
    @JsProperty
    public native int getLength();
    
    @JsMethod
    public native void observe(TextObserver observer);
}