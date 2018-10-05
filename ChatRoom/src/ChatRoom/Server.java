package ChatRoom;

/**
 * ����һ�������ҷ�������������ת����ͻ��˵���Ϣ
 * @author ����
 * 1.�����������ˣ�ָ���˿�
 * 2.�������ӣ��õ��ܵ���
 * 3.��������
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
 * ��������ÿһ���ͻ���һ����ȡ����һ���������߳�
 * 
 * ���������߳������ȴ��µĿͻ�������
 * �����̷ֹ߳���ȷ���������
 * @author ����
 *
 */
public class Server {
	//������
	ServerSocket server = null;
	//�洢��������������ӵĿͻ���ͨ�����߳�
	ArrayList<Channel> clients = new ArrayList<Channel>();
	
	public Server(int port) {
		startServer(port);
	}
	
	public void startServer(int port) {
		try {
			server = new ServerSocket(port);
			System.out.println("�ȴ��ͻ�������...");
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
			new Thread(thread,"��ͻ���"+socket.getPort()+"ͨ�ŵ��߳�").start();
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
	
	//Ϊÿһ���ͻ��˷���һ��ͨ���߳�
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
				out.writeUTF("���ʲô����?");
				clientName = in.readUTF();
				System.out.println(clientName+"������...");
				sendToAll(clientName+"������...");
				port = socket.getPort();
			} catch (IOException e) {
				isRunning = false;
				Util.closeAll(in);
			}
		}
		
		public String receive() {
			try {
				String msg = in.readUTF();
				System.out.println(clientName+"������Ϣ:"+msg);
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
			System.out.println("��ת�ͻ���"+clientName+"����Ϣ");
			if(name.equals("all")) {
				sendToAll(content);
			}
			else {
				for(Channel s:clients) {
					if(s.clientName.equals(name)) {
						try {
							s.out.writeUTF("�ͻ�"+clientName+"������Ϣ:"+content);
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


