package Pro;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 面向连接的，服务器和客户端需要首先建立连接,相对照与UDP协议
 * 请求-相应
 * @author 安迪
 * 1.创建客户端：服务器地址+端口号(在此时与服务器连接),客户端的接收端口系统自动分配
 * 2.接收服务器数据
 * 输入流与输出流应在两个线程内，可同时运行,彼此独立
 */
public class CilentSocket {
	public static void main(String[] args) throws UnknownHostException, IOException {
		//1.
		Socket client = new Socket("localhost",8888);
		
		//获取客户端发送数据/接收数据流
		/*DataInputStream in = new DataInputStream(clien
		 * 
		 * t.getInputStream());
		DataOutputStream out = new DataOutputStream(client.getOutputStream());
		String echo = in.readUTF();
		System.out.println(echo);
		out.writeUTF("收到问候，谢谢服务器...");
		out.flush();*/
		
		new Thread(new SendThread(client)).start();
		new Thread(new ReceiveThread(client)).start();
	}
}


