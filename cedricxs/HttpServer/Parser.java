package cedricxs.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Parser {

	//从socket输入流读取请求并返回request对象
	public static request parserRequest(Socket socket) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		char[] buffer = new char[1024];
		System.out.println("请求头内容:");
		read.read(buffer);
		String receive = new String(buffer);
		return new request(receive);
	}
}
