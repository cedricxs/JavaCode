package ChatRoom;

/**
 * 创建一个聊天室服务器，用于中转多个客户端的消息
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
 * 服务器中每一个客户端一个读取流，一个发送流线程
 * 
 * 服务器主线程用来等待新的客户端连接
 * 各个线程分工明确，互相独立
 * @author 安迪
 *
 */
public class Server {
	//服务器
	ServerSocket server = null;
	//存储与服务器建立连接的客户端通信流线程
	ArrayList<Channel> clients = new ArrayList<Channel>();
	
	public Server(int port) {
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
	
	class startNewClient implements Runnable{
		Socket socket = null;
		startNewClient(Socket socket){
			this.socket = socket;
		}
		@Override
		public void run() {
			Channel thread = new Channel(socket);
			clients.add(thread);
			new Thread(thread,"与客户端"+socket.getPort()+"通信的线程").start();
		}	
	}
	
	void start(Socket socket) {
		new Thread(new startNewClient(socket)).start();
	}

	public static void main(String[] args) throws IOException {
		Server server = new Server(8888);
		while(true) {
			Socket socket = server.server.accept();
			server.start(socket);
		}
	}
	
	//为每一个客户端分配一个通信线程
	class Channel implements Runnable{
		private DataInputStream in;
		private DataOutputStream out;
		private int port;
		private String clientName;
		private boolean isRunning = true;
		
		public Channel(Socket socket) {
			try {
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
				out.writeUTF("你叫什么名字?");
				clientName = in.readUTF();
				System.out.println(clientName+"上线了...");
				sendToAll(clientName+"上线了...");
				port = socket.getPort();
			} catch (IOException e) {
				isRunning = false;
				Util.closeAll(in);
			}
		}
		
		public String receive() {
			try {
				String msg = in.readUTF();
				System.out.println(clientName+"发来消息:"+msg);
				return msg;
			} catch (IOException e) {
				Util.closeAll(in);
				clients.remove(this);
				return null;
			}
		}
		public void sendToAll(String content) {
			for(Channel s:clients) {
				if(s.port!=this.port) {
					try {
						s.out.writeUTF(content);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		public void sendTo(String msg) {
			String name = msg.split("@")[1];
			String content = msg.split("@")[0];
			System.out.println("中转客户端"+clientName+"的消息");
			if(name.equals("all")) {
				sendToAll(content);
			}
			else {
				for(Channel s:clients) {
					if(s.clientName.equals(name)) {
						try {
							s.out.writeUTF("客户"+clientName+"发来消息:"+content);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}

			}
		}
		@Override
		public void run() {
			while(isRunning) {
				sendTo(receive());
			}
			
		}
		
	}
	
	
		
}


