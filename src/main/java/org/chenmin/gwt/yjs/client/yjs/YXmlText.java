package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Wrapper for Y.XmlText - extends Y.Text to represent a Y.Xml node.
 * Inherits from Y.Text.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Y.XmlText")
public class YXmlText extends YText {
    
    /**
     * Create a new Y.XmlText instance.
     */
    @JsConstructor
    public YXmlText() {}
    
    /**
     * Create an instance of Y.XmlText with existing content.
     */
    @JsConstructor
    public YXmlText(String initialContent) {}
    
    /**
     * The previous sibling of this type. Is null if this is the first child of its parent.
     */
    @JsProperty
    public native Object getPrevSibling();
    
    /**
     * The next sibling of this type. Is null if this is the last child of its parent.
     */
    @JsProperty
    public native Object getNextSibling();
    
    /**
     * Returns the XML-String representation of this element. Formatting attributes are transformed to XML-tags.
     * If the formatting attribute contains an object, the key-value pairs will be used as attributes.
     */
    @JsMethod
    public native String toString();
}