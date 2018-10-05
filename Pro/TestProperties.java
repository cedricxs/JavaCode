package Pro;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
/**
 * 资源文件配置的读写
 * 写：store()方法，后缀名.properties
 *    storeToXML(),后缀名.xml
 * @author 安迪
 *
 */
public class TestProperties {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Properties pros = new Properties();
		pros.setProperty("dbpath", "c:/db");
		pros.setProperty("user", "cedric");
		pros.setProperty("pwd", "260074");
		System.out.println(pros.getProperty("user","default"));
		//使用相对路径
		pros.store(new FileOutputStream(new File("db.properties")), "db配置");
		pros.storeToXML(new FileOutputStream(new File("db.xml")), "db配置");
		Properties pros_ = new Properties();
		pros_.load(new FileReader("db.properties"));
		System.out.println(pros.getProperty("dbpath"));
	}
}
