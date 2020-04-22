package com.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileTool {

    public static void  wirte(String path,String content){
        // 构建指定文件
        File file = new File(path);
        System.out.println("数据sql 路径：：：：" +  path);
        // 根据文件创建文件的输出流
        try (OutputStream os = new FileOutputStream(file)) {
            // 把内容转换成字节数组
            byte[] data = content.getBytes();
            // 向文件写入内容
            os.write(data);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    public  static List<String> getXMLFiles(String path) {
        File file = new File(path);//File类型可以是文件也可以是文件夹
        File[] fileList = file.listFiles();//将该目录下的所有文件放置在一个File类型的数组中
        List<String> wjList = new ArrayList<String>();//新建一个文件集合
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isFile() && fileList[i].getName().endsWith(".xml") ) {//判断是否为XML文件
                wjList.add(fileList[i].getAbsolutePath());
            }
        }
        return  wjList;
    }
}
