package org.chenmin.gwt.yjs.client.indexeddb;

import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import org.chenmin.gwt.yjs.client.yjs.Doc;

/**
 * Wrapper for Y-IndexedDB persistence.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "IndexeddbPersistence")
public class IndexeddbPersistence {
    
    @JsConstructor
    public IndexeddbPersistence(String roomName, Doc doc) {}
    
    @JsMethod
    public native void on(String eventName, SyncEventHandler handler);
}