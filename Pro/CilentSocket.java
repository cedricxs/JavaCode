package Pro;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * �������ӵģ��������Ϳͻ�����Ҫ���Ƚ�������,�������UDPЭ��
 * ����-��Ӧ
 * @author ����
 * 1.�����ͻ��ˣ���������ַ+�˿ں�(�ڴ�ʱ�����������),�ͻ��˵Ľ��ն˿�ϵͳ�Զ�����
 * 2.���շ���������
 * �������������Ӧ�������߳��ڣ���ͬʱ����,�˴˶���
 */
public class CilentSocket {
	public static void main(String[] args) throws UnknownHostException, IOException {
		//1.
		Socket client = new Socket("localhost",8888);
		
		//��ȡ�ͻ��˷�������/����������
		/*DataInputStream in = new DataInputStream(clien
		 * 
		 * t.getInputStream());
		DataOutputStream out = new DataOutputStream(client.getOutputStream());
		String echo = in.readUTF();
		System.out.println(echo);
		out.writeUTF("�յ��ʺ�лл������...");
		out.flush();*/
		
		new Thread(new SendThread(client)).start();
		new Thread(new ReceiveThread(client)).start();
	}
}


