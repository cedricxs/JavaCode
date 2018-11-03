package ChatRoom.ServerPack;

import java.io.Serializable;
import java.util.HashMap;

//规定服务器和客户端传递数据的格式
public class request implements Serializable{
	private static final long serialVersionUID = 1L;
	//指令集合:LOGIN,OFFLINE,CONNECT,ADDFRIEND,BUFFER
	//该请求的指令
	String URL;
	public HashMap<String,String> data  = new HashMap<String,String>();
	public String getURL() {
		return URL;
	}
	public static  request parse(String info) {
		System.out.println(info);
		String com = info.split(" ")[0];
		request req = new request();
		req.URL = com;
		switch(com) {
		case "LOGIN":
			req.data.put("username", info.split(" ")[1]);
			req.data.put("pwd",info.split(" ")[2]);
			break;
		case "OFFLINE":
			req.data.put("name", info.split(" ")[1]);
			break;
		case "CONNECT":
			req.data.put("ConnectToName", info.split(" ")[1]);
			break;
		case "UPDATEIP":
			req.data.put("IP", info.split(" ")[1]);
			req.data.put("PORT",info.split(" ")[2]);
			req.data.put("THISNAME",info.split(" ")[3]);
			break;
		case "BUFFER":
			req.data.put("BUFFERTO", info.split(" ")[1]);
			req.data.put("FROM",info.split(" ")[2]);
			req.data.put("MSG",info.split(" ")[3]);
			break;
		}
		return req;
	}	
}

