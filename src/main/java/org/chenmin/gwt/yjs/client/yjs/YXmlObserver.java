package org.chenmin.gwt.yjs.client.yjs;

import jsinterop.annotations.JsFunction;

/**
 * Observer for Y.Xml* changes (XmlFragment, XmlElement, XmlText).
 */
@JsFunction
@FunctionalInterface
public interface YXmlObserver {
    void onXmlChange(YXmlEvent event, YTransaction transaction);
}