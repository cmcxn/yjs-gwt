package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import java.util.Set;

/**
 * Event for Y.Map changes.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class YMapEvent extends YEvent {
    
    /**
     * A Set containing all keys that were modified during a transaction.
     */
    @JsProperty
    public native Set<String> getKeysChanged();
    
    /**
     * Compute the differences for map changes.
     */
    @JsProperty
    public native YMapChanges getChanges();
}