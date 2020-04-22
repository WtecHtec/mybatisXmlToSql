package com.utils;

import com.alibaba.fastjson.JSONObject;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConvertTool {

    public static  List<JSONObject> start(String path) throws DocumentException {
        // 创建saxReader对象  
        SAXReader reader = new SAXReader();
        // 通过read方法读取一个文件 转换成Document对象  
        Document document = reader.read(new File(path));
        //获取根节点元素对象  
        Element node = document.getRootElement();
        List<JSONObject> jsonObject = new ArrayList<>();
        //读取dom4j.xml文件中所有元素
        listNodes(node,jsonObject);
        System.out.println(jsonObject.toString());
        return  jsonObject;

    }

    //节点内容
    public  static  void listNodes(Element node,  List<JSONObject> jsonObject) {
        String nodeName = node.getName();
        if ("result".equals(nodeName) || "id".equals(nodeName)
                || "resultMap".equals(nodeName) || "mapper".equals(nodeName)) {
            System.out.println("当前节点的名称：：" + node.getName());
// 获取当前节点的所有属性节点  
            List<Attribute> list = node.attributes();
            JSONObject item = new JSONObject();
            // 遍历属性节点  
            for (Attribute attr : list) {
                System.out.println(attr.getText()
                        + "---" + attr.getName()
                        + "---" + attr.getValue());
                item.put(attr.getName(), attr.getValue());
            }
            if (item.get("namespace") == null) {
                jsonObject.add(item);
            }

//如果文本内容不是空，则打印
            if (!(node.getTextTrim().equals(""))) {
                System.out.println("文本内容：：：：" + node.getText());
            }
            // 当前节点下面子节点迭代器  
            Iterator<Element> it = node.elementIterator();

            // 遍历  
            while (it.hasNext()) {
// 获取某个子节点对象  
                Element e = it.next();
                // 对子节点进行遍历  
                listNodes(e,jsonObject);
            }
        } else {
            return;
        }

    }

    // 将xml解析出来的数据转换成String
    public  static  String handleXMLToText( List<List<JSONObject>> jsonData){
        StringBuffer stringBuffer =  new StringBuffer();
        String tableName = null; // 数据表名
        String typeVal = null;
        String column = null; // 列名
        String jdbcType = null; // 数据类型
        for(int i = 0; i  < jsonData.size(); i++){
            List<JSONObject> items = jsonData.get(i);
            stringBuffer.append("CREATE TABLE ");
            for (int j = 0; j  < items.size(); j++){
                JSONObject item = items.get(j);
                if (item.get("column") == null) {
                    typeVal = (String) item.get("type");
                    String[]  typeValS = typeVal.split("\\.");
                    tableName = typeValS[typeValS.length - 1];
                    System.out.println("数据表 名：：：：" + tableName);
                    stringBuffer.append(tableName + "( ");
                } else  {
                    column  = (String) item.get("column");
                    jdbcType  = (String) item.get("jdbcType");
                    stringBuffer.append(column + " " + jdbcType
                            + (("VARCHAR".equals(jdbcType))? "(255)" : "")
                            + (j == items.size() - 1 ? " " : "," + "\r\n"));
                }
            }
            stringBuffer.append("\r\n )\r\n");
        }
        System.out.println("数据sql：：：：" +  stringBuffer.toString());
        return  stringBuffer.toString();
    }

}
