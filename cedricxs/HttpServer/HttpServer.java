package cedricxs.HttpServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class HttpServer {
	ServerSocket server=null;
	Socket socket = null;
	request req;
	response res;
	private HttpServer(int port) throws IOException {
		//创建Socket服务器，监听port端口
		server = new ServerSocket(port);
		new Thread(new CloseThread(),"监听服务器关闭线程").start();
		//装载路由表
		SetRouter.setRouter();
		while(true) {
			//无限循环等待http请求,无并发处理
			socket = server.accept();
			//解析获取request对象
			req = Parser.parserRequest(socket);
			//初始化response对象
			res = new  response(socket.getOutputStream());	
			//根据请求路由找到相应路由服务
			try {
				ServerLet.routers.get(req.getURL()).handleFunction(req,res);
			}catch(NullPointerException e) {
				res.setCode(404);
				res.setContent("NOT FOUND");
				res.send();
			}
		}
		
		
	}
	
	
	
	public static HttpServer createHttpServer(int port) throws IOException {
		return new HttpServer(port); 
	}
	
	//监听服务器停止事件:服务器控制台输入EXIT
	class CloseThread implements Runnable{
		Scanner scanner = new Scanner(System.in);
		boolean isRunning = true;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(isRunning) {
				if(scanner.nextLine().equals("EXIT")) {
					System.out.println("IS SURE TO EXIT?");
					if(scanner.nextLine().equals("YES")) {
						try {
							server.close();
							scanner.close();
							isRunning = false;
						} catch (IOException e) {
							System.out.println("服务器关闭错误...");
						}
					}
				}
			}
		}
		
	}
}
