package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Event object for Y.Xml* changes.
 * Inherits from Y.Event.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Y.XmlEvent")
public class YXmlEvent extends YEvent {
    
    /**
     * The target object that changed.
     */
    @JsProperty
    public native YAbstractType getTarget();
    
    /**
     * The changes that occurred.
     */
    @JsProperty
    public native YXmlChanges getChanges();
    
    /**
     * Set of keys that changed (for attribute changes in XmlElement).
     * Returns a JavaScript Set wrapped with JSInterop for proper API compatibility.
     */
    @JsProperty
    public native JSSet<String> getKeysChanged();
}