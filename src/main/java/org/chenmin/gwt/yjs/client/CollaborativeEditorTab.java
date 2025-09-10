package org.chenmin.gwt.yjs.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import org.chenmin.gwt.yjs.client.yjs.Doc;
import org.chenmin.gwt.yjs.client.yjs.Y;
import org.chenmin.gwt.yjs.client.yjs.YArray;
import org.chenmin.gwt.yjs.client.yjs.YArrayEvent;
import org.chenmin.gwt.yjs.client.yjs.YMap;
import org.chenmin.gwt.yjs.client.yjs.YMapEvent;
import org.chenmin.gwt.yjs.client.yjs.YTransaction;

/**
 * A collaborative editor tab that contains both Y.Map and Y.Array editors.
 * Shows real-time synchronization of shared data structures.
 */
public class CollaborativeEditorTab extends Composite {
    
    private Doc doc;
    private YMap ymap;
    private YArray yarray;
    private int tabIndex;
    
    // Y.Map UI components
    private TextBox mapKeyInput;
    private TextBox mapValueInput;
    private FlexTable mapTable;
    private Label mapStatusLabel;
    
    // Y.Array UI components
    private TextBox arrayValueInput;
    private FlexTable arrayTable;
    private Label arrayStatusLabel;
    
    private VerticalPanel mainPanel;
    
    public CollaborativeEditorTab(Doc document, int tabIndex) {
        this.tabIndex = tabIndex;
        setDocument(document);
        createUI();
        initWidget(mainPanel);
    }
    
    public void setDocument(Doc document) {
        this.doc = document;
        if (doc != null) {
            setupYjsTypes();
        }
    }
    
    private void setupYjsTypes() {
        // Initialize Y.Map and Y.Array with unique names for this demo
        ymap = doc.getMap("shared-map");
        yarray = doc.getArray("shared-array");
        
        // Set up observers for real-time updates
        ymap.observe((YMapEvent event, YTransaction transaction) -> {
            GWT.log("Tab " + tabIndex + ": Y.Map changed");
            updateMapDisplay();
            updateMapStatus();
        });
        
        yarray.observe((YArrayEvent event, YTransaction transaction) -> {
            GWT.log("Tab " + tabIndex + ": Y.Array changed");
            updateArrayDisplay();
            updateArrayStatus();
        });
        
        // Initial display update
        updateMapDisplay();
        updateArrayDisplay();
        updateMapStatus();
        updateArrayStatus();
    }
    
    private void createUI() {
        mainPanel = new VerticalPanel();
        mainPanel.setSpacing(15);
        mainPanel.setWidth("100%");
        
        // Tab header
        HTML tabHeader = new HTML("<h3>标签页 " + tabIndex + " - 协作编辑器</h3>");
        tabHeader.getElement().getStyle().setColor("#34495e");
        tabHeader.getElement().getStyle().setMarginBottom(10, com.google.gwt.dom.client.Style.Unit.PX);
        mainPanel.add(tabHeader);
        
        // Create Y.Map editor section
        createMapEditor();
        
        // Add separator
        HTML separator = new HTML("<hr style='margin: 20px 0; border: 1px solid #bdc3c7;'>");
        mainPanel.add(separator);
        
        // Create Y.Array editor section
        createArrayEditor();
    }
    
