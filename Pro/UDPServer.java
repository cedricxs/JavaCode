package Pro;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

/**
 * ����UDPЭ��ͨ��(�����Э��)
 * @author ����
 * ���������ӣ����ݷ����ɶ�ʧ
 * ���տͻ��˷������ݰ�����������
 * ������ͷ�����(bs)����һ�����Ŀͻ��˺ͷ����(cs)�ṹ
 * ��Ϊ�������д�õĶ����ģ�������������http/ftp��ʽ,���Զ���������ķ�������ֻ��Ҳ���http/ftp����Э�飬����������ݸ�ʽ
 * ����������ſ�����������http/ftpЭ���������(Ӧ�ò�Э��)
 * ������cs��˵�����ڱ����п��Կ������������������(��ʽ/Э��)���ɿͻ��˺ͷ��������Զ��ģ�ֻ����Լ���
 * �����������������һ����ѭһ������Э��Ŀͻ��˷������˽ṹ
 */

public class UDPServer{
	public static void main(String[] args) throws IOException {
		
		//���������� DatagramSocket+ָ�����ն˿�
		DatagramSocket server = new DatagramSocket(8888);
		System.out.println("������������...");
		//׼���������� �ֽ����� ��װ��DatapramPacket
		byte [] content = new byte[1024];
		DatagramPacket packet = new DatagramPacket(content,content.length);
		System.out.println("�ȴ����տͻ�������...");
		//��������
		server.receive(packet);
		//����
		byte[] result = packet.getData();
		String[] res = new String(result).trim().split(" ");
		System.out.println(Arrays.toString(res));
		//�ͷ���Դ
		
		server.close();
	}
	
}