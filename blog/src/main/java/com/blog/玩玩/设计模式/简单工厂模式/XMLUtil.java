package com.blog.玩玩.设计模式.简单工厂模式;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLUtil {
    //该方法用于从XML配置文件中提取图表类型，并返回类型名
    /**
     * 获取图表类型的方法。
     * 该方法通过以下步骤从"config.xml"文件中提取图表类型：
     * 1. 使用DocumentBuilderFactory创建一个DocumentBuilder对象。
     * 2. 使用DocumentBuilder对象的parse方法，对"config.xml"文件进行解析，生成一个Document对象。
     * 3. 通过Document对象的getElementsByTagName方法，获取包含"chartType"的节点列表。
     * 4. 从节点列表中获取第一个节点，并获取该节点的第一个子节点。
     * 5. 从子节点中获取文本值，即图表类型，并将其返回。
     * 如果在上述过程中发生任何异常，该方法将返回null。
     *
     * @return 图表类型的字符串表示，或者如果无法获取则返回null。
     */
    public static String getChartType() {
        try {
            // 使用Java的反射机制创建DocumentBuilderFactory的实例，这个实例被称为dFactory
            // 创建文档对象，这个对象会被用于解析XML文件
            //createDocument对象
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            // 使用dFactory创建DocumentBuilder对象，用于解析XML文件
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            // 声明一个Document类型的变量doc，但没有初始化
            Document doc;
            // 使用DocumentBuilder的parse方法解析名为"config.xml"的文件，并将结果赋值给doc变量
            doc = builder.parse(new File("config.xml"));

            // 通过调用doc对象的getElementsByTagName方法，获取所有标签名为"chartType"的节点，结果保存在NodeList类型的变量nl中
            //获取包含图表类型的文本节点
            NodeList nl = doc.getElementsByTagName("chartType");
            // 从nl中获取第一个节点，并获取该节点的第一个子节点，结果保存在Node类型的变量classNode中
            Node classNode = nl.item(0).getFirstChild();
            // 获取classNode的节点值，并去除两端的空格，结果保存在String类型的变量chartType中
            String chartType = classNode.getNodeValue().trim();
            // 返回chartType
            return chartType;
        }
        // 如果在try块中的代码执行过程中发生异常，那么会进入catch块
        catch(Exception e) {
            // 打印异常的堆栈跟踪信息，帮助开发者定位问题
            e.printStackTrace();
            // 返回null，表示获取图表类型失败
            return null;
        }
    }
}