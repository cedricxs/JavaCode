package Pro;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * һ������:����ͬ����ļ�������ӵ�һ�� ������ ������ ������
 * ����IP��ַ:ȷ��������һ�����Ե�ַ->���ӵĵ�ַ
 * �����˿ں�:���ּ�����ϵ����->���ź�,2���ֽڣ���65536��
 * ��ͬһ��Э���£��˿ںŲ����ظ�
 * 0-1024Ԥ��������ϵͳ��֪������
 * �ġ���Դ��λ:URL ͳһ��Դ��λ��
 * �塢���ݵĴ���:
 * 1.ͨ��Э��:	TcpЭ�� �������� �������� ��ȫ Ч�ʵ�
 * 			UDPЭ�� ���������� ���Ϳ죬Ч�ʸ� ���ݿ��ܶ�ʧ ����ȫ
 * 2.����:��װ->���
 * 		
 * @author ����
 *
 */
public class TestNetProgrammer {
	public static void main(String[] args) throws UnsupportedEncodingException, IOException {
		test1();
		test2();
		testURL("www.baidu.com");
	}
	
	
	public static void test1() throws UnknownHostException {
		
		//hostName->��������->ip��ַ
		InetAddress addr;
		addr = InetAddress.getLoopbackAddress();
		System.out.println(addr.getHostName());
		System.out.println(addr.getHostAddress());
		addr = InetAddress.getByName("www.baidu.com");
		System.out.println(addr.getHostName());
		System.out.println(addr.getHostAddress());
	}
	
	public static void test2() {
		//InetSocketAddress��װ��InetAddress�Ͷ˿ں�
		InetSocketAddress addr = new InetSocketAddress("localhost",9999);
		System.out.println(addr.getHostName());
		System.out.println(addr.getAddress());
		System.out.println(addr.getPort());
		
	}
	
	//������ʵ��
	public static void testURL(String url) throws UnsupportedEncodingException, IOException {
		URL u = new URL("http://"+url);
		//System.out.println(u.getProtocol());
		byte[] con = new byte[1024];
		BufferedInputStream in = new BufferedInputStream(u.openStream());
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File("baidu.html")));
		while(-1!=in.read(con))	{	
			out.write(con);
			System.out.println(new String(con,"utf-8"));
		}
		System.out.println();
		TestIOStream.closeAll(out,in);
	}
}
