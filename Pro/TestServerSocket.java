package Pro;

/**
 * ����TCPЭ��ͨ��(�����Э��)
 * �������ӵģ��������Ϳͻ�����Ҫ���Ƚ�������
 * http�ǻ���TCP�ģ��������һ����TCPΪ����Э�飬httpΪӦ��Э��Ĵ��Ϳͻ���
 * �ڴ˴����ķ������޷������������ͻ��˽������ӣ�ӦΪ����������ѭhttpЭ��
 * ����-��Ӧ
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
 * ��������ÿһ���ͻ���һ����ȡ���߳�
 * ���пͻ��˹��÷�����һ��������߳�(ֻ��һ��System.in)
 * ���������߳������ȴ��µĿͻ�������
 * �����̷ֹ߳���ȷ���������
 * @author ����
 *
 */
public class TestServerSocket {
	//������
	ServerSocket server = null;
	//�洢��������������ӵĿͻ���Socket
	ArrayList<Socket> clients = new ArrayList<Socket>();
	
	public TestServerSocket(int port) {
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
	
	void startNewClient(Socket socket) {
		clients.add(socket);
		//Ϊ�ÿͻ��˷���һ�������߳�
		serverreceiveThread thread = new serverreceiveThread(socket);
		new Thread(thread,"���տͻ���"+socket.getPort()+"���߳�").start();
	}
	
	void startSendThread() {
		new Thread(new sendToClientThread(),"���ͻ��˷������ݵ��߳�").start();
	}
	public static void main(String[] args) throws IOException {
		TestServerSocket server = new TestServerSocket(8888);
		server.startSendThread();
		while(true) {
			//�ȴ����տͻ��˵����� ����ʽ,һ��accept()һ���ͻ��ˣ�
			//�������ͻ������ӽ������Ҳ�ֹͣ,�˴���socket����client,��һ��ͨ�Źܵ�
			Socket socket = server.server.accept();
			System.out.println("�ͻ���"+socket.getPort()+"������...");
			/*//3.��������
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			DataInputStream in = new DataInputStream(socket.getInputStream());
			out.writeUTF("���Է��������ʺ�...");
			out.flush();
			System.out.println(in.readUTF());*/
			
			//��accept()�������������ͻ��˵����ӣ�һ��������ӣ����ٳ������̷ֱ߳������������ͨ��,���Զ�������
			//whileѭ��ʹ�û����һ���ͻ�������֮������main�̵߳ȴ������һ���ͻ�������
			server.startNewClient(socket);
		}
	}
	
	//Ϊÿһ���ͻ��˷���һ��ͨ���߳�
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
				System.out.println("�ͻ���"+socket.getPort()+"������Ϣ:"+msg);
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
	
	//ȷ���ͻ��˲��������ݵ��߳�
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
					//�Խ������!!!
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


