package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Wrapper for Y.Text - shared text type for collaborative editing.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Y.Text")
public class YText {
    
    @JsConstructor
    public YText() {}
    
    /**
     * Create an instance of Y.Text with existing content.
     */
    @JsConstructor
    public YText(String initialContent) {}
    
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
     * The length of the string in UTF-16 code units.
     */
    @JsProperty
    public native int getLength();
    
    /**
     * Insert content at a specified index.
     */
    @JsMethod
    public native void insert(int index, String content);
    
    /**
     * Insert content at a specified index with formatting attributes.
     */
    @JsMethod
    public native void insert(int index, String content, Object format);
    
    /**
     * Assign formatting attributes to a range of text.
     */
    @JsMethod
    public native void format(int index, int length, Object format);
    
    /**
     * Apply a Text-Delta to the Y.Text instance.
     */
    @JsMethod
    public native void applyDelta(YDelta[] delta);
    
    /**
     * Delete length characters starting from index.
     */
    @JsMethod
    public native void delete(int index, int length);
    
    /**
     * Retrieve the string-representation from the Y.Text instance.
     */
    @JsMethod
    public native String toString();
    
    /**
     * Retrieve the Text-Delta-representation of the Y.Text instance.
     */
    @JsMethod
    public native YDelta[] toDelta();
    
    /**
     * Retrieves the string representation of the Y.Text instance.
     */
    @JsMethod
    public native String toJSON();
    
    /**
     * Clone this type into a fresh Y.Text instance.
     */
    @JsMethod
    public native YText clone();
    
    /**
     * Registers a change observer.
     */
    @JsMethod
    public native void observe(YTextObserver observer);
    
    /**
     * Unregisters a change observer.
     */
    @JsMethod
    public native void unobserve(YTextObserver observer);
    
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