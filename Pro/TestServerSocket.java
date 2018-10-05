package Pro;

/**
 * 测试TCP协议通信(传输层协议)
 * 面向连接的，服务器和客户端需要首先建立连接
 * http是基于TCP的，浏览器是一个以TCP为传输协议，http为应用协议的大型客户端
 * 在此创立的服务器无法用浏览器这个客户端进行连接，应为服务器不遵循http协议
 * 请求-相应
 * @author 安迪
 * 1.创建服务器端，指定端口
 * 2.建立连接，得到管道流
 * 3.发送数据
 */
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 服务器中每一个客户端一个读取流线程
 * 所有客户端公用服务器一个输出流线程(只有一个System.in)
 * 服务器主线程用来等待新的客户端连接
 * 各个线程分工明确，互相独立
 * @author 安迪
 *
 */
public class TestServerSocket {
	//服务器
	ServerSocket server = null;
	//存储与服务器建立连接的客户端Socket
	ArrayList<Socket> clients = new ArrayList<Socket>();
	
	public TestServerSocket(int port) {
		startServer(port);
	}
	
	public void startServer(int port) {
		try {
			server = new ServerSocket(port);
			System.out.println("等待客户端连接...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void startNewClient(Socket socket) {
		clients.add(socket);
		//为该客户端分配一个接收线程
		serverreceiveThread thread = new serverreceiveThread(socket);
		new Thread(thread,"接收客户端"+socket.getPort()+"的线程").start();
	}
	
	void startSendThread() {
		new Thread(new sendToClientThread(),"给客户端发送数据的线程").start();
	}
	public static void main(String[] args) throws IOException {
		TestServerSocket server = new TestServerSocket(8888);
		server.startSendThread();
		while(true) {
			//等待接收客户端的连接 阻塞式,一个accept()一个客户端，
			//可与多个客户端连接交互而且不停止,此处的socket就是client,是一个通信管道
			Socket socket = server.server.accept();
			System.out.println("客户端"+socket.getPort()+"的连接...");
			/*//3.发送数据
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			DataInputStream in = new DataInputStream(socket.getInputStream());
			out.writeUTF("来自服务器的问候...");
			out.flush();
			System.out.println(in.readUTF());*/
			
			//由accept()方法阻塞获得与客户端的连接，一旦获得连接，开辟出两个线程分别供输入流输出流通信,各自独立运行
			//while循环使得获得上一个客户端连接之后阻塞main线程等待获得下一个客户端连接
			server.startNewClient(socket);
		}
	}
	
	//为每一个客户端分配一个通信线程
	class serverreceiveThread implements Runnable{
		private DataInputStream in;
		private Socket socket;
		private boolean isRunning = true;
		
		public serverreceiveThread(Socket socket) {
			try {
				in = new DataInputStream(socket.getInputStream());
				this.socket = socket;
			} catch (IOException e) {
				isRunning = false;
				TestIOStream.closeAll(in);
			}
		}
		
		public void receive() {
			try {
				String msg = in.readUTF();
				System.out.println("客户端"+socket.getPort()+"发来消息:"+msg);
			} catch (IOException e) {
				TestIOStream.closeAll(in);
				clients.remove(this.socket);
			}
		}
		

		@Override
		public void run() {
			while(isRunning) {
				receive();
			}
			
		}
		
	}
	
	//确定客户端并发送数据的线程
	class sendToClientThread implements Runnable {
		boolean isRunning = true;
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		DataOutputStream out = null;
		private String getInfoFromConsole() {
			try {
				String msg = console.readLine();
				return msg;
			} catch (IOException e) {
				return null;
			}
		}
		private void sendToAll(String msg) {
			for(Socket s:clients) {
				try {
					out = new DataOutputStream(s.getOutputStream());
					out.writeUTF(msg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		private void send() {
			String info = getInfoFromConsole();
			String p = info.split(",")[0];
			String msg = info.split(",")[1];
			System.out.println(p);
			if(p.equals("all")) {
				sendToAll(msg);
			}
			else {
				int port = Integer.parseInt(p);
				for(Socket s:clients) {
					//对接输出流!!!
					if(s.getPort()==port) {
						try {
							out = new DataOutputStream(s.getOutputStream());
							
						} catch (IOException e) {
							e.printStackTrace();
						}
						break;
					}
				}
				try {
					if(out!=null)out.writeUTF(msg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			out =null;
		}
		@Override
		public void run() {
			while(isRunning) {
				send();
				
			}
			
		}
		
	}
}


