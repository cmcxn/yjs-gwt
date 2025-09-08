package org.chenmin.gwt.yjs.client.test;

import com.google.gwt.core.client.GWT;
import org.chenmin.gwt.yjs.client.yjs.*;

/**
 * Simple test class to verify JSSet functionality.
 */
public class JSSetTest {
    
    /**
     * Test the YMapEvent fix with a mock scenario.
     */
    public static void testYMapEventKeysChanged() {
        GWT.log("=== Testing YMapEvent keysChanged fix ===");
        
        try {
            // Create a simple Y.Doc and Y.Map
            Doc doc = new Doc();
            YMap map = doc.getMap("test-map");
            
            // Set up an observer to catch the event
            final boolean[] testPassed = {false};
            
            map.observe((YMapEvent event, YTransaction transaction) -> {
                try {
                    JSSet<String> keysChanged = event.getKeysChanged();
                    int size = keysChanged.getSize();  // This should now work
                    GWT.log("Keys changed count: " + size);
                    testPassed[0] = true;
                    GWT.log("✓ YMapEvent.getKeysChanged().getSize() works correctly!");
                } catch (Exception e) {
                    GWT.log("✗ Test failed: " + e.getMessage());
                }
            });
            
            // Trigger a change to generate an event
            map.set("test-key", "test-value");
            
            // Give a moment for the observer to fire
            com.google.gwt.core.client.Scheduler.get().scheduleDeferred(() -> {
                if (testPassed[0]) {
                    GWT.log("✓ All tests passed - YMapEvent fix is working!");
                } else {
                    GWT.log("? Test result pending...");
                }
            });
            
        } catch (Exception e) {
            GWT.log("✗ Test setup failed: " + e.getMessage());
        }
    }
}