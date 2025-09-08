package org.chenmin.gwt.yjs.client.test;

import com.google.gwt.core.client.GWT;
import org.chenmin.gwt.yjs.client.yjs.*;

/**
 * Example demonstrating the usage of Y.js JSInterop wrappers.
 */
public class YjsExamples {

    public static void demonstrateDocUsage() {
        GWT.log("=== Y.Doc Example ===");
        
        // Create a new Y.Doc
        Doc doc = new Doc();
        GWT.log("Created new Y.Doc with clientID: " + doc.getClientID());
        
        // Set garbage collection
        doc.setGc(false);
        GWT.log("Garbage collection enabled: " + doc.getGc());
        
        // Get shared types
        YText text = doc.getText("shared-text");
        YMap map = doc.getMap("shared-map");
        YArray array = doc.getArray("shared-array");
        
        GWT.log("Created shared types: text, map, array");
    }
    
    public static void demonstrateTextUsage() {
        GWT.log("=== Y.Text Example ===");
        
        Doc doc = new Doc();
        YText text = doc.getText("example-text");
        
        // Insert text
        text.insert(0, "Hello World!");
        GWT.log("Text content: " + text.toString());
        GWT.log("Text length: " + text.getLength());
        
        // Format text
        Object boldFormat = createFormatObject("bold", true);
        text.format(0, 5, boldFormat);
        GWT.log("Applied bold formatting to 'Hello'");
        
        // Observe changes
        text.observe((YTextEvent event, YTransaction transaction) -> {
            GWT.log("Text changed: " + text.toString());
        });
        
        // Delete and insert
        text.delete(6, 6);
        text.insert(6, "GWT!");
        GWT.log("Final text: " + text.toString());
    }
    
    public static void demonstrateMapUsage() {
        GWT.log("=== Y.Map Example ===");
        
        Doc doc = new Doc();
        YMap map = doc.getMap("example-map");
        
        // Set values
        map.set("name", "John Doe");
        map.set("age", Double.valueOf(30));
        map.set("active", true);
        
        GWT.log("Map size: " + map.getSize());
        GWT.log("Name: " + map.get("name"));
        GWT.log("Age: " + map.get("age"));
        GWT.log("Has 'email': " + map.has("email"));
        
        // Observe changes
        map.observe((YMapEvent event, YTransaction transaction) -> {
            GWT.log("Map changed, keys affected: " + event.getKeysChanged().getSize());
        });
        
        // Update and delete
        map.set("age", Double.valueOf(31));
        map.delete("active");
        GWT.log("Updated map, final size: " + map.getSize());
    }
    
    public static void demonstrateArrayUsage() {
        GWT.log("=== Y.Array Example ===");
        
        Doc doc = new Doc();
        YArray array = doc.getArray("example-array");
        
        // Insert elements
        array.insert(0, new Object[]{"first", "second", "third"});
        GWT.log("Array length: " + array.getLength());
        
        // Push and unshift
        array.push(new Object[]{"fourth"});
        array.unshift(new Object[]{"zero"});
        GWT.log("After push/unshift, length: " + array.getLength());
        
        // Get elements
        GWT.log("First element: " + array.get(0));
        GWT.log("Last element: " + array.get(array.getLength() - 1));
        
        // Observe changes
        array.observe((YArrayEvent event, YTransaction transaction) -> {
            GWT.log("Array changed, new length: " + array.getLength());
        });
        
        // Delete elements
        array.delete(1, 2);
        GWT.log("After deletion, length: " + array.getLength());
    }
    
    public static void demonstrateUndoManagerUsage() {
        GWT.log("=== Y.UndoManager Example ===");
        
        Doc doc = new Doc();
        YText text = doc.getText("undo-text");
        
        // Create undo manager
        YUndoManager undoManager = new YUndoManager(text);
        
        // Make some changes
        text.insert(0, "Hello");
        text.insert(5, " World");
        
        GWT.log("Text after changes: " + text.toString());
        
        // Undo last change
        undoManager.undo();
        GWT.log("After undo: " + text.toString());
        
        // Redo
        undoManager.redo();
        GWT.log("After redo: " + text.toString());
        
        // Listen to undo manager events
        undoManager.on("stack-item-added", (YUndoManagerEvent event, YUndoManager manager) -> {
            GWT.log("Stack item added: " + event.getType());
        });
    }
    
    public static void demonstrateTransactionUsage() {
        GWT.log("=== Transaction Example ===");
        
        Doc doc = new Doc();
        YText text = doc.getText("transaction-text");
        YMap map = doc.getMap("transaction-map");
        
        // Perform multiple operations in a single transaction
        doc.transact(() -> {
            text.insert(0, "Batched operations");
            map.set("operation", "batch");
            map.set("timestamp", Double.valueOf(System.currentTimeMillis()));
        });
        
        GWT.log("Transaction completed - Text: " + text.toString());
        GWT.log("Map operation: " + map.get("operation"));
    }
    
    // Helper method to create format objects (simplified for example)
    private static native Object createFormatObject(String key, Object value) /*-{
        var obj = {};
        obj[key] = value;
        return obj;
    }-*/;
}