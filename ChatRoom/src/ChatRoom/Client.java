package ChatRoom;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
/**
 * 面向服务器的客户端，消息需通过服务器中转，不可点对点
 * @author 安迪
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
	 * 从客户端接收数据
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
			//不断从与服务器连接的输入流管道中读取数据
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
	 * 从控制台接收数据
	 * 发送数据
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
			//不断往与服务器连接的输出流管道中输出数据
			send();
		}
		
	}

}