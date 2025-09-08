package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Wrapper for Y.XmlFragment - a shared XML fragment type.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Y.XmlFragment")
public class YXmlFragment extends YAbstractType {
    
    @JsConstructor
    public YXmlFragment() {}
    
    /**
     * The Yjs document that this type is bound to.
     */
    @JsProperty
    public native Doc getDoc();
    
    /**
     * The parent that holds this type.
     */
    @JsProperty
    public native YAbstractType getParent();
}