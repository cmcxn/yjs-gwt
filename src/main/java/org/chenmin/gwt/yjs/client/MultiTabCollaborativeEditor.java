package org.chenmin.gwt.yjs.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import org.chenmin.gwt.yjs.client.websocket.StatusEvent;
import org.chenmin.gwt.yjs.client.websocket.WebsocketProvider;
import org.chenmin.gwt.yjs.client.indexeddb.IndexeddbPersistence;
import org.chenmin.gwt.yjs.client.yjs.Doc;

/**
 * Multi-tab collaborative editor for Y.Map and Y.Array demonstration.
 * Shows real-time synchronization across multiple tabs.
 */
public class MultiTabCollaborativeEditor implements EntryPoint {
    
    private Doc doc;
    private WebsocketProvider provider;
    private IndexeddbPersistence idb;
    
    private TextBox roomInput;
    private TextBox serverInput;
    private Label statusLabel;
    private TabPanel mainTabPanel;
    
    @Override
    public void onModuleLoad() {
        createUI();
        
        // Inject Yjs scripts and initialize
        YjsLoader.injectScripts(() -> {
            initYjs("collaborative-demo");
        });
    }
    
    private void createUI() {
        VerticalPanel mainPanel = new VerticalPanel();
        mainPanel.setSpacing(10);
        
        // Title
        HTML title = new HTML("<h1>多标签页协作编辑器 - Y.Map & Y.Array 演示</h1>");
        title.getElement().getStyle().setColor("#2c3e50");
        mainPanel.add(title);
        
        // Connection controls
        createConnectionControls(mainPanel);
        
        // Main tab panel for multiple editors
        mainTabPanel = new TabPanel();
        mainTabPanel.setSize("100%", "600px");
        
        // Add initial tabs
        addNewTab();
        addNewTab();
        
        mainPanel.add(mainTabPanel);
        
        RootPanel.get().add(mainPanel);
        
        // Select first tab
        mainTabPanel.selectTab(0);
    }
    
    private void createConnectionControls(VerticalPanel mainPanel) {
        HorizontalPanel controlPanel = new HorizontalPanel();
        controlPanel.setSpacing(10);
        
        controlPanel.add(new Label("房间名："));
        roomInput = new TextBox();
        roomInput.setValue("collaborative-demo");
        controlPanel.add(roomInput);
        
        Button connectBtn = new Button("连接/切换房间");
        connectBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                String roomName = roomInput.getValue().trim();
                if (roomName.isEmpty()) {
                    roomName = "collaborative-demo";
                }
                connect(roomName);
            }
        });
        controlPanel.add(connectBtn);
        
        Button addTabBtn = new Button("添加新标签页");
        addTabBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                addNewTab();
            }
        });
        controlPanel.add(addTabBtn);
        
        statusLabel = new Label("未连接");
        statusLabel.getElement().getStyle().setFontWeight(com.google.gwt.dom.client.Style.FontWeight.BOLD);
        statusLabel.getElement().getStyle().setColor("#e74c3c");
        controlPanel.add(statusLabel);
        
        mainPanel.add(controlPanel);
        
        // Server input
        HorizontalPanel serverPanel = new HorizontalPanel();
        serverPanel.setSpacing(5);
        serverPanel.add(new Label("服务器地址："));
        
        serverInput = new TextBox();
        serverInput.setValue("ws://127.0.0.1:3001/collaboration");
        serverInput.setWidth("350px");
        serverPanel.add(serverInput);
        
        mainPanel.add(serverPanel);
    }
    
    private void addNewTab() {
        int tabIndex = mainTabPanel.getTabBar().getTabCount() + 1;
        String tabTitle = "编辑器 " + tabIndex;
        
        CollaborativeEditorTab editorTab = new CollaborativeEditorTab(doc, tabIndex);
        mainTabPanel.add(editorTab, tabTitle);
        
        // Select the newly added tab
        mainTabPanel.selectTab(mainTabPanel.getTabBar().getTabCount() - 1);
    }
    
    private void initYjs(String roomName) {
        connect(roomName);
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
        
        // Update all existing tabs with new document
        updateAllTabsWithNewDoc();
        
        // Set up IndexedDB persistence
        idb = new IndexeddbPersistence(roomName, doc);
        idb.on("synced", () -> {
            GWT.log("IndexedDB synced for room: " + roomName);
        });
        
        // Set up WebSocket provider
        String wsUrl = serverInput != null ? serverInput.getValue().trim() : "ws://127.0.0.1:3001/collaboration";
        provider = new WebsocketProvider(wsUrl, roomName, doc);
        provider.on("status", (StatusEvent event) -> {
            String status = event.getStatus();
            statusLabel.setText("状态：" + status);
            
            if ("connected".equals(status)) {
                statusLabel.getElement().getStyle().setColor("#27ae60");
            } else if ("disconnected".equals(status)) {
                statusLabel.getElement().getStyle().setColor("#e74c3c");
            } else {
                statusLabel.getElement().getStyle().setColor("#f39c12");
            }
        });
        
        GWT.log("Connected to room: " + roomName);
    }
    
    private void updateAllTabsWithNewDoc() {
        int tabCount = mainTabPanel.getTabBar().getTabCount();
        for (int i = 0; i < tabCount; i++) {
            CollaborativeEditorTab tab = (CollaborativeEditorTab) mainTabPanel.getWidget(i);
            tab.setDocument(doc);
        }
    }
}