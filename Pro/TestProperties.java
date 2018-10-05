package Pro;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
/**
 * ��Դ�ļ����õĶ�д
 * д��store()��������׺��.properties
 *    storeToXML(),��׺��.xml
 * @author ����
 *
 */
public class TestProperties {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Properties pros = new Properties();
		pros.setProperty("dbpath", "c:/db");
		pros.setProperty("user", "cedric");
		pros.setProperty("pwd", "260074");
		System.out.println(pros.getProperty("user","default"));
		//ʹ�����·��
		pros.store(new FileOutputStream(new File("db.properties")), "db����");
		pros.storeToXML(new FileOutputStream(new File("db.xml")), "db����");
		Properties pros_ = new Properties();
		pros_.load(new FileReader("db.properties"));
		System.out.println(pros.getProperty("dbpath"));
	}
}
