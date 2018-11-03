package ChatRoom.ServerPack;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import ChatRoom.ServerPack.Server.Channel;

public class Router {
	public static HashMap<String,ServerLet> routes = new HashMap<String,ServerLet>(); 
	public static void use(String url,ServerLet s) {
		routes.put(url, s);
	}
	static {
		use("LOGIN",new ServerLet(){
			void handleFunction(request req,Connection database,Channel channel,ArrayList<Channel> clients) throws IOException {
				String username = req.data.get("username");
				String pwd = req.data.get("pwd");
				QueryInfo<String,String> info = new QueryInfo<String,String>(username,pwd);
				if(ConnectSQL.getLogin(database,info)) {	
					channel.out.writeUTF("loginsuccess...");
					channel.out.flush();
				}
				else {
					channel.out.writeUTF("用户名或密码不正确...");
					channel.out.flush();
				}
			}
		});
		use("CONNECT",new ServerLet() {
			void handleFunction(request req,Connection database,Channel channel,ArrayList<Channel> clients) throws IOException {
				String username = req.data.get("ConnectToName");
				String res = ConnectSQL.getUserIP(database,username);
				channel.out.writeUTF(res);
				channel.out.flush();
			}
		});
		use("UPDATEIP",new ServerLet() {
			void handleFunction(request req,Connection database,Channel channel,ArrayList<Channel> clients) throws IOException {
				String name = req.data.get("THISNAME");
				String IP = req.data.get("IP");
				String PORT = req.data.get("PORT");
				ConnectSQL.updateLoginStatus(database, true,name );
				ConnectSQL.updateLoginIP(database,IP,PORT,name);
				channel.name = name;
				for(Channel c:clients) {
					if(c.name!=null&&!c.name.equals(channel.name)) {
						c.out.writeUTF(channel.name+"ONLINE");
						c.out.flush();
					}
				}
				String s = ConnectSQL.getFriends(database, name);
				channel.out.writeUTF(s);
				channel.out.flush();
				System.out.println(s);
			}
		});
		use("OFFLINE",new ServerLet() {
			void handleFunction(request req,Connection database,Channel channel,ArrayList<Channel> clients) throws IOException {
				String name = req.data.get("name");
				ConnectSQL.updateLoginStatus(database, false,name );
				ConnectSQL.updateLoginIP(database,"","",name);
				for(Channel c:clients) {
					if(!c.name.equals(channel.name)) {
						c.out.writeUTF(channel.name+"OFFLINE");
						c.out.flush();
					}
					else {
						clients.remove(c);
					}
				}
				channel.isRunning = false;
			}
		});
		use("BUFFER",new ServerLet() {
			void handleFunction(request req,Connection database,Channel channel,ArrayList<Channel> clients) throws IOException {
				String to = req.data.get("BUFFERTO");
				String from = req.data.get("FROM");
				String msg = req.data.get("MSG");
				ConnectSQL.addBufferMsg(database,msg,to,from);
			}
		});	
	}
}
