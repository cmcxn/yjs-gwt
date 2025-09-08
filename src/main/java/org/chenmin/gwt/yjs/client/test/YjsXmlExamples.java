package org.chenmin.gwt.yjs.client.test;

import com.google.gwt.core.client.GWT;
import org.chenmin.gwt.yjs.client.yjs.*;

/**
 * Comprehensive test examples for Y.Xml* and Y.RelativePosition JSInterop wrappers.
 * Demonstrates all the functionality to validate the wrappers work correctly.
 */
public class YjsXmlExamples {

    /**
     * Demonstrates the usage of Y.XmlFragment wrapper.
     */
    public static void demonstrateXmlFragmentUsage() {
        GWT.log("=== Y.XmlFragment Example ===");
        
        // Create a new Y.Doc
        Doc doc = new Doc();
        
        // Method 1: Define a top-level type
        YXmlFragment xmlFragment = doc.getXmlFragment("my xml fragment");
        GWT.log("Created top-level Y.XmlFragment");
        
        // Method 2: Define Y.XmlFragment that can be included into the Yjs document
        YXmlFragment xmlNested = new YXmlFragment();
        GWT.log("Created nested Y.XmlFragment");
        
        // Create some content
        YXmlText xmlText = new YXmlText();
        YXmlElement xmlElement = new YXmlElement("node-name");
        
        // Test common methods
        xmlFragment.insert(0, new Object[]{xmlText});
        GWT.log("Inserted XmlText into fragment at index 0");
        GWT.log("Fragment length: " + xmlFragment.getLength());
        
        // Test firstChild property
        Object firstChild = xmlFragment.getFirstChild();
        GWT.log("First child retrieved: " + (firstChild != null ? "exists" : "null"));
        
        // Test insertAfter
        xmlFragment.insertAfter(xmlText, new Object[]{xmlElement});
        GWT.log("Inserted XmlElement after XmlText");
        GWT.log("Fragment length after insertAfter: " + xmlFragment.getLength());
        
        // Test get method
        Object element = xmlFragment.get(0);
        GWT.log("Element at index 0: " + (element != null ? "exists" : "null"));
        
        // Test push method
        YXmlElement anotherElement = new YXmlElement("another-node");
        xmlFragment.push(new Object[]{anotherElement});
        GWT.log("Pushed another element, new length: " + xmlFragment.getLength());
        
        // Test unshift method
        YXmlText firstText = new YXmlText("First text");
        xmlFragment.unshift(new Object[]{firstText});
        GWT.log("Unshifted text element, new length: " + xmlFragment.getLength());
        
        // Test slice method
        Object[] slice = xmlFragment.slice(1, 3);
        GWT.log("Slice from 1 to 3, length: " + slice.length);
        
        // Test toJSON
        String json = xmlFragment.toJSON();
        GWT.log("JSON representation: " + json);
        
        // Test clone
        YXmlFragment cloned = xmlFragment.clone();
        GWT.log("Cloned fragment: " + (cloned != null ? "success" : "failed"));
        
        // Test observe functionality
        xmlFragment.observe((YXmlEvent event, YTransaction transaction) -> {
            GWT.log("XML Fragment changed");
        });
        GWT.log("Added observer to xmlFragment");
        
        // Test delete
        xmlFragment.delete(0, 1);
        GWT.log("Deleted first element, new length: " + xmlFragment.getLength());
        
        // Test parent and doc properties
        GWT.log("Fragment doc: " + (xmlFragment.getDoc() != null ? "exists" : "null"));
        GWT.log("Fragment parent: " + (xmlFragment.getParent() != null ? "exists" : "null"));
    }
    
