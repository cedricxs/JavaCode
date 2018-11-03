package cedricxs.HttpServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;

//Accept-Ranges	bytes
//Cache-Control	no-cache
//Connection	keep-alive
//Content-Length	3701
//Content-Type	text/html
//构建响应内容
public class response {
	BufferedWriter sender = null;
	//响应头
	String Header;
	//响应正文,与响应头隔一行
	String Content;
	//记录正文长度
	int len = 0;
	int code;
	String ContentType = "text/html";
	public response(OutputStream out) {
		sender = new BufferedWriter(new OutputStreamWriter(out));
	}
	public void setCode(int code) {
		this.code = code;
	}
	public void setContent(String content) {
		this.Content = content;
		this.len = content.getBytes().length;
	}

	public void createHeader() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("HTTP/1.1").append(" ").append(code).append(" ");
		switch(code) {
		case 200:
			buffer.append("OK");
			break;
		case 404:
			buffer.append("NOT FOUND");
			break;
		case 500:
			buffer.append("SERVER ERR");
			break;
		}
		buffer.append("\n");
		buffer.append("Server:Cedricxs Server/0.0.1").append("\n");
		buffer.append("Date:").append(new Date()).append("\n");
		buffer.append("Content-type:"+ContentType+";charset=UTF-8").append("\n");
		buffer.append("Content-Length:").append(len).append("\n").append("\n");
		Header = buffer.toString();
	}
	public void setContentType(String type) {
		this.ContentType = type;
	}
	public void send() {
		createHeader();
		System.out.println("响应内容:");
		System.out.println(Header+Content);
		try {
			sender.write(Header+Content);
			sender.flush();
			sender.close();
			System.out.println("发送完毕...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void send(String FileName) {
		BufferedReader read = null;
		String content = null;
		try {
			StringBuffer buffer = new StringBuffer();
			read = new BufferedReader(new FileReader(new File(FileName)));
			String temp = read.readLine();
			while(temp!=null) {
				buffer.append(temp+"\n");
				temp = read.readLine();
			}
			content = buffer.toString();
			this.setCode(200);
		} catch (IOException e1) {
			content = "FILE NOT FOUND";
			this.setCode(404);
			e1.printStackTrace();
		}
		this.setContent(content);
		createHeader();
		System.out.println("响应内容:");
		System.out.println(Header+Content);
		try {
			sender.write(Header+Content);
			sender.flush();
			sender.close();
			System.out.println("发送完毕...");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
