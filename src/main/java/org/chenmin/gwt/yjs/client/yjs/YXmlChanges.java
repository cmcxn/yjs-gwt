package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Changes object for Y.Xml* events.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class YXmlChanges {
    
    /**
     * Delta changes for child elements (insertions/deletions).
     */
    @JsProperty
    public native YDelta[] getDelta();
    
    /**
     * Map of attribute changes (for XmlElement).
     */
    @JsProperty
    public native Object getKeys();
}