    /**
     * Demonstrates the usage of Y.XmlElement wrapper.
     */
    public static void demonstrateXmlElementUsage() {
        GWT.log("=== Y.XmlElement Example ===");
        
        Doc doc = new Doc();
        
        // Method 1: Define as top-level type (nodeName is undefined)
        YXmlElement topLevelElement = doc.getXmlElement("top-level");
        GWT.log("Created top-level Y.XmlElement");
        
        // Method 2: Define Y.XmlElement that can be included into document
        YXmlElement element = new YXmlElement("div");
        GWT.log("Created Y.XmlElement with nodeName: " + element.getNodeName());
        
        // Test attributes
        element.setAttribute("class", "container");
        element.setAttribute("id", "main-div");
        element.setAttribute("data-value", "123");
        GWT.log("Set attributes: class, id, data-value");
        
        // Get attributes
        Object classAttr = element.getAttribute("class");
        GWT.log("Class attribute: " + classAttr);
        
        Object allAttributes = element.getAttributes();
        GWT.log("All attributes retrieved: " + (allAttributes != null ? "exists" : "null"));
        
        // Test toString (XML representation)
        String xmlString = element.toString();
        GWT.log("XML representation: " + xmlString);
        
        // Create child elements to test sibling properties
        YXmlFragment parent = new YXmlFragment();
        YXmlElement firstChild = new YXmlElement("first");
        YXmlElement secondChild = new YXmlElement("second");
        YXmlElement thirdChild = new YXmlElement("third");
        
        parent.insert(0, new Object[]{firstChild, secondChild, thirdChild});
        GWT.log("Added three child elements to parent");
        
        // Test sibling properties (these will be null for standalone elements)
        Object prevSibling = secondChild.getPrevSibling();
        Object nextSibling = secondChild.getNextSibling();
        GWT.log("Second child prev sibling: " + (prevSibling != null ? "exists" : "null"));
        GWT.log("Second child next sibling: " + (nextSibling != null ? "exists" : "null"));
        
        // Test inherited methods from YXmlFragment
        element.insert(0, new Object[]{new YXmlText("Hello World")});
        GWT.log("Inserted text into element, length: " + element.getLength());
        
        // Remove attribute
        element.removeAttribute("data-value");
        GWT.log("Removed data-value attribute");
        
        // Test with shared type as attribute value
        YText sharedText = new YText("shared value");
        element.setAttribute("shared-attr", sharedText);
        GWT.log("Set attribute with shared type value");
    }
    
    /**
     * Demonstrates the usage of Y.XmlText wrapper.
     */
    public static void demonstrateXmlTextUsage() {
        GWT.log("=== Y.XmlText Example ===");
        
        Doc doc = new Doc();
        
        // Method 1: Create a standalone Y.XmlText instance
        YXmlText topLevelText = new YXmlText();
        GWT.log("Created standalone Y.XmlText");
        
        // Method 2: Define Y.XmlText that can be included into document
        YXmlText xmlText = new YXmlText();
        GWT.log("Created nested Y.XmlText");
        
        // Method 3: Create with initial content
        YXmlText xmlTextWithContent = new YXmlText("Initial content");
        GWT.log("Created Y.XmlText with initial content");
        
        // Test inherited methods from Y.Text
        xmlText.insert(0, "Hello");
        xmlText.insert(5, " World");
        GWT.log("Inserted text, content: " + xmlText.toString());
        GWT.log("Text length: " + xmlText.getLength());
        
        // Test formatting
        Object boldFormat = createFormatObject("bold", true);
        xmlText.format(0, 5, boldFormat);
        GWT.log("Applied bold formatting to 'Hello'");
        
        // Test XML-specific toString (should include XML tags for formatting)
        String xmlString = xmlText.toString();
        GWT.log("XML representation with formatting: " + xmlString);
        
        // Test with complex formatting (object attributes)
        Object linkFormat = createLinkFormat("https://example.com");
        xmlText.insert(11, " Click here");
        xmlText.format(11, 11, linkFormat);
        GWT.log("Added link formatting");
        GWT.log("Final XML representation: " + xmlText.toString());
        
        // Test sibling properties in a fragment context
        YXmlFragment parent = new YXmlFragment();
        YXmlText firstText = new YXmlText("First");
        YXmlText secondText = new YXmlText("Second");
        YXmlText thirdText = new YXmlText("Third");
        
        parent.insert(0, new Object[]{firstText, secondText, thirdText});
        GWT.log("Added three text nodes to parent");
        
        Object prevSibling = secondText.getPrevSibling();
        Object nextSibling = secondText.getNextSibling();
        GWT.log("Second text prev sibling: " + (prevSibling != null ? "exists" : "null"));
        GWT.log("Second text next sibling: " + (nextSibling != null ? "exists" : "null"));
        
        // Test Text-Delta functionality
        YDelta[] delta = xmlText.toDelta();
        GWT.log("Delta representation length: " + delta.length);
        
        // Test observe functionality
        xmlText.observe((YTextEvent event, YTransaction transaction) -> {
            GWT.log("XML Text changed: " + xmlText.toString());
        });
        GWT.log("Added observer to xmlText");
        
        // Make a change to trigger observer
        xmlText.insert(0, "PREFIX: ");
        GWT.log("Added prefix to trigger observer");
    }
    
