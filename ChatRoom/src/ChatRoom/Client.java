package ChatRoom;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
/**
 * ����������Ŀͻ��ˣ���Ϣ��ͨ����������ת�����ɵ�Ե�
 * @author ����
 *
 */


public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket client = new Socket("localhost",8888);

		new Thread(new SendThread(client)).start();
		new Thread(new ReceiveThread(client)).start();
	}
}

class ReceiveThread implements Runnable{
	private DataInputStream in;
	private boolean isRunning = true;
	
	public ReceiveThread(Socket socket) {
		try {
			this.in = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			isRunning = false;
			Util.closeAll(in);
		}
	}

	/**
	 * �ӿͻ��˽�������
	 */
	public void receive() {
		try {
			String msg = in.readUTF();
			System.out.println(msg);
		} catch (IOException e) {
			isRunning = false;
			Util.closeAll(in);
		}
	}

	@Override
	public void run() {
		while(isRunning) {
			//���ϴ�����������ӵ��������ܵ��ж�ȡ����
			receive();
		}
		
	}

}
class SendThread implements Runnable{
	private BufferedReader console;
	private DataOutputStream out;
	private boolean isRunning = true;
	
	public SendThread(Socket socket) {
		this.console = new BufferedReader(new InputStreamReader(System.in));
		try {
			this.out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			isRunning = false;
			Util.closeAll(console,out);
		}
	}

	/**
	 * 
	 * �ӿ���̨��������
	 * ��������
	 */
	public void send() {
		String msg = getMsgFromConsole();
		if(msg!=null&&!msg.equals("")) {
			try {
				out.writeUTF(msg);
				out.flush();
			} catch (IOException e) {
				isRunning = false;
				Util.closeAll(console,out);
			}
		}
	}
	
	public String getMsgFromConsole() {
		try {
			return console.readLine();
		} catch (IOException e) {
			return "";
		}
		
	}
	@Override
	public void run() {
		while(isRunning) {
			//����������������ӵ�������ܵ����������
			send();
		}
		
	}

}