package ChatRoom.ClientPack;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.*;
public class ChatFrame extends JFrame{
	String thisName;
	JTextArea jt1;
	JTextArea record;
	private static final long serialVersionUID = 1L;
	String chatwithname;
	Socket client=null;
	public void setConnectSource(Socket s,String chatwithname) {
		client = s;
		this.chatwithname =chatwithname;
	}
	public ChatFrame(String thisName) { 
		this.thisName = thisName;
		Container container = this.getContentPane();
		container.setLayout(new FlowLayout());
		record = new JTextArea(12,40);
		JScrollPane jp = new JScrollPane(record);
		container.add(jp);
		jt1 = new JTextArea(7,40);
		
		JScrollPane jp1 = new JScrollPane(jt1);
		container.add(jp1);
		jt1.setToolTipText("请输入消息:");
		jt1.setText("请输入消息:");
		JButton jb = new JButton("发送"); 
		jb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					synchronized(client) {
						DataOutputStream out = new DataOutputStream(client.getOutputStream());
						if(chatwithname.indexOf("Server")>-1) {
							out.writeUTF("BUFFER "+chatwithname.split(" ")[1]+" "+thisName+" "+getmsgText());
							out.flush();
						}
						else {
							out.writeUTF(getmsgText());
						}
					}
				} 
				catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		jt1.addKeyListener(new KeyAdapter() {
			 public void keyPressed(KeyEvent e) {
				 int key = e.getKeyCode();
				 if(key==KeyEvent.VK_ENTER) {
					 try {
						DataOutputStream out = new DataOutputStream(client.getOutputStream());
						if(chatwithname.indexOf("Server")>-1) {
							out.writeUTF("BUFFER "+chatwithname.split(" ")[1]+" "+thisName+" "+getmsgText());
							out.flush();
						}
						else {
							out.writeUTF(getmsgText());
						}
					} 
					 catch (IOException e1) {
						e1.printStackTrace();
					}
				 }
			 }
		});
		container.add(jb);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				System.out.println("程序正常关闭...");
				try {
					if(chatwithname.indexOf("Server")==-1)
					client.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		this.setSize(500,450);
		this.setLocation(100,100);
		this.setVisible(true);
		//如果是服务器通信,不要占用服务器输入流
		
	}
	
	public void setmsgText(String msg) {
		record.setText(msg);
	}
	public String getmsgText() {
		return jt1.getText();
	}
	public void startInput() {
		if(chatwithname.indexOf("Server")==-1) {
			new Thread(new receive()).start();
		}
	}
	
	class receive implements Runnable{
		public receive() {
		}
		public String rece() throws IOException {
			String msg=null;
			DataInputStream in = new DataInputStream(client.getInputStream());
			msg = in.readUTF();
			return msg;
		}
		@Override
		public void run() {
			while(true) {
				try {
					setmsgText(chatwithname+"发来消息:"+rece());
				} catch (IOException e) {
					try {
						client.close();
						break;
					} catch (IOException e1) {
					}
					e.printStackTrace();
				}
			}
		}
		
	}
}


