package ChatRoom.ClientPack;

import java.awt.Container;
import javax.swing.*;
import java.awt.GridLayout;
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



public class LoginFrame  extends JFrame{

	private static final long serialVersionUID = 1L;
	DataOutputStream out ;	
	DataInputStream in;
	String username;
	JTextField jt1;
	JPasswordField jt2;
	boolean loginsuccess = false;
	public String getmsgText() {
		String msg = "LOGIN ";
		msg+=jt1.getText();
		msg+=" ";
		msg+=new String(jt2.getPassword());
		username = jt1.getText();
		System.out.println(msg);
		return msg;
	}
	public LoginFrame(Socket connectServer) {
		try {
			out = new DataOutputStream(connectServer.getOutputStream());
			in = new DataInputStream(connectServer.getInputStream());
		} catch (IOException e2) {
			e2.printStackTrace();
		}	
		Container container = this.getContentPane();
		container.setLayout(new GridLayout(3,2));
		JLabel jl1 = new JLabel("用户名:");
		jt1 = new JTextField(20);
		container.add(jl1);
		container.add(jt1);
		JLabel jl2 = new JLabel("　密码:");
		jt2 = new JPasswordField(20);
		container.add(jl2);
		container.add(jt2);
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				System.out.println("程序正常关闭...");
				System.exit(0);
			}
		});
		JButton jb = new JButton("登录"); 
		container.add(jb);
		JLabel jl3 = new JLabel("提示信息");
		container.add(jl3);
		//将用户名和密码信息写入通信流
		jb.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					//发送登录信息到服务器
					DataOutputStream out = new DataOutputStream(connectServer.getOutputStream());
					out.writeUTF(getmsgText());
					out.flush();
					//读取服务器返回登录状态
					DataInputStream in = new DataInputStream(connectServer.getInputStream());
					String res = in.readUTF();
					if(res.equals("loginsuccess...")) {
						loginsuccess = true;
					}
					else {
						jl3.setText("提示信息:"+res);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		jt2.addKeyListener( new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()=='\n') {
					try {
						//发送登录信息到服务器
						DataOutputStream out = new DataOutputStream(connectServer.getOutputStream());
						out.writeUTF(getmsgText());
						out.flush();
						//读取服务器返回登录状态
						DataInputStream in = new DataInputStream(connectServer.getInputStream());
						String res = in.readUTF();
						if(res.equals("loginsuccess...")) {
							loginsuccess = true;
						}
						else {
							jl3.setText("提示信息:"+res);
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		this.setSize(300,120);
		this.setLocation(550,300);
		this.setVisible(true);
	}

}
