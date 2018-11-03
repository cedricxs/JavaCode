package ChatRoom.ServerPack;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
/**
 * 实现点对点通信，服务器只负责监听客户端链接请求和发送链接请求
 * 真正的通信流是客户端之间建立的
 * @author cedricxs
 *
 */
public class Server {
	ServerSocket server = null;
	BufferedWriter file =null;
	//数据库连接池...
	Connection database = null;
	//客户端通信池
	ArrayList<Channel>clients = null;
	public Server(int port,String FileName) {
		startServer(port,FileName);
	}
	
	public void startServer(int port,String FileName) {
		try {
			//FileOutputStream(file,true)为追加写模式
			file = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(FileName),true)));
			writeToFile("当前服务器启动时间:"+new Date().toString());
			clients = new ArrayList<Channel>();
		} catch ( IOException e) {
			e.printStackTrace();
		}
		try {
			server = new ServerSocket(port);
			System.out.println("服务器已启动!!!");
			database = ConnectSQL.createConnection();
		} catch (IOException e) {
			System.out.println("端口已被占用...");
			System.exit(0);
		} catch (ClassNotFoundException  | SQLException e) {
			System.out.println("数据库链接失败");
			e.printStackTrace();
		} 
	}
	
	
	void startChannel(Socket socket) {
		Channel c = new Channel(socket);
		new Thread(c).start();
		clients.add(c);
		
	}

	public static void main(String[] args) throws IOException {
		Server server = new Server(8889,"log.txt");
		while(true) {
			//main线程为服务器主线程
			//通信模式由客户端决定,因为是客户端与服务器建立连接
			//客户端决定建立连接之后直接运行完程序，则通信管道Socket直接失效
			//可分为持续链接和非持续链接状态,可参照HTTP请求/响应头的connect:alive
			Socket socket = server.server.accept();
			server.startChannel(socket);
		}
	}
		
	void writeToFile(String msg) {
		try {
			file.write(msg);
			file.newLine();
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//与客户端通信的线程,负责接受客户端的request,并根据路由表处理请求
	public class Channel implements Runnable{
		DataOutputStream out;
		DataInputStream in;
		boolean isRunning;
		String name;
		public Channel(Socket socket) {
			try {
				isRunning = true;
				out = new DataOutputStream(socket.getOutputStream());
				in = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		@Override
		public void run() {
			while(isRunning) {
				try {
					//从客户端接受信息，解析成request对象
					request req = request.parse(in.readUTF());
					Router.routes.get(req.getURL()).handleFunction(req,database,this,clients);
					
				} catch (IOException e) {
					isRunning = false;
					clients.remove(this);
				} 
			}
		}
			
	}
}


