package Pro;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SendThread implements Runnable{
	private BufferedReader console;
	private DataOutputStream out;
	private boolean isRunning = true;
	
	public SendThread(Socket socket) {
		this.console = new BufferedReader(new InputStreamReader(System.in));
		try {
			this.out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			isRunning = false;
			TestIOStream.closeAll(console,out);
		}
	}

	/**
	 * 
	 * �ӿ���̨��������
	 * ��������
	 */
	public void send() {
		synchronized(System.in) {
		String msg = getMsgFromConsole();
		if(msg!=null&&!msg.equals("")) {
			try {
				out.writeUTF(msg);
				out.flush();
			} catch (IOException e) {
				isRunning = false;
				TestIOStream.closeAll(console,out);
			}
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
