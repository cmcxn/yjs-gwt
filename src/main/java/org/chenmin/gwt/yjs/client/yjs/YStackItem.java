package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import java.util.Map;

/**
 * Stack item for undo/redo operations.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class YStackItem {
    
    /**
     * Meta information that can be attached to the stack item.
     */
    @JsProperty
    public native Map<String, Object> getMeta();
}