package Pro;
/**
 * I/O����Ϊ�ַ������ֽ�����������ļ�IO��������File����
 * �ַ�����Reader,Write ���������ͣ���һ�������ʽ
 * �ֽ�����InputStream,OutputStream,���������и��Զ�Ӧ�Ļ�����ֱ��Ƕ��Buffered
 * �ֽ�������:ByteArrayInputStream,ByteArrayOutputStream(����������Byte֮���ת��)
 * ��������������:DataInputStream,DataOutputStream
 * �������л��� :ObjectInputStream,ObjectOutputStream
 * ��ӡ��:PrintStream->System.out,���Դ���PrintStream(OutputStream)
 * ����������:InputStream->System.in
 * System.out/in/err�ض��򷽷�:System.setIn(),System.setOut(),System.setErr()!!!
 * System.out/inĬ����Ϊ����̨�ļ���FileDescriptor.out/in��������FileOutputStream/FileInputStream!!!
 * �����ض���ؿ���̨��ֻ��setOut/setIn()���������̨�������ļ���FileDescriptor.in/out����
 * @author ����
 *
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class TestIOStream {
	public static void testInputStream() throws IOException {
		File f = new File("License");
		InputStream in = new FileInputStream(f);
		byte[] content = new byte[1024];
		in.read(content, 0, 1024);
		System.out.println(new String(content));
		in.close();
	}
	
	public static void testOutputStream() throws IOException {
		File f = new File("License");
		OutputStream out = new FileOutputStream(f);
		String content = "This is write!!";
		byte[] tar = content.getBytes();
		out.write(tar);
		testInputStream();
		out.flush();
		out.write(tar);
		out.close();    //δ�ر�ǰ���ø���ģʽ��������Ϊ����ģʽ
	}
	
	public static void testFileReader() throws IOException {
		File f = new File("License");
		FileReader in = new FileReader(f);
		char[] content = new char[1024];
		in.read(content);
		System.out.println(content);
		in.close();
	}
	
	public static void testFileWriter() throws IOException {
		File f = new File("License");
		FileWriter out = new FileWriter(f);
		out.write(new String("This is append!!!"));
		out.flush();
		out.close();
	}
	
	/**
	 * ���ļ�/����������->�ַ�������ͬ�ļ�����ֻ�ǽ������ļ�д��ByteArrayOutputStream.buf(byte[])��
	 * ���������Խ�
	 * @throws IOException 
	 */
	public static byte[] testFileToByteArray() throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream data = new DataOutputStream(bos);
		data.writeDouble(1.45);
		return bos.toByteArray();
	}
	
	/**
	 * ���ַ�������->�ļ�ͬ�ļ�д��ֻ�ǽ������ļ�д��ByteArrayOutputStream.buf(byte[])��
	 * ���������Խ�
	 * @throws IOException 
	 */
	public static void testByteArrayToFile(byte [] content) throws IOException {
		ByteArrayInputStream bin = new ByteArrayInputStream(content);
		OutputStream out = new FileOutputStream(new File("License"));
		byte[] flush = new byte[1024];
		while(-1!=bin.read(flush,0,1024)) {
			out.write(flush);
		}
		bin.close();
		out.close();
	}
	/**
	 * ��javaObject���л� д���ļ��У������ļ��ж������л����󲢷����л�
	 * �����л�����Objectʵ��Serializable�ӿ�
	 * �ɷ����л���������δ��transient����
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	public static void testObjectStream() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File("Object.txt")));
		Student s = new Student();
		out.writeObject(s);
		out.flush();
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File("Object.txt")));
		Object obj = in.readObject();
		Student tar = null;
		if(obj instanceof Student) {
			tar = (Student)obj;
		}
		tar.prinfo();
		closeAll(out,in);
	}

	/**
	 * ...��ʾ�ɱ䳤�Ȳ�����д���βε����һλ���������������ʽ���룡����
	 * @param io
	 */
	@SafeVarargs
	public static<T extends Closeable> void closeAll(T ... io) {
		for(Closeable t:io) {
			try {
				t.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		testInputStream();
		testOutputStream();
		testFileReader();
		testFileWriter();
		testObjectStream();
	}
	
}
