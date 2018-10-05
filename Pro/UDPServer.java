package Pro;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

/**
 * 测试UDP协议通信(传输层协议)
 * @author 安迪
 * 非面向连接，数据发出可丢失
 * 接收客户端发的数据包并解析数据
 * 浏览器和服务器(bs)就是一个大大的客户端和服务端(cs)结构
 * 因为浏览器是写好的定死的，传输的数据遵从http/ftp格式,所以对于浏览器的服务器端只能也遵从http/ftp传输协议，即传输的数据格式
 * 这样浏览器才可以正常按照http/ftp协议解析数据(应用层协议)
 * 但对于cs来说，我在本例中可以看到，传输的数据类型(格式/协议)是由客户端和服务器端自定的，只需相对即可
 * 所以浏览器服务器是一个遵循一定传输协议的客户端服务器端结构
 */

public class UDPServer{
	public static void main(String[] args) throws IOException {
		
		//创建服务器 DatagramSocket+指定接收端口
		DatagramSocket server = new DatagramSocket(8888);
		System.out.println("服务器已启动...");
		//准备接受容器 字节数组 封装类DatapramPacket
		byte [] content = new byte[1024];
		DatagramPacket packet = new DatagramPacket(content,content.length);
		System.out.println("等待接收客户端数据...");
		//接收数据
		server.receive(packet);
		//分析
		byte[] result = packet.getData();
		String[] res = new String(result).trim().split(" ");
		System.out.println(Arrays.toString(res));
		//释放资源
		
		server.close();
	}
	
}