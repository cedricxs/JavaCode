package cedricxs.HttpServer;

import java.util.HashMap;
import java.util.Map;

public class request {
	Map<String,String> content = new HashMap<String,String>();
	//根据接受的字符串解析出request对象,包含所有请求信息
	public request(String receive) {
		String[] infos = receive.split("\n");
		content.put("method",infos[0].split(" ")[0]);
		content.put("URL",infos[0].split(" ")[1] );
		content.put("http-v", infos[0].split(" ")[2]);
		for(int i=1;i<infos.length&&!infos[i].isBlank();i++) {
			String[] temp = infos[i].split(": ");
			content.put(temp[0], temp[1]);
		}
		System.out.println(content.entrySet().toString());
	}
	//一些request的常用方法
	public String query(){
		if(content.get("method").equals("GET")) {
			String temp =  content.get("URL");
			int t = temp.indexOf("?");
			return t==-1? "":new String(temp.substring(t+1));
		}
		return "";
	}
	public String getURL() {
		return content.get("URL");
	}
}
