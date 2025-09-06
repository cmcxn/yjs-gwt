package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Wrapper for Y.Doc - the shared document that contains the shared data types.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Y.Doc")
public class Doc {
    
    @JsConstructor
    public Doc() {}
    
    @JsMethod
    public native YText getText(String name);
    
    @JsMethod
    public native void destroy();
}