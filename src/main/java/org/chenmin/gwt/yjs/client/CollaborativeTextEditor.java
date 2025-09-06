package org.chenmin.gwt.yjs.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.Scheduler;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import org.apache.tools.ant.input.InputHandler;
import org.chenmin.gwt.yjs.client.indexeddb.IndexeddbPersistence;
import org.chenmin.gwt.yjs.client.websocket.StatusEvent;
import org.chenmin.gwt.yjs.client.websocket.WebsocketProvider;
import org.chenmin.gwt.yjs.client.yjs.Doc;
import org.chenmin.gwt.yjs.client.yjs.Y;
import org.chenmin.gwt.yjs.client.yjs.YText;

/**
 * Main entry point for the collaborative text editor application.
 */
public class CollaborativeTextEditor implements EntryPoint {
    
    private Doc doc;
    private YText ytext;
    private WebsocketProvider provider;
    private IndexeddbPersistence idb;
    
    private TextArea textArea;
    private TextBox roomInput;
    private Label statusLabel;
    
    @Override
    public void onModuleLoad() {
        // First inject the Yjs scripts
        YjsLoader.injectScripts();
        
        // Wait a bit for scripts to load
        Scheduler.get().scheduleDeferred(() -> {
            createUI();
            initYjs("my-roomname");
        });
    }
    
    private void createUI() {
        VerticalPanel mainPanel = new VerticalPanel();
        mainPanel.setSpacing(10);
        
        // Title
        HTML title = new HTML("<h1>Yjs 协作文本编辑器 (GWT版)</h1>");
        mainPanel.add(title);
        
        // Room controls
        HorizontalPanel roomPanel = new HorizontalPanel();
        roomPanel.setSpacing(5);
        roomPanel.add(new Label("房间名："));
        
        roomInput = new TextBox();
        roomInput.setValue("my-roomname");
        roomPanel.add(roomInput);
        
        Button reconnectBtn = new Button("连接/切换房间");
        reconnectBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                String roomName = roomInput.getValue().trim();
                if (roomName.isEmpty()) {
                    roomName = "my-roomname";
                }
                connect(roomName);
            }
        });
        roomPanel.add(reconnectBtn);
        
        statusLabel = new Label("未连接");
        roomPanel.add(statusLabel);
        mainPanel.add(roomPanel);
        
        // Text area
        textArea = new TextArea();
        textArea.setVisibleLines(15);
        textArea.setCharacterWidth(80);
        textArea.getElement().setAttribute("placeholder", "打开多个本地页面（同一房间）即可看到全量覆盖同步。");
        mainPanel.add(textArea);
        
        // Server info
        HTML serverInfo = new HTML("<p>服务器地址：<code>ws://127.0.0.1:3001/collaboration</code>（请自行在本机启动 y-websocket 服务）</p>");
        mainPanel.add(serverInfo);
        
        RootPanel.get().add(mainPanel);
    }
    
    private void initYjs(String roomName) {
        connect(roomName);
        textArea.addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                Y.transact(doc, () -> {
                    ytext.delete(0, ytext.length());
                    ytext.insert(0, textArea.getValue());
                });
            }
        });
    }
    
    private void connect(String roomName) {
        // Clean up previous instances
        if (provider != null) {
            provider.destroy();
        }
        if (doc != null) {
            doc.destroy();
        }
        
        // Create new document
        doc = new Doc();
        ytext = doc.getText("shared");
        
        // Set up IndexedDB persistence
        idb = new IndexeddbPersistence(roomName, doc);
        idb.on("synced", () -> {
            String value = ytext.toString();
            if (!value.equals(textArea.getValue())) {
                textArea.setValue(value);
            }
            // Simulate an input event to ensure initial sync
//            textArea.fireEvent(new InputEvent() {});
        });
        
        // Set up WebSocket provider
        provider = new WebsocketProvider(
            "ws://127.0.0.1:3001/collaboration",
            roomName,
            doc
        );
        provider.on("status", (StatusEvent event) -> {
            statusLabel.setText("状态：" + event.getStatus());
        });
        
        // Set up text observation
        ytext.observe((event) -> {
            String incoming = ytext.toString();
            if (!incoming.equals(textArea.getValue())) {
                textArea.setValue(incoming);
            }
        });
    }
}