package ChatRoom;

/**
 * ����һ�������ҷ�������������ת����ͻ��˵���Ϣ���ǵ�Ե�ʽ
 * @author ����
 * 1.�����������ˣ�ָ���˿�
 * 2.�������ӣ��õ��ܵ���
 * 3.��������
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
 * ��������ÿһ���ͻ���һ����ȡ����һ��������������һ��ͨ���߳�
 * ��Ϊ����������תվ����Ȼ���յ�ĳһ�ͻ��˵�ת���������ת������Ȼֻ��һ���߳�
 * ����ͬ�ͻ��˱������һ�������̣߳���Ϊÿ���ͻ��˵������ǲ����Ⱥ��
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
	//�����ӡ��־
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
			file.write("�ȴ��ͻ�������...");
			file.newLine();
		} catch (IOException e) {
			System.out.println("�˿��ѱ�ռ�ã���");
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
			//����ͨ�Źܵ�ע���ڷ�����
			clients.add(thread);
			new Thread(thread,"��ͻ���"+socket.getPort()+"ͨ�ŵ��߳�").start();
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
	
	//Ϊÿһ���ͻ��˷���һ��ͨ���߳�
	class Channel implements Runnable{
		private DataInputStream in;
		private DataOutputStream out;
		private int port;
		private String clientName;
		private boolean isRunning = true;
		Channel currentChatWith = null;
		
		public Channel(Socket socket) {
			try {
				//�����˺�,�����������ͨ�Źܵ��Ĺ���
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
				out.writeUTF("���ʲô����?");
				clientName = in.readUTF();
				file.write(clientName+"������...");
				file.newLine();
				file.flush();
				sendToAll(clientName+"������...");
				port = socket.getPort();
			} catch (IOException e) {
				isRunning = false;
				Util.closeAll(in,out);
			}
		}
		
		public String receive() {
			try {
				String msg = in.readUTF();
				file.write(clientName+"������Ϣ:"+msg);
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
				file.write(this.clientName+"������...");
				file.newLine();
				file.flush();
				sendToAll(this.clientName+"������...");
			}
			if(msg!=null) {
			String name = msg.split("@")[1];
			String content = msg.split("@")[0];
			file.write("��ת�ͻ���"+clientName+"����Ϣ");
			file.newLine();
			file.flush();
			if(name.equals("all")) {
				sendToAll(content);
			}
			else {
				for(Channel s:clients) {
					if(s.clientName.equals(name)) {
						try {
							s.out.writeUTF("�ͻ�"+clientName+"������Ϣ:"+content);
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


