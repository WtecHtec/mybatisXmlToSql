import com.utils.ConvertTool;
import com.utils.FileTool;
import org.dom4j.DocumentException;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello World!");
        String path="E:\\SPRING-BOOT-master\\档案\\MybatisConvert\\resource\\test.sql";
        FileTool.wirte(path, "Hello World!");
        ConvertTool convertTool = new ConvertTool();
//        try {
////            convertTool.start();
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        }
    }
}
