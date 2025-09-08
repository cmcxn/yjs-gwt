package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Changes object for Y.Text events containing delta information.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class YTextChanges {
    
    /**
     * Text-Delta format for calculating differences.
     */
    @JsProperty
    public native YDelta[] getDelta();
}