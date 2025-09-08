package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Wrapper for Y.Map - a shared type with a similar API to global.Map.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Y.Map")
public class YMap extends YAbstractType {
    
    @JsConstructor
    public YMap() {}
    
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
    
    /**
     * Returns the number of key/value pairs.
     */
    @JsProperty
    public native int getSize();
    
    /**
     * Add or update an entry with a specified key.
     */
    @JsMethod
    public native void set(String key, Object value);
    
    /**
     * Returns an entry with the specified key.
     */
    @JsMethod
    public native Object get(String key);
    
    /**
     * Deletes an entry with the specified key.
     */
    @JsMethod
    public native void delete(String key);
    
    /**
     * Returns true if an entry with the specified key exists.
     */
    @JsMethod
    public native boolean has(String key);
    
    /**
     * Removes all elements from this ymap.
     */
    @JsMethod
    public native void clear();
    
    /**
     * Copies the [key,value] pairs of this Y.Map to a new Object.
     */
    @JsMethod
    public native Object toJSON();
    
    /**
     * Execute the provided function once on every key-value pair.
     */
    @JsMethod
    public native void forEach(YMapForEachCallback callback);
    
    /**
     * Returns an Iterator of [key, value] pairs.
     */
    @JsMethod
    public native YMapIterator entries();
    
    /**
     * Returns an Iterator of values only.
     */
    @JsMethod
    public native YMapIterator values();
    
    /**
     * Returns an Iterator of keys only.
     */
    @JsMethod
    public native YMapIterator keys();
    
    /**
     * Clone all values into a fresh Y.Map instance.
     */
    @JsMethod
    public native YMap clone();
    
    /**
     * Registers a change observer.
     */
    @JsMethod
    public native void observe(YMapObserver observer);
    
    /**
     * Unregisters a change observer.
     */
    @JsMethod
    public native void unobserve(YMapObserver observer);
    
    /**
     * Registers a deep change observer.
     */
    @JsMethod
    public native void observeDeep(YDeepObserver observer);
    
    /**
     * Unregisters a deep change observer.
     */
    @JsMethod
    public native void unobserveDeep(YDeepObserver observer);
}