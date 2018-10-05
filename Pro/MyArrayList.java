package Pro;


/**
 * ��дArrayList����
 * @author ����
 * ���е��õ�System.arraycopy()����,��Ϊ�����������͵�ǳ����,ǣһ����ȫ��
 * ʹ�÷��ͺ�value������ΪObject,��ֻ��T�Ϳ�����
 * ��Ϊ����û�����飡
 * ��T = Object��ͬ�ڲ�������
 */
public class MyArrayList<T> /*implements List*/{
	/**
     * The value is used for Object storage.
     */
	private Object[] value;
	
    /**
     * The count is the number of Objects used.
     * �ڴˣ�count <= value.length-1
     * ��count == value.length,����expandCapacity()
     */
    private int count;
    
    /**
     * �޲ι��캯�� 
     */
	public MyArrayList() {
		value = new Object[16];
		//this(16);
	}
	
	/**
	 * ָ��size�Ĺ��캯��
	 * @param size
	 */
	public MyArrayList(int size) {
		value = new Object[size];
	}
	
	/**
	 * ���ƹ��캯��
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
	 * pushһ��Ԫ��
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
	 * pushһ��MyArrayList����
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
	 * ����������
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
	 * ��չ��������
	 */
	private void expandCapacity() {
		int newCapacity = (value.length<<1)+2;
		Object[] newValue = new Object[newCapacity];
		System.arraycopy(value, 0, newValue, 0, count);
//		for(int i=0;i<count;i++) {
//			newValue[i] = value[i];
//		}
		value = newValue;
		System.out.println("����������չ�ˣ���"+"\n"+count+"/"+value.length);
	}
	
	/**
	 * ��ȡָ��λ��Ԫ�ص�����
	 * @param index
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T getValueOf(int index)  {
		rangeCheck(index);
		return (T)value[index];
	}
	
	/**
	 * ��index����ֵ��Ϊany
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
	 * ��������ʹ����
	 * @return
	 */
	public int getCount() {
		return this.count;
	}
	
	/**
	 * ������������
	 * @return
	 */
	public int getCapacity() {
		return value.length;
	}
	
	/**
	 * ���������Ƿ�Ϊ��
	 * @return
	 */
	public boolean isEmpty() {
		return count == 0;
	}
	
	/**
	 * ����any�������е�����λ�ã���������ڣ�����-1
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
	 * ����any�������еķ���λ�ã���������ڣ�����-1
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
	 * ��ָ��λ�ò���Ԫ��any
	 * @param any
	 * @param index
	 */
	public void insert(Object any,int index) {
		rangeCheck(index);
		//count����==value.length-1
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
	 * ��ָ��λ�ò���MyArrayList����
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
	 * ɾ��ָ��λ��Ԫ��һ��Ԫ�ص�
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
	 * ɾ��ָ��λ�ÿ�ʼ��num��Ԫ��
	 * @param index
	 * @param num
	 */
	public void remove(int index,int length) {
		rangeCheck(index);
		System.arraycopy(value, index+length, value, index, count-index-length);
		count-=length;
	}

	/**
	 * ɾ����һ����ָ��Ԫ����ȵ�����
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
	 * ɾ��������ָ��Ԫ����ͬ������
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
	 * ��index1��index2λ�õ�Ԫ�ؽ���
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
	 * ������Ԫ�ط���
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
	 * λ���Ƿ�Խ���⺯�������Խ�磬��������
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
	 * ����Syso
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
	 * ����������
	 * @param args
	 */
	public static void main(String[] args) {
		MyArrayList<Object> myarr = new MyArrayList<Object>(2);
		myarr.append(new NewStudent());
		myarr.append("abc");
		//�������Զ�ת��ΪObject->Integer
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
		 * �˴���һ©��������ͨ��getValueOf()����ʵ��setValueOf()�Ĺ��ܣ����ҳ���
		 * ���Ҵ˴�Ԫ��Ϊ�����������ͣ����Ԫ�ض����øö���
		 */
		NewStudent str = (NewStudent)_myarr.getValueOf(4);
		str.name = new String("cedrixs");
		_myarr.setValueOf(888, 3);
		System.out.println(_myarr);
		System.out.println(_myarr.subArray(0, 2));
	}
}
