package Pro;

import java.io.File;
/**
 * File���һЩ����
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
 * createNewFile()  �ļ������򷵻�false
 * delete()
 * mkdir()
 * mkdirs()
 * list()
 * listFiles()
 * listRoots()
 * @author ����
 * ��win�д��̷���Ϊ����·��
 * ���·����user.dir(��ǰ����Ŀ¼)Ϊ��Ŀ¼
 */
public class TestFile {
	public static void main(String[] args) {
		File f = new File("D:\\Notepad++");
		printFileTree(f,0);
		//File(parent,filename)��·��+�ļ���
		//File(file)�ļ��ľ���·��
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
		s.renameTo(dest);//��s��д��Ŀ���ļ���,��ɾ��ԭ��λ���ļ�
		System.out.println(dest.getPath());
		
		
		
		
		
	}
	/**
	 * ��ӡ���ĵ���
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
