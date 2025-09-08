package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Changes object for Y.Array events containing delta information.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class YArrayChanges {
    
    /**
     * Array-Delta format for calculating differences.
     */
    @JsProperty
    public native YDelta[] getDelta();
}