    private void createMapEditor() {
        VerticalPanel mapPanel = new VerticalPanel();
        mapPanel.setSpacing(10);
        
        // Section title
        HTML mapTitle = new HTML("<h4>📊 Y.Map 键值对编辑器</h4>");
        mapTitle.getElement().getStyle().setColor("#2980b9");
        mapPanel.add(mapTitle);
        
        // Input controls
        HorizontalPanel mapInputPanel = new HorizontalPanel();
        mapInputPanel.setSpacing(5);
        
        mapInputPanel.add(new Label("键:"));
        mapKeyInput = new TextBox();
        mapKeyInput.setWidth("120px");
        mapInputPanel.add(mapKeyInput);
        
        mapInputPanel.add(new Label("值:"));
        mapValueInput = new TextBox();
        mapValueInput.setWidth("150px");
        mapInputPanel.add(mapValueInput);
        
        Button addMapBtn = new Button("添加/更新");
        addMapBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                addOrUpdateMapEntry();
            }
        });
        mapInputPanel.add(addMapBtn);
        
        // Allow Enter key to add entries
        KeyUpHandler enterHandler = new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                if (event.getNativeKeyCode() == 13) { // Enter key
                    addOrUpdateMapEntry();
                }
            }
        };
        mapKeyInput.addKeyUpHandler(enterHandler);
        mapValueInput.addKeyUpHandler(enterHandler);
        
        mapPanel.add(mapInputPanel);
        
        // Status label
        mapStatusLabel = new Label("Y.Map 大小: 0");
        mapStatusLabel.getElement().getStyle().setFontWeight(com.google.gwt.dom.client.Style.FontWeight.BOLD);
        mapStatusLabel.getElement().getStyle().setColor("#27ae60");
        mapPanel.add(mapStatusLabel);
        
        // Map display table
        mapTable = new FlexTable();
        mapTable.setStyleName("demo-table");
        mapTable.setBorderWidth(1);
        mapTable.setCellSpacing(0);
        mapTable.setCellPadding(5);
        mapTable.setText(0, 0, "键");
        mapTable.setText(0, 1, "值");
        mapTable.setText(0, 2, "操作");
        mapTable.getRowFormatter().addStyleName(0, "table-header");
        mapPanel.add(mapTable);
        
        mainPanel.add(mapPanel);
    }
    
    private void createArrayEditor() {
        VerticalPanel arrayPanel = new VerticalPanel();
        arrayPanel.setSpacing(10);
        
        // Section title
        HTML arrayTitle = new HTML("<h4>📋 Y.Array 数组编辑器</h4>");
        arrayTitle.getElement().getStyle().setColor("#8e44ad");
        arrayPanel.add(arrayTitle);
        
        // Input controls
        HorizontalPanel arrayInputPanel = new HorizontalPanel();
        arrayInputPanel.setSpacing(5);
        
        arrayInputPanel.add(new Label("新元素:"));
        arrayValueInput = new TextBox();
        arrayValueInput.setWidth("200px");
        arrayInputPanel.add(arrayValueInput);
        
        Button pushBtn = new Button("末尾添加");
        pushBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                pushArrayElement();
            }
        });
        arrayInputPanel.add(pushBtn);
        
        Button unshiftBtn = new Button("开头添加");
        unshiftBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                unshiftArrayElement();
            }
        });
        arrayInputPanel.add(unshiftBtn);
        
        // Allow Enter key to push elements
        arrayValueInput.addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                if (event.getNativeKeyCode() == 13) { // Enter key
                    pushArrayElement();
                }
            }
        });
        
        arrayPanel.add(arrayInputPanel);
        
        // Status label
        arrayStatusLabel = new Label("Y.Array 长度: 0");
        arrayStatusLabel.getElement().getStyle().setFontWeight(com.google.gwt.dom.client.Style.FontWeight.BOLD);
        arrayStatusLabel.getElement().getStyle().setColor("#e67e22");
        arrayPanel.add(arrayStatusLabel);
        
        // Array display table
        arrayTable = new FlexTable();
        arrayTable.setStyleName("demo-table");
        arrayTable.setBorderWidth(1);
        arrayTable.setCellSpacing(0);
        arrayTable.setCellPadding(5);
        arrayTable.setText(0, 0, "索引");
        arrayTable.setText(0, 1, "值");
        arrayTable.setText(0, 2, "操作");
        arrayTable.getRowFormatter().addStyleName(0, "table-header");
        arrayPanel.add(arrayTable);
        
        mainPanel.add(arrayPanel);
    }
    
    private void addOrUpdateMapEntry() {
        if (ymap == null) return;
        
        String key = mapKeyInput.getValue().trim();
        String value = mapValueInput.getValue().trim();
        
        if (key.isEmpty()) {
            GWT.log("Tab " + tabIndex + ": Key cannot be empty");
            return;
        }
        
        Y.transact(doc, () -> {
            ymap.set(key, value);
        });
        
        // Clear inputs
        mapKeyInput.setValue("");
        mapValueInput.setValue("");
        mapKeyInput.setFocus(true);
        
        GWT.log("Tab " + tabIndex + ": Added/Updated map entry: " + key + " = " + value);
    }
    
    private void pushArrayElement() {
        if (yarray == null) return;
        
        String value = arrayValueInput.getValue().trim();
        if (value.isEmpty()) {
            GWT.log("Tab " + tabIndex + ": Value cannot be empty");
            return;
        }
        
        Y.transact(doc, () -> {
            yarray.push(new Object[]{value});
        });
        
        arrayValueInput.setValue("");
        arrayValueInput.setFocus(true);
        
        GWT.log("Tab " + tabIndex + ": Pushed array element: " + value);
    }
    
    private void unshiftArrayElement() {
        if (yarray == null) return;
        
        String value = arrayValueInput.getValue().trim();
        if (value.isEmpty()) {
            GWT.log("Tab " + tabIndex + ": Value cannot be empty");
            return;
        }
        
        Y.transact(doc, () -> {
            yarray.unshift(new Object[]{value});
        });
        
        arrayValueInput.setValue("");
        arrayValueInput.setFocus(true);
        
        GWT.log("Tab " + tabIndex + ": Unshifted array element: " + value);
    }
    
    private void updateMapDisplay() {
        if (ymap == null || mapTable == null) return;
        
        // Clear existing rows (except header)
        while (mapTable.getRowCount() > 1) {
            mapTable.removeRow(1);
        }
        
        // Add current map entries
        ymap.forEach((Object value, String key, YMap map) -> {
            int row = mapTable.getRowCount();
            mapTable.setText(row, 0, key);
            mapTable.setText(row, 1, String.valueOf(value));
            
            Button deleteBtn = new Button("删除");
            deleteBtn.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    Y.transact(doc, () -> {
                        ymap.delete(key);
                    });
                    GWT.log("Tab " + tabIndex + ": Deleted map key: " + key);
                }
            });
            mapTable.setWidget(row, 2, deleteBtn);
        });
    }
    
    private void updateArrayDisplay() {
        if (yarray == null || arrayTable == null) return;
        
        // Clear existing rows (except header)
        while (arrayTable.getRowCount() > 1) {
            arrayTable.removeRow(1);
        }
        
        // Add current array elements
        for (int i = 0; i < yarray.getLength(); i++) {
            final int index = i;
            int row = arrayTable.getRowCount();
            arrayTable.setText(row, 0, String.valueOf(index));
            arrayTable.setText(row, 1, String.valueOf(yarray.get(index)));
            
            Button deleteBtn = new Button("删除");
            deleteBtn.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    Y.transact(doc, () -> {
                        yarray.delete(index, 1);
                    });
                    GWT.log("Tab " + tabIndex + ": Deleted array element at index: " + index);
                }
            });
            arrayTable.setWidget(row, 2, deleteBtn);
        }
    }
    
    private void updateMapStatus() {
        if (ymap != null && mapStatusLabel != null) {
            mapStatusLabel.setText("Y.Map 大小: " + ymap.getSize());
        }
    }
    
    private void updateArrayStatus() {
        if (yarray != null && arrayStatusLabel != null) {
            arrayStatusLabel.setText("Y.Array 长度: " + yarray.getLength());
        }
    }
}