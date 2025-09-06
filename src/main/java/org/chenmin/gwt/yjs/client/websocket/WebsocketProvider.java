package org.chenmin.gwt.yjs.client.websocket;

import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import org.chenmin.gwt.yjs.client.yjs.Doc;

/**
 * Wrapper for Y-WebSocket provider.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "WebsocketProvider")
public class WebsocketProvider {
    
    @JsConstructor
    public WebsocketProvider(String serverUrl, String roomName, Doc doc) {}
    
    @JsMethod
    public native void on(String eventName, StatusEventHandler handler);
    
    @JsMethod
    public native void destroy();
}