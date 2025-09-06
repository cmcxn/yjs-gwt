package org.chenmin.gwt.yjs.client.indexeddb;

import jsinterop.annotations.JsFunction;

/**
 * Handler for IndexedDB sync events.
 */
@JsFunction
public interface SyncEventHandler {
    void onSync();
}