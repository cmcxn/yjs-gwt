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
    
    /**
     * Create a relative position fixated to the i-th element in any sequence-like shared type.
     * By default, the position associates with the character that comes after the specified index position.
     * If assoc < 0, then the relative position associates with the character before the specified index position.
     */
    @JsMethod
    public static native YRelativePosition createRelativePositionFromTypeIndex(YAbstractType type, int index);
    
    /**
     * Create a relative position fixated to the i-th element in any sequence-like shared type with association.
     */
    @JsMethod
    public static native YRelativePosition createRelativePositionFromTypeIndex(YAbstractType type, int index, int assoc);
    
    /**
     * Create an absolute position from a relative position. 
     * If the relative position cannot be referenced, or the type is deleted, then the result is null.
     */
    @JsMethod
    public static native YAbsolutePosition createAbsolutePositionFromRelativePosition(YRelativePosition relativePosition, Doc doc);
    
    /**
     * Encode a relative position to an Uint8Array. Binary data is the preferred encoding format for document updates.
     */
    @JsMethod
    public static native Object encodeRelativePosition(YRelativePosition relativePosition);
    
    /**
     * Decode a binary-encoded relative position to a RelativePosition object.
     */
    @JsMethod
    public static native YRelativePosition decodeRelativePosition(Object uint8Array);
}