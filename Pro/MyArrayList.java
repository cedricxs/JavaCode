package Pro;
/**
 * 重写ArrayList数组
 * @author 安迪
 *
 */
public class MyArrayList {
	/**
     * The value is used for Object storage.
     */
	private Object[] value;
	
    /**
     * The count is the number of Objects used.
     */
    private int count;

	public MyArrayList() {
		value = new Object[16];
		//this(16);
	}
	
	public MyArrayList(int size) {
		value = new Object[size];
	}
	
	public void append(Object any) {
		value[count] = any;
		count++;
		if(count == value.length) {
			expandCapacity();
		}
	}
	
	private void expandCapacity() {
		int newCapacity = (value.length<<1)+2;
		Object[] newValue = new Object[newCapacity];
		for(int i=0;i<count;i++) {
			newValue[i] = value[i];
		}
		value = newValue;
	}
	
	public Object get(int index)  {
		rangeCheck(index);
		return value[index];
	}
	
	public Object set(Object any,int index) {
		rangeCheck(index);
		Object OldValue = value[index];
		value[index] = any;
		return OldValue;
	}
	
	public int getCount() {
		return this.count;
	}
	
	public int getCapacity() {
		return value.length;
	}
	
	public boolean isEmpty() {
		return count == 0;
	}
	
	public int indexOf(Object any) {
		for(int i=0;i<value.length;i++) {
			if(value[i].equals(any))
				return i;
		}
		return -1;
	}
	
	public int lastIndexOf(Object any) {
		for(int i=count;i>=0;i--) {
			if(value[i].equals(any))
				return i;
		}
		return -1;
	}
	
	
	private void rangeCheck(int index) {
		if(index<0||index>=count) {
			try {
				throw new Exception();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
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
	
	public void delete(int index) {
		rangeCheck(index);
		for(int i=index;i<count-1;i++) {
			value[i] = value[i+1];
		}
		count--;
	}
	
	public String toString() {
		String res = new String();
		for(int i=0;i<count;i++) {
			res+="MyArrayList["+i+"]:";
			res+=value[i].toString()+" <"+value[i].getClass().getName()+">";
			res+='\n';
		}
		return res;
	}
	
	public void swap(int index1,int index2) {
		rangeCheck(index1);
		rangeCheck(index2);
		Object temp = value[index1];
		value[index1] = value[index2];
		value[index2] = temp;
	}
	
	public void reverse() {
		int n = (count>>1)-1;
		for(int i=n;i>=0;i--)
		{
			int j = count-i-1;
			swap(i,j);
		}
	}
	
	public static void main(String[] args) {
		MyArrayList myarr = new MyArrayList(2);
		myarr.append(new NewStudent());
		myarr.append("abc");
		myarr.append(12);
		System.out.println(myarr.get(2));
		System.out.println(myarr.getCount());
		System.out.println(myarr.getCapacity());
		System.out.println(myarr.indexOf("abc"));
		System.out.println(myarr);
		myarr.insert(456,2);
		System.out.println(myarr);
		myarr.delete(1);
		System.out.println(myarr);
		myarr.swap(0, 1);
		System.out.println(myarr);
		myarr.append("def");
		myarr.append("xyz");
		myarr.append("uvw");
		myarr.reverse();
		System.out.println(myarr);
	}
}
