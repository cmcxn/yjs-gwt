package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Wrapper for Y.RelativePosition - a powerful position encoding that transforms back to index positions.
 * A relative position is fixated to an element in the shared document and is not affected by remote changes.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class YRelativePosition {
    
    /**
     * The type that this relative position is associated with.
     */
    @JsProperty
    public YAbstractType type;
    
    /**
     * The index position within the type.
     */
    @JsProperty
    public int index;
    
    /**
     * The association value. If assoc >= 0, position associates with character after index.
     * If assoc < 0, position associates with character before index.
     */
    @JsProperty
    public int assoc;
}