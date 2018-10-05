package Pro;
/**
 * I/O流分为字符流和字节流，若针对文件IO操作加上File即可
 * 字符流：Reader,Write 无数据类型，按一定编码格式
 * 字节流：InputStream,OutputStream,以上两组有各自对应的缓冲流直接嵌套Buffered
 * 字节数组流:ByteArrayInputStream,ByteArrayOutputStream(数据类型与Byte之间的转化)
 * 基本数据类型流:DataInputStream,DataOutputStream
 * 对象序列化流 :ObjectInputStream,ObjectOutputStream
 * 打印流:PrintStream->System.out,可以创建PrintStream(OutputStream)
 * 键盘输入流:InputStream->System.in
 * System.out/in/err重定向方法:System.setIn(),System.setOut(),System.setErr()!!!
 * System.out/in默认流为控制台文件流FileDescriptor.out/in来创建的FileOutputStream/FileInputStream!!!
 * 若想重定向回控制台，只需setOut/setIn()输入与控制台关联的文件流FileDescriptor.in/out即可
 * @author 安迪
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
		out.close();    //未关闭前采用附加模式，但总体为覆盖模式
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
	 * 将文件/数据类型流->字符数组流同文件读，只是将整个文件写到ByteArrayOutputStream.buf(byte[])中
	 * 将两个流对接
	 * @throws IOException 
	 */
	public static byte[] testFileToByteArray() throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream data = new DataOutputStream(bos);
		data.writeDouble(1.45);
		return bos.toByteArray();
	}
	
	/**
	 * 将字符数组流->文件同文件写，只是将整个文件写到ByteArrayOutputStream.buf(byte[])中
	 * 将两个流对接
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
	 * 将javaObject序列化 写入文件中，并从文件中读入序列化对象并反序列化
	 * 可序列化满足Object实现Serializable接口
	 * 可反序列化满足属性未被transient修饰
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
	 * ...表示可变长度参数，写在形参的最后一位，最终以数组的形式输入！！！
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
