package cedricxs.HttpServer;

import java.util.HashMap;
import java.util.Map;

abstract public class ServerLet {
	abstract void handleFunction(request req,response res);
	public ServerLet() {

	}
	//路由表
	static Map<String,ServerLet> routers = new HashMap<String,ServerLet>();
	
	//路由建立
	//建立路由的过程时,实际上就是为服务器装载路由
	//request已经确定,根据URL来选定用哪个路由
	public static void use(String url,ServerLet router) {
		//将router装载入路由表,由查询req.getURL()使用某一路由
		ServerLet.routers.put(url,router);
	}
	
}
