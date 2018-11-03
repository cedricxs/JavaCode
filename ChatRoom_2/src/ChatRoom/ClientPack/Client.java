package ChatRoom.ClientPack;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import ChatRoom.ServerPack.Util;


												
public class Client {
	LoginFrame login;
	//与服务器通信的客户端
	Socket connectServer;
	
	MainFrame mainframe;
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		Client c = new Client();
		c.connectServer = new Socket("172.20.10.6",8889);
		//将与服务器通信的管道暂时移交给登录窗口
		c.login = new LoginFrame(c.connectServer);
		c.waitfor();
	}
	void waitfor() {
		new Thread(new login()).start();
	}
	class login implements Runnable{
		@Override
		public void run() {
			boolean loginstatus = false;
			while(!loginstatus) {
				synchronized(login) {
					//不断读取登录状态直到登陆成功
					loginstatus= login.loginsuccess;
				}
			}
			login.setVisible(false);
			//登录窗口将与服务器通信的管道移交给主窗口
			try {
				mainframe = new MainFrame(connectServer,login.username);
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}
}




class SendThread implements Runnable{
	private BufferedReader console;
	private DataOutputStream out;
	private boolean isRunning = true;
	public SendThread(Socket socket,ChatFrame chat) {
		this.console = new BufferedReader(new InputStreamReader(System.in));
		try {
			this.out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			isRunning = false;
			Util.closeAll(console,out);
		}
	}


	public void send() {
		String msg = getMsgFromConsole();
		if(msg!=null&&!msg.equals("")) {
			try {
				out.writeUTF(msg);
				out.flush();
			} catch (IOException e) {
				isRunning = false;
				Util.closeAll(console,out);
			}
		}
	}
	
	public String getMsgFromConsole() {
		try {
			return console.readLine();
		} catch (IOException e) {
			return "";
		}
		
	}
	@Override
	public void run() {
		while(isRunning) {
			send();
		}
		
	}

}