package org.chenmin.gwt.yjs.client.websocket;

import jsinterop.annotations.JsFunction;

/**
 * Handler for WebSocket status events.
 */
@JsFunction
public interface StatusEventHandler {
    void onStatus(StatusEvent event);
}