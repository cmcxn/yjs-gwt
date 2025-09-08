package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Wrapper for Y.Doc - the shared document that contains the shared data types.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Y.Doc")
public class Doc {
    
    @JsConstructor
    public Doc() {}
    
    /**
     * A unique id that identifies a client for a session.
     */
    @JsProperty
    public native double getClientID();
    
    /**
     * Whether garbage collection is enabled on this doc instance.
     */
    @JsProperty
    public native boolean getGc();
    
    @JsProperty
    public native void setGc(boolean gc);
    
    /**
     * Execute a transaction on the document.
     */
    @JsMethod
    public native void transact(TransactionFunction function);
    
    /**
     * Execute a transaction on the document with origin.
     */
    @JsMethod
    public native void transact(TransactionFunction function, Object origin);
    
    /**
     * Get a top-level instance of a shared type.
     */
    @JsMethod
    public native <T> T get(String name, Class<T> typeClass);
    
    /**
     * Define a shared Y.Array type.
     */
    @JsMethod
    public native YArray getArray();
    
    /**
     * Define a shared Y.Array type with a name.
     */
    @JsMethod
    public native YArray getArray(String name);
    
    /**
     * Define a shared Y.Map type.
     */
    @JsMethod
    public native YMap getMap();
    
    /**
     * Define a shared Y.Map type with a name.
     */
    @JsMethod
    public native YMap getMap(String name);
    
    /**
     * Define a shared Y.Text type.
     */
    @JsMethod
    public native YText getText();
    
    /**
     * Define a shared Y.Text type with a name.
     */
    @JsMethod
    public native YText getText(String name);
    
    /**
     * Define a shared Y.XmlFragment type.
     */
    @JsMethod
    public native YXmlFragment getXmlFragment();
    
    /**
     * Define a shared Y.XmlFragment type with a name.
     */
    @JsMethod
    public native YXmlFragment getXmlFragment(String name);
    
    /**
     * Destroy this Y.Doc instance.
     */
    @JsMethod
    public native void destroy();
    
    /**
     * Register an event handler.
     */
    @JsMethod
    public native void on(String eventName, DocEventHandler handler);
    
    /**
     * Register an event handler that only fires once.
     */
    @JsMethod
    public native void once(String eventName, DocEventHandler handler);
    
    /**
     * Unregister an event handler.
     */
    @JsMethod
    public native void off(String eventName, DocEventHandler handler);
}