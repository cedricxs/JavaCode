package Pro;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
/**
 * 从data.txt以二进制形式读取数据
 * 打包成数据包并发送给指定服务器及端口
 * @author 安迪
 * 若要发送有类型的数据，则通过IO转化将数据类型转化为Byte[]
 */
public class UDPClient{
	public static void main(String[] args) throws IOException {
		//创建客户端 DatagramSocket+指定接收端口
		DatagramSocket client = new DatagramSocket(6666);
		System.out.println("客户端已启动...");
		//准备数据
		@SuppressWarnings("resource")
		//无类型的，纯文本的
		FileInputStream data = new FileInputStream(new File("data.txt"));
		byte[] content = new byte[20];
		data.read(content);
		//打包DatagramPacket + 目标地址+端口
		DatagramPacket packet = new DatagramPacket(content,content.length,new InetSocketAddress("localhost",8888));
		//发送
		client.send(packet);
		System.out.println("数据已发出...");
		//释放资源
	    client.close();
		
	}
}