    /**
     * Demonstrates the usage of Y.RelativePosition wrapper.
     */
    public static void demonstrateRelativePositionUsage() {
        GWT.log("=== Y.RelativePosition Example ===");
        
        Doc doc = new Doc();
        YText text = doc.getText("position-text");
        
        // Insert some content
        text.insert(0, "Hello World! This is a test.");
        GWT.log("Initial text: " + text.toString());
        
        // Create relative position at index 6 (after "Hello ")
        YRelativePosition relPos = Y.createRelativePositionFromTypeIndex(text, 6);
        GWT.log("Created relative position at index 6");
        
        // Create relative position with custom association
        YRelativePosition relPosLeft = Y.createRelativePositionFromTypeIndex(text, 6, -1);
        GWT.log("Created relative position at index 6 with left association");
        
        // Transform back to absolute position
        YAbsolutePosition absPos = Y.createAbsolutePositionFromRelativePosition(relPos, doc);
        if (absPos != null) {
            GWT.log("Absolute position - type: " + (absPos.type != null ? "exists" : "null") + 
                   ", index: " + absPos.index + ", assoc: " + absPos.assoc);
        } else {
            GWT.log("Failed to create absolute position (returned null)");
        }
        
        // Insert content before the relative position
        text.insert(0, "PREFIX: ");
        GWT.log("After inserting prefix: " + text.toString());
        
        // Transform relative position again - should still point to the same logical location
        YAbsolutePosition newAbsPos = Y.createAbsolutePositionFromRelativePosition(relPos, doc);
        if (newAbsPos != null) {
            GWT.log("New absolute position after insertion - index: " + newAbsPos.index);
            // The index should now be 6 + length of "PREFIX: " = 14
        }
        
        // Test encoding and decoding (JSON format)
        String encodedJson = encodeRelativePositionAsJson(relPos);
        GWT.log("Encoded relative position as JSON: " + encodedJson);
        
        YRelativePosition decodedFromJson = decodeRelativePositionFromJson(encodedJson);
        if (decodedFromJson != null) {
            YAbsolutePosition decodedAbsPos = Y.createAbsolutePositionFromRelativePosition(decodedFromJson, doc);
            if (decodedAbsPos != null) {
                GWT.log("Decoded position index: " + decodedAbsPos.index);
            }
        }
        
        // Test binary encoding/decoding
        Object encodedBinary = Y.encodeRelativePosition(relPos);
        GWT.log("Encoded relative position as binary: " + (encodedBinary != null ? "success" : "failed"));
        
        if (encodedBinary != null) {
            YRelativePosition decodedFromBinary = Y.decodeRelativePosition(encodedBinary);
            if (decodedFromBinary != null) {
                YAbsolutePosition decodedBinaryAbsPos = Y.createAbsolutePositionFromRelativePosition(decodedFromBinary, doc);
                if (decodedBinaryAbsPos != null) {
                    GWT.log("Decoded binary position index: " + decodedBinaryAbsPos.index);
                }
            }
        }
        
        // Test with different shared types
        YArray array = doc.getArray("position-array");
        array.insert(0, new Object[]{"first", "second", "third", "fourth"});
        
        YRelativePosition arrayRelPos = Y.createRelativePositionFromTypeIndex(array, 2);
        YAbsolutePosition arrayAbsPos = Y.createAbsolutePositionFromRelativePosition(arrayRelPos, doc);
        if (arrayAbsPos != null) {
            GWT.log("Array relative position - index: " + arrayAbsPos.index);
        }
        
        // Insert in array and test position stability
        array.insert(0, new Object[]{"inserted"});
        YAbsolutePosition newArrayAbsPos = Y.createAbsolutePositionFromRelativePosition(arrayRelPos, doc);
        if (newArrayAbsPos != null) {
            GWT.log("Array position after insertion - index: " + newArrayAbsPos.index);
            // Should be 3 now (2 + 1 for inserted element)
        }
    }
    
