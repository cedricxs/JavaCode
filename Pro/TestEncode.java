package Pro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * 测试编解码在不同字符集时会乱码
 * 并测试两种IO流的转化方式
 * @author 安迪
 *
 */
public class TestEncode {
	
	public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {
		String str = "你好";
		byte[] data = str.getBytes();
		//编解码都采用平台的字符集:GBK
		System.out.println(new String(data));
		//指定编码字符集
		data = str.getBytes("utf-8");
		//若不指定解码字符集，则会乱码
		System.out.println(new String(data));
		
		//Reader为字符流读取，用InputStreamReader(InputStream,charset)
		//指定字符集将字节流转化为字符流，文件必须为utf-8编码 
		BufferedReader buffer = new BufferedReader(
				new InputStreamReader(
						new FileInputStream(
								new File("License")),"utf-8"));
		
		
	}
}
