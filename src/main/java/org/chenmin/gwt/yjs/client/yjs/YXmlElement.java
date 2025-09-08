package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Wrapper for Y.XmlElement - a shared type that represents an XML node.
 * Inherits from Y.XmlFragment.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Y.XmlElement")
public class YXmlElement extends YXmlFragment {
    
    /**
     * Create a new Y.XmlElement with the specified node name.
     */
    @JsConstructor
    public YXmlElement(String nodeName) {}
    
    /**
     * The name of this Y.XmlElement as a String.
     */
    @JsProperty
    public native String getNodeName();
    
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
     * Returns the XML-String representation of this element. E.g. "<div height="30px"></div>"
     */
    @JsMethod
    public native String toString();
    
    /**
     * Set an XML attribute. Technically, the value can only be a string. But we also allow shared types.
     */
    @JsMethod
    public native void setAttribute(String name, String value);
    
    /**
     * Set an XML attribute with a shared type value.
     */
    @JsMethod
    public native void setAttribute(String name, YAbstractType value);
    
    /**
     * Remove an XML attribute.
     */
    @JsMethod
    public native void removeAttribute(String name);
    
    /**
     * Retrieve an XML attribute.
     */
    @JsMethod
    public native Object getAttribute(String name);
    
    /**
     * Retrieve all XML attributes.
     */
    @JsMethod
    public native Object getAttributes();
}