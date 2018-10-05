package Pro;


/**
 * 重写ArrayList数组
 * @author 安迪
 * 所有调用到System.arraycopy()方法,均为引用数据类型的浅拷贝,牵一发动全身
 * 使用泛型后，value类型仍为Object,但只有T型可运行
 * 因为泛型没有数组！
 * 将T = Object等同于擦除泛型
 */
public class MyArrayList<T> /*implements List*/{
	/**
     * The value is used for Object storage.
     */
	private Object[] value;
	
    /**
     * The count is the number of Objects used.
     * 在此，count <= value.length-1
     * 若count == value.length,触发expandCapacity()
     */
    private int count;
    
    /**
     * 无参构造函数 
     */
	public MyArrayList() {
		value = new Object[16];
		//this(16);
	}
	
	/**
	 * 指定size的构造函数
	 * @param size
	 */
	public MyArrayList(int size) {
		value = new Object[size];
	}
	
	/**
	 * 复制构造函数
	 * @param another
	 */
	public MyArrayList(MyArrayList<T> another) {
		value = new Object[another.value.length];
//		for(int i=0;i<another.count;i++) {
//			value[i]=another.value[i];
//			}
		System.arraycopy(another.value, 0, value, 0, another.count);
		count = another.count;
	}
	
	
//	public void arrayCopy(Object[] src,int pos,Object[] dest,int destpos,int length) {
//		for(int i=pos;i<pos+length;i++) {
//			if(src[i] instanceof NewStudent) {
//				NewStudent ns = (NewStudent)src[i];
//				NewStudent n = (NewStudent) ns.clone();
//				dest[destpos++] = n;
//			}
//		}	
//	}
	
	/**
	 * push一个元素
	 * @param any
	 */
	public void append(T any) {
		value[count] = any;
		count++;
		if(count == value.length) {
			expandCapacity();
		}
	}
	
	/**
	 * push一个MyArrayList对象
	 * @param another
	 */
	public void append(MyArrayList<T> another) {
		while(value.length <= count+another.count) {
			expandCapacity();
		}
		System.arraycopy(another.value, 0, value, count, another.count);
		count+=another.count;
	}
	
	/**
	 * 返回子容器
	 * @param index
	 * @param length
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public MyArrayList<T> subArray(int index,int length) {
		MyArrayList<T> res = new MyArrayList<T>();
		for(Object obj:value) {
			res.append((T)obj);
		}
		res.count = length;
		return res;
	}
	/**
	 * 扩展容器容量
	 */
	private void expandCapacity() {
		int newCapacity = (value.length<<1)+2;
		Object[] newValue = new Object[newCapacity];
		System.arraycopy(value, 0, newValue, 0, count);
//		for(int i=0;i<count;i++) {
//			newValue[i] = value[i];
//		}
		value = newValue;
		System.out.println("容器容量扩展了！！"+"\n"+count+"/"+value.length);
	}
	
	/**
	 * 获取指定位置元素的引用
	 * @param index
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T getValueOf(int index)  {
		rangeCheck(index);
		return (T)value[index];
	}
	
	/**
	 * 将index处的值设为any
	 * @param any
	 * @param index
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T setValueOf(T any,int index) {
		rangeCheck(index);
		T OldValue = (T)value[index];
		value[index] = any;
		return OldValue;
	}
	
	/**
	 * 返回容器使用量
	 * @return
	 */
	public int getCount() {
		return this.count;
	}
	
	/**
	 * 返回容器容量
	 * @return
	 */
	public int getCapacity() {
		return value.length;
	}
	
	/**
	 * 返回容器是否为空
	 * @return
	 */
	public boolean isEmpty() {
		return count == 0;
	}
	
	/**
	 * 返回any在容器中的正向位置，如果不存在，返回-1
	 * @param any
	 * @return
	 */
	public int indexOf(Object any) {
		for(int i=0;i<count;i++) {
			if(value[i].equals(any))
				return i;
		}
		return -1;
	}
	
	/**
	 * 返回any在容器中的反向位置，如果不存在，返回-1
	 * @param any
	 * @return
	 */
	public int lastIndexOf(Object any) {
		for(int i=count;i>=0;i--) {
			if(value[i].equals(any))
				return i;
		}
		return -1;
	}
	
