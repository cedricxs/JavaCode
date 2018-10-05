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
 * 一、网络:将不同区域的计算机连接到一起 局域网 城域网 互联网
 * 二、IP地址:确定网络上一个绝对地址->房子的地址
 * 三、端口号:区分计算机上的软件->房门号,2个字节，共65536个
 * 在同一个协议下，端口号不能重复
 * 0-1024预留给操作系统或知名厂商
 * 四、资源定位:URL 统一资源定位符
 * 五、数据的传输:
 * 1.通信协议:	Tcp协议 三次握手 面向连接 安全 效率低
 * 			UDP协议 非面向连接 发送快，效率高 数据可能丢失 不安全
 * 2.传输:封装->拆封
 * 		
 * @author 安迪
 *
 */
public class TestNetProgrammer {
	public static void main(String[] args) throws UnsupportedEncodingException, IOException {
		test1();
		test2();
		testURL("www.baidu.com");
	}
	
	
	public static void test1() throws UnknownHostException {
		
		//hostName->域名解析->ip地址
		InetAddress addr;
		addr = InetAddress.getLoopbackAddress();
		System.out.println(addr.getHostName());
		System.out.println(addr.getHostAddress());
		addr = InetAddress.getByName("www.baidu.com");
		System.out.println(addr.getHostName());
		System.out.println(addr.getHostAddress());
	}
	
	public static void test2() {
		//InetSocketAddress封装了InetAddress和端口号
		InetSocketAddress addr = new InetSocketAddress("localhost",9999);
		System.out.println(addr.getHostName());
		System.out.println(addr.getAddress());
		System.out.println(addr.getPort());
		
	}
	
	//简单爬虫实现
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
