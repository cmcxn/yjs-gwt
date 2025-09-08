package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Wrapper for Y.XmlFragment - a shared type to manage a collection of Y.Xml* Nodes.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Y.XmlFragment")
public class YXmlFragment extends YAbstractType {
    
    @JsConstructor
    public YXmlFragment() {}
    
    /**
     * The Yjs document that this type is bound to. Is null when it is not bound yet.
     */
    @JsProperty
    public native Doc getDoc();
    
    /**
     * The parent that holds this type. Is null if this xmlFragment is a top-level type.
     */
    @JsProperty
    public native YAbstractType getParent();
    
    /**
     * The first child that this type holds. Is null if this type doesn't hold any children.
     */
    @JsProperty
    public native Object getFirstChild();
    
    /**
     * The number of child-elements that this Y.XmlFragment holds.
     */
    @JsProperty
    public native int getLength();
    
    /**
     * Insert content at a specified index. Note that - for performance reasons - content is always an array of elements.
     */
    @JsMethod
    public native void insert(int index, Object[] content);
    
    /**
     * Insert content after a reference element. If the reference element ref is null, then the content is inserted at the beginning.
     */
    @JsMethod
    public native void insertAfter(Object ref, Object[] content);
    
    /**
     * Delete length elements starting from index.
     */
    @JsMethod
    public native void delete(int index, int length);
    
    /**
     * Append content at the end of the Y.XmlFragment. Equivalent to insert(length, content).
     */
    @JsMethod
    public native void push(Object[] content);
    
    /**
     * Prepend content to the beginning of the Y.XmlFragment. Same as insert(0, content).
     */
    @JsMethod
    public native void unshift(Object[] content);
    
    /**
     * Retrieve the n-th element.
     */
    @JsMethod
    public native Object get(int index);
    
    /**
     * Retrieve a range of content starting from index start (inclusive) to index end (exclusive).
     */
    @JsMethod
    public native Object[] slice();
    
    /**
     * Retrieve a range of content starting from index start (inclusive) to index end (exclusive).
     */
    @JsMethod
    public native Object[] slice(int start);
    
    /**
     * Retrieve a range of content starting from index start (inclusive) to index end (exclusive).
     */
    @JsMethod
    public native Object[] slice(int start, int end);
    
    /**
     * Retrieve the JSON representation of this type. The result is a concatenated string of XML elements.
     */
    @JsMethod
    public native String toJSON();
    
    /**
     * Create an Iterable that walks through all children of this type (not only direct children).
     */
    @JsMethod
    public native Object createTreeWalker(Object filter);
    
    /**
     * Clone all values into a fresh Y.XmlFragment instance. The returned type can be included into the Yjs document.
     */
    @JsMethod
    public native YXmlFragment clone();
    
    /**
     * Transforms this type and all children to new DOM elements.
     */
    @JsMethod
    public native Object toDOM();
    
    /**
     * Registers a change observer that will be called synchronously every time this shared type is modified.
     */
    @JsMethod
    public native void observe(YXmlObserver observer);
    
    /**
     * Unregisters a change observer that has been registered with observe.
     */
    @JsMethod
    public native void unobserve(YXmlObserver observer);
    
    /**
     * Registers a change observer that will be called synchronously every time this type or any of its children is modified.
     */
    @JsMethod
    public native void observeDeep(YDeepObserver observer);
    
    /**
     * Unregisters a change observer that has been registered with observeDeep.
     */
    @JsMethod
    public native void unobserveDeep(YDeepObserver observer);
}