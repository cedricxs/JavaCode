package ChatRoom;

/**
 * 创建一个聊天室服务器，用于中转多个客户端的消息，非点对点式
 * @author 安迪
 * 1.创建服务器端，指定端口
 * 2.建立连接，得到管道流
 * 3.发送数据
 */
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 * 服务器中每一个客户端一个读取流，一个发送流，共用一个通信线程
 * 因为服务器是中转站，必然先收到某一客户端的转发请求后再转发，自然只需一个线程
 * 但不同客户端必须分配一个独立线程，因为每个客户端的请求是不分先后的
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
	//输出打印日志
	BufferedWriter file ;
	public Server(int port,String FileName) {
		try {
			file = new BufferedWriter(new FileWriter(new File(FileName)));
		} catch ( IOException e) {
			e.printStackTrace();
		}
		startServer(port);
	}
	
	public void startServer(int port) {
		try {
			server = new ServerSocket(port);
			file.write("等待客户端连接...");
			file.newLine();
		} catch (IOException e) {
			System.out.println("端口已被占用！！");
			System.exit(0);
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
			//将此通信管道注册在服务器
			clients.add(thread);
			new Thread(thread,"与客户端"+socket.getPort()+"通信的线程").start();
		}	
	}
	
	void start(Socket socket) {
		new Thread(new startNewClient(socket)).start();
	}

	public static void main(String[] args) throws IOException {
		Server server = new Server(8888,"log.txt");
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
		Channel currentChatWith = null;
		
		public Channel(Socket socket) {
			try {
				//创建账号,与服务器建立通信管道的过程
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
				out.writeUTF("你叫什么名字?");
				clientName = in.readUTF();
				file.write(clientName+"上线了...");
				file.newLine();
				file.flush();
				sendToAll(clientName+"上线了...");
				port = socket.getPort();
			} catch (IOException e) {
				isRunning = false;
				Util.closeAll(in,out);
			}
		}
		
		public String receive() {
			try {
				String msg = in.readUTF();
				file.write(clientName+"发来消息:"+msg);
				file.newLine();
				file.flush();
				return msg;
			} catch (Exception e) {
				isRunning=false;
				Util.closeAll(in,out);
				clients.remove(this);
				return null;
			}
		}
		public void sendToAll(String content) {
			for(Channel s:clients) {
				if(s.port!=this.port) {
					try {
						s.out.writeUTF(content);
					} catch (Exception e) {
						isRunning=false;
						Util.closeAll(in,out);
						clients.remove(this);
					}
				}
			}
		}
		public void sendTo(String msg) throws IOException {
			if(msg == null) {
				file.write(this.clientName+"下线了...");
				file.newLine();
				file.flush();
				sendToAll(this.clientName+"下线了...");
			}
			if(msg!=null) {
			String name = msg.split("@")[1];
			String content = msg.split("@")[0];
			file.write("中转客户端"+clientName+"的消息");
			file.newLine();
			file.flush();
			if(name.equals("all")) {
				sendToAll(content);
			}
			else {
				for(Channel s:clients) {
					if(s.clientName.equals(name)) {
						try {
							s.out.writeUTF("客户"+clientName+"发来消息:"+content);
						} catch (Exception e) {
							isRunning=false;
							Util.closeAll(in,out);
						}
					}
				}

			}}
		}
		@Override
		public void run() {
			while(isRunning) {
				try {
					sendTo(receive());
				} catch (IOException e) {
					isRunning=false;
					Util.closeAll(in,out);
					clients.remove(this);
				}
			}
			
		}
		
	}
	
	
		
}


