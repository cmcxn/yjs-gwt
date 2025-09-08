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
     * Callback interface for module loading completion.
     */
    public interface LoadCallback {
        void onLoad();
    }

    /**
     * Injects the required scripts for Yjs, Y-WebSocket, and Y-IndexedDB.
     * @param callback Callback to invoke when modules are loaded
     */
    public static void injectScripts(LoadCallback callback) {
        // Create a script element with type="module"
        Element head = Document.get().getHead();
        ScriptElement script = Document.get().createScriptElement();
        script.setType("module");
        
        // Define the ES module imports with callback support
        String moduleCode = 
            "import * as Y from 'https://esm.sh/yjs@13';\n" +
            "import { WebsocketProvider } from 'https://esm.sh/y-websocket@2';\n" +
            "import { IndexeddbPersistence } from 'https://esm.sh/y-indexeddb@9';\n\n" +
            
            // Expose the modules to the global scope so JSInterop can access them
            "window.Y = Y;\n" +
            "window.WebsocketProvider = WebsocketProvider;\n" +
            "window.IndexeddbPersistence = IndexeddbPersistence;\n\n" +
            
            // Mark as loaded and call the callback
            "window.yjsModulesLoaded = true;\n" +
            "if (window.yjsLoadCallback) {\n" +
            "  window.yjsLoadCallback();\n" +
            "}\n";
            
        script.setInnerText(moduleCode);
        
        // Set up the callback before adding the script
        setLoadCallback(callback);
        head.appendChild(script);
    }

    /**
     * Injects the required scripts for Yjs, Y-WebSocket, and Y-IndexedDB.
     * @deprecated Use injectScripts(LoadCallback) instead
     */
    @Deprecated
    public static void injectScripts() {
        injectScripts(null);
    }
    
    private static native void setLoadCallback(LoadCallback callback) /*-{
        if (callback) {
            $wnd.yjsLoadCallback = function() {
                callback.@org.chenmin.gwt.yjs.client.YjsLoader.LoadCallback::onLoad()();
            };
        }
    }-*/;
}