package Pro;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
/**
 * ��data.txt�Զ�������ʽ��ȡ����
 * ��������ݰ������͸�ָ�����������˿�
 * @author ����
 * ��Ҫ���������͵����ݣ���ͨ��IOת������������ת��ΪByte[]
 */
public class UDPClient{
	public static void main(String[] args) throws IOException {
		//�����ͻ��� DatagramSocket+ָ�����ն˿�
		DatagramSocket client = new DatagramSocket(6666);
		System.out.println("�ͻ���������...");
		//׼������
		@SuppressWarnings("resource")
		//�����͵ģ����ı���
		FileInputStream data = new FileInputStream(new File("data.txt"));
		byte[] content = new byte[20];
		data.read(content);
		//���DatagramPacket + Ŀ���ַ+�˿�
		DatagramPacket packet = new DatagramPacket(content,content.length,new InetSocketAddress("localhost",8888));
		//����
		client.send(packet);
		System.out.println("�����ѷ���...");
		//�ͷ���Դ
	    client.close();
		
	}
}