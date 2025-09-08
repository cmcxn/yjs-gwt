package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Result object returned by createAbsolutePositionFromRelativePosition.
 * Contains the type, index, and association information for an absolute position.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class YAbsolutePosition {
    
    /**
     * The type that this absolute position refers to.
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