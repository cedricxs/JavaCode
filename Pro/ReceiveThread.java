package Pro;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ReceiveThread implements Runnable{
	private DataInputStream in;
	private boolean isRunning = true;
	
	public ReceiveThread(Socket socket) {
		try {
			this.in = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			isRunning = false;
			TestIOStream.closeAll(in);
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
			TestIOStream.closeAll(in);
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
