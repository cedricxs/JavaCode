package Pro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * ���Ա�����ڲ�ͬ�ַ���ʱ������
 * ����������IO����ת����ʽ
 * @author ����
 *
 */
public class TestEncode {
	
	public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {
		String str = "���";
		byte[] data = str.getBytes();
		//����붼����ƽ̨���ַ���:GBK
		System.out.println(new String(data));
		//ָ�������ַ���
		data = str.getBytes("utf-8");
		//����ָ�������ַ������������
		System.out.println(new String(data));
		
		//ReaderΪ�ַ�����ȡ����InputStreamReader(InputStream,charset)
		//ָ���ַ������ֽ���ת��Ϊ�ַ������ļ�����Ϊutf-8���� 
		BufferedReader buffer = new BufferedReader(
				new InputStreamReader(
						new FileInputStream(
								new File("License")),"utf-8"));
		
		
	}
}
