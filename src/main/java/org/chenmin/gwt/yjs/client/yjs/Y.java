package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Wrapper for the main Y namespace.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Y")
public class Y {
    
    @JsMethod
    public static native void transact(Doc doc, TransactionFunction callback);
}