	/**
	 * 在指定位置插入元素any
	 * @param any
	 * @param index
	 */
	public void insert(Object any,int index) {
		rangeCheck(index);
		//count至多==value.length-1
		for(int i=count-1;i>=index;i--) {
			value[i+1] = value[i]; 
		}
		value[index] = any;
		count++;
		if(count == value.length) {
			expandCapacity();
		}
	}
	
	/**
	 * 在指定位置插入MyArrayList对象
	 * @param index
	 * @param another
	 */
	public void insert(int index,MyArrayList<T> another) {
		rangeCheck(index);
		while(value.length <= count+another.count) {
			expandCapacity();
		}
		System.arraycopy(value, index, value, index+another.count, count-index);
		System.arraycopy(another.value, 0, value, index, another.count);
		count+=another.count;
	}
	
	/**
	 * 删除指定位置元素一个元素的
	 * @param index
	 */
	public void remove(int index) {
		rangeCheck(index);
		for(int i=index;i<count-1;i++) {
			value[i] = value[i+1];
		}
		count--;
	}
	
	/**
	 * 删除指定位置开始的num个元素
	 * @param index
	 * @param num
	 */
	public void remove(int index,int length) {
		rangeCheck(index);
		System.arraycopy(value, index+length, value, index, count-index-length);
		count-=length;
	}

	/**
	 * 删除第一个与指定元素相等的数据
	 * @param tar
	 */
	public void remove(Object tar) {
		for(int i=0;i<this.count;i++) {
			if(value[i].equals(tar)) {
				this.remove(i);
				break;
			}
		}
	}
	
	/**
	 * 删除所有与指定元素相同的数据
	 * @param tar
	 */
	public void removeAll(Object tar) {
		for(int i=0;i<this.count;i++) {
			if(value[i].equals(tar)) {
				this.remove(i);
			}
		}
	}
	
	
	
	/**
	 * 将index1与index2位置的元素交换
	 * @param index1
	 * @param index2
	 */
	public void swap(int index1,int index2) {
		rangeCheck(index1);
		rangeCheck(index2);
		Object temp = value[index1];
		value[index1] = value[index2];
		value[index2] = temp;
	}
	
	/**
	 * 将容器元素反置
	 */
	public void reverse() {
		int n = (count>>1)-1;
		for(int i=n;i>=0;i--)
		{
			int j = count-i-1;
			swap(i,j);
		}
	}
	
	/**
	 * 位置是否越界检测函数，如果越界，则程序结束
	 * @param index
	 */
	private void rangeCheck(int index) {
		if(index<0||index>=value.length) {
			try {
				throw new Exception();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 用于Syso
	 */
	public String toString() {
		String res = new String();
		for(int i=0;i<count;i++) {
			res+="MyArrayList["+i+"]:";
			res+=value[i].toString()+" <"+value[i].getClass().getName()+">";
			res+='\n';
		}
		return res;
	}
	
	
	/**
	 * 测试主函数
	 * @param args
	 */
	public static void main(String[] args) {
		MyArrayList<Object> myarr = new MyArrayList<Object>(2);
		myarr.append(new NewStudent());
		myarr.append("abc");
		//将数字自动转化为Object->Integer
		myarr.append(12);
//		System.out.println(myarr.getValueOf(2));
//		System.out.println(myarr.getCount());
//		System.out.println(myarr.getCapacity());
//		System.out.println(myarr.indexOf("abc"));
		myarr.setValueOf(new NewStudent("Aline",20),1);
//		System.out.println(myarr);
		myarr.insert(456,2);
//		System.out.println(myarr);
		myarr.remove(2);
//		System.out.println(myarr);
		myarr.swap(0, 1);
//		System.out.println(myarr);
		myarr.append("def");
		myarr.append("xyz");
		myarr.append("uvw");
//		System.out.println(myarr);
		myarr.reverse();
//		System.out.println(myarr);
		MyArrayList<Object> _myarr = new MyArrayList<Object>(myarr);
//		System.out.println(_myarr);
		_myarr.append(myarr);
		_myarr.append(myarr);
		_myarr.remove(0,6);
		_myarr.insert(10,myarr);
		/**
		 * 此处有一漏洞，可以通过getValueOf()方法实现setValueOf()的功能，非我初衷
		 * 并且此处元素为引用数据类型，多个元素都引用该对象
		 */
		NewStudent str = (NewStudent)_myarr.getValueOf(4);
		str.name = new String("cedrixs");
		_myarr.setValueOf(888, 3);
		System.out.println(_myarr);
		System.out.println(_myarr.subArray(0, 2));
	}
}
