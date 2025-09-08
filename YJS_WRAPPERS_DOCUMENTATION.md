# Y.js JSInterop Wrappers for GWT

This project provides comprehensive JSInterop wrappers for the Y.js collaborative editing library, allowing you to use Y.js features in GWT applications.

## Overview

The wrappers provide type-safe Java interfaces for all major Y.js types:

- **Y.Doc** - The shared document that contains shared data types
- **Y.Map** - A shared type with Map-like API for key-value data
- **Y.Array** - A shared type for sequence-like data structures
- **Y.Text** - A shared text type for collaborative text editing
- **Y.UndoManager** - Selective undo/redo functionality
- **Y.Event** - Event system for observing changes

## Core Types

### Y.Doc

The main document that holds all shared types:

```java
// Create a new document
Doc doc = new Doc();

// Get document properties
double clientId = doc.getClientID();
boolean gcEnabled = doc.getGc();

// Configure garbage collection
doc.setGc(false);

// Get shared types
YText text = doc.getText("my-text");
YMap map = doc.getMap("my-map");
YArray array = doc.getArray("my-array");

// Execute operations in transactions
doc.transact(() -> {
    text.insert(0, "Hello");
    map.set("key", "value");
    array.insert(0, new Object[]{"item1", "item2"});
});

// Listen to document events
doc.on("update", (Object... args) -> {
    // Handle update events
});
```

### Y.Text

Collaborative text editing with rich text support:

```java
YText text = doc.getText("shared-text");

// Basic text operations
text.insert(0, "Hello World!");
text.delete(6, 6);  // Delete "World!"
text.insert(6, "GWT!");

// Rich text formatting
Object boldFormat = createFormatObject("bold", true);
text.format(0, 5, boldFormat);  // Make "Hello" bold

// Get text content
String content = text.toString();
int length = text.getLength();

// Convert to/from delta format
YDelta[] delta = text.toDelta();
text.applyDelta(delta);

// Observe changes
text.observe((YTextEvent event, YTransaction transaction) -> {
    // Handle text changes
    YDelta[] changes = event.getDelta();
});
```

### Y.Map

Key-value shared data structure:

```java
YMap map = doc.getMap("shared-map");

// Basic operations
map.set("name", "John Doe");
map.set("age", 30);
map.set("active", true);

// Get values
Object name = map.get("name");
boolean hasEmail = map.has("email");
int size = map.getSize();

// Iteration
map.forEach((Object value, String key, YMap m) -> {
    System.out.println(key + ": " + value);
});

// Conversion
Object json = map.toJSON();

// Observe changes
map.observe((YMapEvent event, YTransaction transaction) -> {
    Set<String> changedKeys = event.getKeysChanged();
    Map<String, YMapKeyChange> changes = event.getChanges().getKeys();
    
    changes.forEach((key, change) -> {
        String action = change.getAction(); // "add", "update", "delete"
        Object oldValue = change.getOldValue();
    });
});
```

### Y.Array

Shared array for sequential data:

```java
YArray array = doc.getArray("shared-array");

// Insert elements
array.insert(0, new Object[]{"first", "second", "third"});
array.push(new Object[]{"fourth"});
array.unshift(new Object[]{"zero"});

// Access elements
Object first = array.get(0);
int length = array.getLength();
Object[] slice = array.slice(1, 3);

// Modify
array.delete(1, 2);  // Delete 2 elements starting at index 1

// Conversion
Object[] asArray = array.toArray();
Object[] asJson = array.toJSON();

// Functional operations
array.forEach((Object value, int index, YArray arr) -> {
    System.out.println(index + ": " + value);
});

// Observe changes
array.observe((YArrayEvent event, YTransaction transaction) -> {
    YDelta[] delta = event.getChanges().getDelta();
    // Process delta changes
});
```

### Y.UndoManager

Selective undo/redo functionality:

```java
YText text = doc.getText("text");
YUndoManager undoManager = new YUndoManager(text);

// Configure options
YUndoManagerOptions options = new YUndoManagerOptions();
options.captureTimeout = 500;  // Merge operations within 500ms
YUndoManager undoManager2 = new YUndoManager(text, options);

// Use undo/redo
text.insert(0, "Hello");
text.insert(5, " World");

undoManager.undo();  // Undo last change
undoManager.redo();  // Redo last undone change

// Prevent merging
undoManager.stopCapturing();

// Clear history
undoManager.clear();

// Listen to events
undoManager.on("stack-item-added", (YUndoManagerEvent event, YUndoManager manager) -> {
    String type = event.getType(); // "undo" or "redo"
    YStackItem item = event.getStackItem();
    
    // Add meta information
    item.getMeta().put("cursor", getCurrentCursorPosition());
});
```

## Event System

All Y types support observing changes:

### Basic Observation

```java
// Observe specific type changes
text.observe((YTextEvent event, YTransaction transaction) -> {
    // Handle text-specific changes
});

map.observe((YMapEvent event, YTransaction transaction) -> {
    // Handle map-specific changes
});

array.observe((YArrayEvent event, YTransaction transaction) -> {
    // Handle array-specific changes
});
```

### Deep Observation

```java
// Observe changes to a type and all its nested types
map.observeDeep((YEvent[] events, YTransaction transaction) -> {
    for (YEvent event : events) {
        YAbstractType target = event.getTarget();
        String[] path = event.getPath();
        // Handle nested changes
    }
});
```

### Document Events

```java
doc.on("beforeTransaction", (Object... args) -> {
    // Called before each transaction
});

doc.on("afterTransaction", (Object... args) -> {
    // Called after each transaction
});

doc.on("update", (Object... args) -> {
    // Called when document is updated
    // args[0] is Uint8Array update
    // args[1] is origin
});
```

## Integration with Existing GWT Code

The wrappers are designed to integrate seamlessly with existing GWT applications:

```java
public class CollaborativeTextEditor implements EntryPoint {
    private Doc doc;
    private YText ytext;
    private TextArea textArea;
    
    @Override
    public void onModuleLoad() {
        // Initialize Y.js document
        doc = new Doc();
        ytext = doc.getText("shared-text");
        
        // Create UI
        textArea = new TextArea();
        textArea.setSize("400px", "300px");
        
        // Sync Y.Text with TextArea
        ytext.observe((YTextEvent event, YTransaction transaction) -> {
            String content = ytext.toString();
            if (!content.equals(textArea.getValue())) {
                textArea.setValue(content);
            }
        });
        
        // Sync TextArea with Y.Text
        textArea.addKeyUpHandler(event -> {
            String content = textArea.getValue();
            if (!content.equals(ytext.toString())) {
                // Update Y.Text (simplified - would need proper diffing)
                ytext.delete(0, ytext.getLength());
                ytext.insert(0, content);
            }
        });
        
        RootPanel.get().add(textArea);
    }
}
```

## Best Practices

1. **Use Transactions**: Group related operations in transactions for better performance and atomicity.

2. **Proper Event Handling**: Always check if changes originated from your own operations to avoid infinite loops.

3. **Memory Management**: Unregister observers when components are destroyed.

4. **Type Safety**: While the wrappers use `Object` for flexibility, cast to appropriate types when needed.

5. **Error Handling**: Wrap Y.js operations in try-catch blocks as needed.

## Example: Complete Collaborative Editor

See `YjsExamples.java` for comprehensive usage examples of all wrapper features.

## Compatibility

- Requires GWT 2.8+ with JSInterop support
- Compatible with Y.js v13+
- Uses standard Java types (no jsinterop.base dependency required)
- Supports all major Y.js features and APIs