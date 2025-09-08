package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Event object for Y.UndoManager events.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class YUndoManagerEvent {
    
    /**
     * The stack item that was added, popped, or updated.
     */
    @JsProperty
    public native YStackItem getStackItem();
    
    /**
     * The origin of the transaction.
     */
    @JsProperty
    public native Object getOrigin();
    
    /**
     * The type of operation: 'undo' or 'redo'.
     */
    @JsProperty
    public native String getType();
    
    /**
     * The changed parent types.
     */
    @JsProperty
    public native YAbstractType[] getChangedParentTypes();
}