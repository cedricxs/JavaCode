package ChatRoom.ClientPack;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import ChatRoom.ServerPack.Util;

/**
 * 想要和某人聊天时，向服务器发送与某人聊天请求，服务器返回目标的IP地址与端口号,由客户端去端对端连接
 * 服务器相当于追踪器,P2P应用中，每个客户端是一个对等方
 * 但是对于P2P应用，NAT的出现阻碍了每个客户端成为TCP连接的接收方
 * 有方法可以在局域网中实现P2P,但是我还不会...
 * @author cedricxs
 *
 */
public class MainFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	String thisName;
	//与其他客户端端到端通信的本客户端服务器
	ServerSocket self;
	String friendlists;
	//此列表记录此客户端聊天的聊天框
	ArrayList<ChatFrame> chatWith;
	JTextField jt;
	Map<String,Boolean> FriendLoginStatus;
	//与服务器通信流
	DataOutputStream out ;	
	DataInputStream in;
	Socket connectServer;
	int port=30000;
	public MainFrame(Socket connectServer,String thisName) throws IOException {
		//启动客户端通信接收通道
		while(true) {
			try{
				self = new ServerSocket(port);
				break;
			}
			catch(Exception e) {
				port++;
			}
		}
		chatWith = new ArrayList<ChatFrame>();
		this.thisName = thisName;
		this.connectServer = connectServer;
		out = new DataOutputStream(connectServer.getOutputStream());	
		in = new DataInputStream(connectServer.getInputStream());
		Container container = this.getContentPane();
		container.setLayout(new GridLayout(5,1));
		
		try {
			//向服务器发送并在服务端注册此通信IP和端口
			//InetSocketAddress a  = new InetSocketAddress(InetAddress.getLocalHost(),port);
			//String ipport =a.getAddress().getHostAddress()+" "+a.getPort();
			out.writeUTF("UPDATEIP "+"172.168.10.6"+" "+port+" "+thisName);
			out.flush();
			//服务器返回好友列表及在线状态
			friendlists = in.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] friends = friendlists.split(" ");
		FriendLoginStatus = new HashMap<String,Boolean>(friends.length);
		for(int i=0;i<friends.length&&!friends[i].equals("");i++) {
			JButton jb = new JButton(friends[i].split(":")[0]);
			if(friends[i].split(":")[1].equals("false")) {
				jb.setBackground(Color.GRAY);
				FriendLoginStatus.put(friends[i].split(":")[0], false);
			}
			else {
				FriendLoginStatus.put(friends[i].split(":")[0], true);
				jb.setBackground(Color.PINK);
			}
			container.add(jb);
			//为button 添加时间监听,点击按钮，向服务器发送要建立连接的客户端姓名
			jb.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						
						//如果在线
						if(FriendLoginStatus.get(jb.getText())) {
							//向服务器发起连接请求通知
							out.writeUTF("CONNECT "+jb.getText());
							out.flush();
						}
						//如果不在线,发送给服务器
						else {
							ChatFrame c = new ChatFrame(thisName);
							c.setConnectSource(connectServer,"Server "+jb.getText());
							c.startInput();
							System.out.println(c.chatwithname);
							chatWith.add(c);
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			
			
		}
		jt = new JTextField(10);
		container.add(jt);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				try {
					out.writeUTF("OFFLINE "+thisName);
					out.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});
		this.setSize(300,300);
		this.setLocation(150,150);
		this.setVisible(true);
		new Thread(new ReceiveThread()).start();
		new Thread(new Accept()).start();
	}
	public void paint(Graphics g) {
		Container container = getContentPane();
		Component[] coms = container.getComponents();
		for(int i=0;i<coms.length;i++) {
			if(coms[i] instanceof JButton) {
				JButton b = (JButton)coms[i];
				if(FriendLoginStatus.get(b.getText())) {
					b.setBackground(Color.PINK);
				}
				else  {
					b.setBackground(Color.GRAY);
				}
			}
		}
	}
	
	//接收服务器返回的消息
	class ReceiveThread implements Runnable{
		private boolean isRunning = true;
		public ReceiveThread() {
		}


		public String receive() {
			try {
				String msg = in.readUTF();
				return msg;
			} catch (IOException e) {
				isRunning = false;
				Util.closeAll(in);
				return null;
			}
		}

		@Override
		public void run() {
			while(isRunning) {
				//客户端XXX请求连接IPxxxPortxxx
				String msg = receive();
				jt.setText(msg);
				System.out.println("服务器发来消息:"+msg);
				if(msg!=null&&msg.indexOf("ONLINE")>=0) {
					String NewLoginName = msg.substring(0,msg.indexOf("ONLINE"));
					FriendLoginStatus.put(NewLoginName,true);
					repaint();
					for(ChatFrame c:chatWith) {
						System.out.println(c.chatwithname);
						if(c.chatwithname.equals("Server "+NewLoginName)) {
							try {
								out.writeUTF("CONNECT "+NewLoginName);
								out.flush();
							} catch (IOException e) {
								e.printStackTrace();
							}
							
						}
					}
				}
				else if(msg!=null&&msg.indexOf("OFFLINE")>=0) {
					String NewLogoutName =  msg.substring(0,msg.indexOf("OFFLINE"));
					FriendLoginStatus.put(NewLogoutName,false);
					for(ChatFrame c:chatWith) {
						if(c.chatwithname.equals(NewLogoutName)) {
							c.setConnectSource(connectServer,"Server "+NewLogoutName);
							
						}
					}
					repaint();
				}
				else if(msg!=null) {
					//服务器返回要通信客户端所在IP PORT
					String Name = msg.split(":")[0];
					String IP = msg.split(":")[1];
					int port = Integer.parseInt(msg.split(":")[2]);
					Socket con = null;
					try {
						con = new Socket(IP,port);
						DataOutputStream o = new DataOutputStream(con.getOutputStream());
						o.writeUTF(thisName);
						o.flush();
					} catch (Exception e) {
						e.printStackTrace();
					} 
					for(ChatFrame c:chatWith) {
						if(c.chatwithname.equals(Name)) {
							c.setConnectSource(con,c.chatwithname);
							c.startInput();
							continue;
						}
					}
					ChatFrame c = new ChatFrame(thisName);
					c.setConnectSource(con,Name);
					c.startInput();
					chatWith.add(c);
				}
			}
			
		}

	}
	class Accept implements Runnable{

		@Override
		public void run() {
			while(true) {
				try {
					Socket chat = self.accept();
					DataInputStream i = new DataInputStream(chat.getInputStream());
					String chatFrom = i.readUTF();
					ChatFrame c = new ChatFrame(thisName);
					c.setConnectSource(chat,chatFrom);
					c.startInput();
					chatWith.add(c);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
	}
}

