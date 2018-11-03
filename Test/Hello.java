package Test; 
import java.awt.*; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import javax.swing.*;
public class Hello extends JFrame { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String a2="";
	int pd; 
	public Hello() { 
		Container c1 = getContentPane();
		getContentPane().setLayout(new FlowLayout());
		JLabel jl1 =new JLabel("请输入要相加减的两个数：");
		JLabel jl2 =new JLabel("选择输入的方法："); 
		JLabel jl3 =new JLabel("结果："); 
		JTextField jt1 = new JTextField(10); 
		JTextField jt2 = new JTextField(10); 
		JTextField jt3 = new JTextField(20);
		jt3.setEditable(false); 
		JButton jb = new JButton("运算"); 
		jb.setBorderPainted(true);
		//设置按钮显示边界
		JRadioButton jr1 = new JRadioButton("加法");
		//单选按钮 
		JRadioButton jr2 = new JRadioButton("减法"); 
		ButtonGroup group = new ButtonGroup();
		//没有这句，你的单选按钮就不叫单选按钮，可以自己去掉试试 
		group.add(jr1); 
		group.add(jr2); 
		jb.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent arg0){
				//弹出对话框
				String e1 = jt1.getText();
				//输入的数据 
				String e2 = jt2.getText(); 
				int e11 =Integer.parseInt(e1); 
				int e22 = Integer.parseInt(e2); 
				if(pd==1){ 
					int a = e11+e22; 
					a2 = String.valueOf(a); 
					} 
				else if(pd==2) { 
					int a = e11-e22;
					a2 = String.valueOf(a);
					} 
				jt3.setText(a2);
				} 
			}); 
		jr1.addActionListener( new ActionListener() { 
			@Override public void actionPerformed(ActionEvent arg0) {
				 pd=1; 
				 }
			} ); 
		jr2.addActionListener(new ActionListener() { 
			@Override public void actionPerformed(ActionEvent arg0) { 
				 pd=2; 
				 }
			} ); 
		c1.add(jl1); 
		c1.add(jt1); 
		c1.add(jt2); 
		c1.add(jl2); 
		c1.add(jr1); 
		c1.add(jr2); 
		c1.add(jl3); 
		c1.add(jt3); 
		c1.add(jb); 
		setSize(420, 200); 
		setVisible(true); 
		} 
	public static void main(String args[]) { 
		new Hello(); 
		}
}
