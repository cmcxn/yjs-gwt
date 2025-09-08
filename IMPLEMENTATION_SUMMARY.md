# Y.Xml* 和 Y.RelativePosition JSInterop 封装实现文档

本文档描述了为 GWT 项目实现的 Y.XmlFragment、Y.XmlElement、Y.XmlText 和 Y.RelativePosition 的 JSInterop 封装。

## 实现的类

### 1. Y.XmlFragment (YXmlFragment.java)
共享 XML 片段类型，用于管理 Y.Xml* 节点的集合。

**属性:**
- `getDoc()` - 绑定的 Yjs 文档
- `getParent()` - 父类型
- `getFirstChild()` - 第一个子节点
- `getLength()` - 子元素数量

**方法:**
- `insert(index, content[])` - 在指定位置插入内容
- `insertAfter(ref, content[])` - 在参考元素后插入
- `delete(index, length)` - 删除元素
- `push(content[])` / `unshift(content[])` - 末尾/开头添加
- `get(index)` - 获取指定位置元素
- `slice(start, end)` - 获取子集
- `toJSON()` - JSON 表示
- `clone()` - 克隆
- `toDOM()` - 转换为 DOM 元素
- `observe()` / `unobserve()` - 观察器

### 2. Y.XmlElement (YXmlElement.java)
表示 XML 节点的共享类型，继承自 YXmlFragment。

**构造器:**
- `new YXmlElement(nodeName)` - 使用节点名创建

**属性:**
- `getNodeName()` - 节点名称
- `getPrevSibling()` / `getNextSibling()` - 兄弟节点

**方法:**
- `setAttribute(name, value)` - 设置属性
- `removeAttribute(name)` - 删除属性
- `getAttribute(name)` - 获取属性
- `getAttributes()` - 获取所有属性
- `toString()` - XML 字符串表示

### 3. Y.XmlText (YXmlText.java)
扩展 Y.Text 表示 XML 文本节点。

**构造器:**
- `new YXmlText()` - 空文本
- `new YXmlText(content)` - 带初始内容

**属性:**
- `getPrevSibling()` / `getNextSibling()` - 兄弟节点

**方法:**
- `toString()` - XML 格式字符串（包含格式化标签）

### 4. Y.RelativePosition (YRelativePosition.java)
强大的位置编码，可转换为索引位置，不受远程更改影响。

**属性:**
- `type` - 关联的类型
- `index` - 索引位置
- `assoc` - 关联值

### 5. Y 类静态方法 (Y.java)
RelativePosition 相关的静态方法：

**方法:**
- `createRelativePositionFromTypeIndex(type, index [, assoc])` - 创建相对位置
- `createAbsolutePositionFromRelativePosition(relPos, doc)` - 转换为绝对位置
- `encodeRelativePosition(relPos)` - 编码为二进制
- `decodeRelativePosition(uint8Array)` - 从二进制解码

### 6. 支持类
- **YXmlObserver** - XML 变更观察器接口
- **YXmlEvent** - XML 变更事件
- **YXmlChanges** - XML 变更详情
- **YAbsolutePosition** - 绝对位置结果

## 使用示例

### 基本 XML 结构创建
```java
Doc doc = new Doc();
YXmlFragment fragment = doc.getXmlFragment("document");

// 创建元素
YXmlElement div = new YXmlElement("div");
div.setAttribute("class", "container");

YXmlText text = new YXmlText("Hello World");
div.insert(0, new Object[]{text});

fragment.insert(0, new Object[]{div});

// 获取 JSON 表示
String json = fragment.toJSON();
```

### 相对位置使用
```java
YText text = doc.getText("mytext");
text.insert(0, "Hello World");

// 创建相对位置
YRelativePosition relPos = Y.createRelativePositionFromTypeIndex(text, 5);

// 插入文本后位置仍然有效
text.insert(0, "Hi ");

// 转换回绝对位置
YAbsolutePosition absPos = Y.createAbsolutePositionFromRelativePosition(relPos, doc);
// absPos.index 现在是 8 (5 + 3)
```

### 观察器使用
```java
xmlFragment.observe((YXmlEvent event, YTransaction transaction) -> {
    GWT.log("XML 内容发生变化");
    // 处理变更
});
```

## 测试验证

完整的测试示例在 `YjsXmlExamples.java` 中，包含：

1. **demonstrateXmlFragmentUsage()** - XmlFragment 所有功能
2. **demonstrateXmlElementUsage()** - XmlElement 属性和方法
3. **demonstrateXmlTextUsage()** - XmlText 格式化功能
4. **demonstrateRelativePositionUsage()** - 相对位置编码/解码
5. **demonstrateCombinedXmlUsage()** - 复杂 XML 结构

在 CollaborativeTextEditor 中集成了一个"运行 XML 示例"按钮来执行这些测试。

## 编译验证

所有代码都已通过：
- ✅ Maven 编译 (`mvn compile`)
- ✅ GWT 编译 (`mvn gwt:compile`) 
- ✅ JSInterop 类型检查

这些封装提供了完整的 Y.js XML 和相对位置功能，完全符合 GWT JSInterop 规范。