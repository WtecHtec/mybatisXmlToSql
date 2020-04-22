import com.alibaba.fastjson.JSONObject;
import com.utils.ConvertTool;
import com.utils.FileTool;
import org.dom4j.DocumentException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.*;

public class JFrameMain extends JFrame implements ActionListener{

    JButton open = null;
    Label pathLabel = null;
    TextField pathText = null;


    JButton openTarger = null;
    Label targerLabel = null;
    TextField targerText = null;


    JButton handleStart = null;
    public static void main(String[] args) {
        new JFrameMain();
    }
    public JFrameMain(){



        FlowLayout layout = new FlowLayout(FlowLayout.LEFT, 30, 20);
        Panel pannel = new Panel(layout);

        pathLabel = new Label();
        pathLabel.setText("选择XML文件目录：");

        pannel.add(pathLabel);

        open=new JButton("选择");
        open.setSize(80, 40);
        pannel.add(open);

        pathText = new TextField();
        pathText.setColumns(50);
        pathText.setEnabled(false);
        pannel.add(pathText);

        open.setActionCommand("path");
        open.addActionListener(this);


        targerLabel = new Label();
        targerLabel.setText("选择存放目录：");
        pannel.add(targerLabel);

        openTarger=new JButton("选择");
        openTarger.setSize(80, 40);
        pannel.add(openTarger);

        targerText = new TextField();
        targerText.setColumns(50);
        targerText.setEnabled(false);
        pannel.add(targerText);

        openTarger.setActionCommand("target");
        openTarger.addActionListener(this);

        handleStart = new JButton("开始");
        handleStart.setSize(750, 40);
        pannel.add(handleStart);
        handleStart.setActionCommand("start");
        handleStart.addActionListener(this);


        this.setBounds(400, 200, 700, 200);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(pannel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("actionPerformed:"+ e.getActionCommand());
        // TODO Auto-generated method stub
        if ("path".equals(e.getActionCommand()) || "target".equals(e.getActionCommand())) {
            JFileChooser jfc=new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
            jfc.showDialog(new JLabel(), "选择");
            File file=jfc.getSelectedFile();
            if(file.isDirectory()){
                System.out.println("文件夹:"+file.getAbsolutePath());
                if ("path".equals(e.getActionCommand())) {
                    pathText.setText(file.getAbsolutePath().toString());
                } else if ("target".equals(e.getActionCommand())){
                    targerText.setText(file.getAbsolutePath().toString());
                }
            }else{
                JOptionPane.showMessageDialog(null, "请选择文件夹");
                if ("path".equals(e.getActionCommand())) {
                    pathText.setText("");
                } else if ("target".equals(e.getActionCommand())){
                    pathText.setText("");
                }
                System.out.println("文件:"+file.getAbsolutePath());
            }
            System.out.println(jfc.getSelectedFile().getName());
        } else if ("start".equals(e.getActionCommand())) {
            String path = pathText.getText();
            String targetPath = targerText.getText();
            if ( ( path == null ||  "".equals(path))
                    ||  ( targetPath == null ||  "".equals(targetPath))
                    ) {
                JOptionPane.showMessageDialog(null, "请选择文件夹");
                return;
            }
            Long startTime =  new Date().getTime();
            System.out.println("开始转换"+  startTime);
            handleStart.setEnabled(false);
            handleFile();
            System.out.println("结束转换"+ new Date().getTime());
            System.out.println("转换消耗"+  (new Date().getTime() - startTime ));
            handleStart.setEnabled(true);
            JOptionPane.showMessageDialog(null, "转换成功转换消耗（ms）" + (new Date().getTime() - startTime ) );
        }

    }

    private  void  handleFile(){
        String path = pathText.getText();
        List<String> datas =  FileTool.getXMLFiles(path);
        System.out.println("xmls文件:"+ datas.toString());
        List<List<JSONObject>> jsonData = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++){
            try {
                List<JSONObject> item =    ConvertTool.start(datas.get(i));
                jsonData.add(item);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
        System.out.println("xmls文件解析:"+ jsonData.toString());
        String result =  ConvertTool.handleXMLToText(jsonData);

        String targetPath = targerText.getText();
        targetPath += "\\"+ new Date().getTime()+ ".sql";
        FileTool.wirte(targetPath, result);

    }

}
