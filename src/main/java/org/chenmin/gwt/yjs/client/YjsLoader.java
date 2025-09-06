package org.chenmin.gwt.yjs.client;

import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.ScriptElement;

/**
 * Utility to load Yjs libraries.
 */
public class YjsLoader {

    /**
     * Injects the required scripts for Yjs, Y-WebSocket, and Y-IndexedDB.
     */
    public static void injectScripts() {
        // Create a script element with type="module"
        Element head = Document.get().getHead();
        ScriptElement script = Document.get().createScriptElement();
        script.setType("module");
        
        // Define the ES module imports
        String moduleCode = 
            "import * as Y from 'https://esm.sh/yjs@13';\n" +
            "import { WebsocketProvider } from 'https://esm.sh/y-websocket@2';\n" +
            "import { IndexeddbPersistence } from 'https://esm.sh/y-indexeddb@9';\n\n" +
            
            // Expose the modules to the global scope so JSInterop can access them
            "window.Y = Y;\n" +
            "window.WebsocketProvider = WebsocketProvider;\n" +
            "window.IndexeddbPersistence = IndexeddbPersistence;\n";
            
        script.setInnerText(moduleCode);
        head.appendChild(script);
    }
}