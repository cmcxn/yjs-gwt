package org.chenmin.gwt.yjs.client.websocket;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * WebSocket status event.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class StatusEvent {
    
    @JsProperty
    public native String getStatus();
}