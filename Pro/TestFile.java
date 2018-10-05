package Pro;

import java.io.File;
/**
 * File类的一些方法
 * getName()
 * getPath()
 * getAbsoluteFile()
 * getAbsolutePath()
 * getParent()
 * renameTo(File newName)
 * exists()
 * canWrite()
 * canRead()
 * isFile()
 * isDirectory()
 * isAbsolute()
 * length()
 * createNewFile()  文件存在则返回false
 * delete()
 * mkdir()
 * mkdirs()
 * list()
 * listFiles()
 * listRoots()
 * @author 安迪
 * 在win中带盘符的为绝对路径
 * 相对路径以user.dir(当前工程目录)为根目录
 */
public class TestFile {
	public static void main(String[] args) {
		File f = new File("D:\\Notepad++");
		printFileTree(f,0);
		//File(parent,filename)父路径+文件名
		//File(file)文件的绝对路径
		String filename = "LICENSE";
		File s = new File(f,filename);
		System.out.println(s.getAbsolutePath());
		System.out.println(s.getName());
//		System.out.println(s.getParentFile().equals(f));
		File[] fs = f.listFiles();
		for(int i=0;i<fs.length;i++) {
			System.out.println(fs[i].getName());
		}
		System.out.println(s.length());
		File dest = new File("License");
		s.renameTo(dest);//将s重写到目标文件中,并删除原处位置文件
		System.out.println(dest.getPath());
		
		
		
		
		
	}
	/**
	 * 打印出文档树
	 * @param f
	 * @param level
	 */
	private static void printFileTree(File f,int level) {
		for(int i=0;i<level;i++) {
			System.out.print("-");
		}
		System.out.println(f.getName());
		if(f.isDirectory()) {
			File[] fs = f.listFiles();
			for(File c:fs) {
			printFileTree(c, level+1);
			}
		}
	
	}
}
