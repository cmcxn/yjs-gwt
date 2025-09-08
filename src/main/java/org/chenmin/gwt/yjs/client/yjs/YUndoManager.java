package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Wrapper for Y.UndoManager - a selective Undo/Redo manager for Yjs.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Y.UndoManager")
public class YUndoManager {
    
    /**
     * Creates a new Y.UndoManager on a scope of shared types.
     */
    @JsConstructor
    public YUndoManager(YAbstractType scope) {}
    
    /**
     * Creates a new Y.UndoManager on multiple shared types.
     */
    @JsConstructor
    public YUndoManager(YAbstractType[] scope) {}
    
    /**
     * Creates a new Y.UndoManager with options.
     */
    @JsConstructor
    public YUndoManager(YAbstractType scope, YUndoManagerOptions options) {}
    
    /**
     * Creates a new Y.UndoManager on multiple shared types with options.
     */
    @JsConstructor
    public YUndoManager(YAbstractType[] scope, YUndoManagerOptions options) {}
    
    /**
     * Undo the last operation on the UndoManager stack.
     */
    @JsMethod
    public native void undo();
    
    /**
     * Redo the last operation on the redo-stack.
     */
    @JsMethod
    public native void redo();
    
    /**
     * Call stopCapturing() to ensure that the next operation is not merged.
     */
    @JsMethod
    public native void stopCapturing();
    
    /**
     * Delete all captured operations from the undo & redo stack.
     */
    @JsMethod
    public native void clear();
    
    /**
     * Register an event handler.
     */
    @JsMethod
    public native void on(String eventName, YUndoManagerEventHandler handler);
    
    /**
     * Register an event handler that only fires once.
     */
    @JsMethod
    public native void once(String eventName, YUndoManagerEventHandler handler);
    
    /**
     * Unregister an event handler.
     */
    @JsMethod
    public native void off(String eventName, YUndoManagerEventHandler handler);
}