    /**
     * Comprehensive test that combines all Y.Xml* types together.
     */
    public static void demonstrateCombinedXmlUsage() {
        GWT.log("=== Combined Y.Xml* Example ===");
        
        Doc doc = new Doc();
        YXmlFragment fragment = doc.getXmlFragment("document-fragment");
        
        // Create a document structure: <div class="container"><p><strong>Hello</strong> World!</p></div>
        YXmlElement div = new YXmlElement("div");
        div.setAttribute("class", "container");
        
        YXmlElement paragraph = new YXmlElement("p");
        YXmlElement strong = new YXmlElement("strong");
        
        YXmlText strongText = new YXmlText("Hello");
        YXmlText normalText = new YXmlText(" World!");
        
        // Build the structure
        strong.insert(0, new Object[]{strongText});
        paragraph.insert(0, new Object[]{strong, normalText});
        div.insert(0, new Object[]{paragraph});
        fragment.insert(0, new Object[]{div});
        
        GWT.log("Created complex XML structure");
        GWT.log("Fragment JSON: " + fragment.toJSON());
        
        // Test navigation
        Object firstChild = fragment.getFirstChild();
        GWT.log("Fragment first child: " + (firstChild != null ? "exists" : "null"));
        
        if (firstChild instanceof YXmlElement) {
            YXmlElement divElement = (YXmlElement) firstChild;
            GWT.log("First child nodeName: " + divElement.getNodeName());
            GWT.log("First child attributes: " + (divElement.getAttributes() != null ? "has attributes" : "no attributes"));
        }
        
        // Test tree walker (if supported)
        try {
            Object treeWalker = fragment.createTreeWalker(createParagraphFilter());
            GWT.log("Created tree walker for <p> elements");
        } catch (Exception e) {
            GWT.log("Tree walker not available or error: " + e.getMessage());
        }
        
        // Test relative positions within the XML structure
        YRelativePosition strongTextPos = Y.createRelativePositionFromTypeIndex(strongText, 2);
        YAbsolutePosition strongAbsPos = Y.createAbsolutePositionFromRelativePosition(strongTextPos, doc);
        if (strongAbsPos != null) {
            GWT.log("Relative position in strong text at index: " + strongAbsPos.index);
        }
        
        // Modify structure and test position stability
        strongText.insert(0, "Hi ");
        YAbsolutePosition newStrongAbsPos = Y.createAbsolutePositionFromRelativePosition(strongTextPos, doc);
        if (newStrongAbsPos != null) {
            GWT.log("Position after modifying strong text: " + newStrongAbsPos.index);
        }
        
        GWT.log("Final structure JSON: " + fragment.toJSON());
    }
    
    // Helper methods for creating formatting objects
    
    private static native Object createFormatObject(String key, Object value) /*-{
        var obj = {};
        obj[key] = value;
        return obj;
    }-*/;
    
    private static native Object createLinkFormat(String href) /*-{
        return { a: { href: href } };
    }-*/;
    
    private static native Object createParagraphFilter() /*-{
        return function(yxml) {
            return yxml.nodeName === 'p';
        };
    }-*/;
    
    private static native String encodeRelativePositionAsJson(YRelativePosition relPos) /*-{
        return JSON.stringify(relPos);
    }-*/;
    
    private static native YRelativePosition decodeRelativePositionFromJson(String json) /*-{
        return JSON.parse(json);
    }-*/;
}