package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import java.util.Map;

/**
 * Changes object for Y.Map events containing detailed change information.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class YMapChanges {
    
    /**
     * Map of key changes with action and old value information.
     */
    @JsProperty
    public native Map<String, YMapKeyChange> getKeys();
}