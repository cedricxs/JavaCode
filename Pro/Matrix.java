package Pro;
/**
 * 用父类引用子类，运行时仍是子类类型！
 * @author 安迪
 *
 */
public class Matrix {
	private int[][] value; 
	
	public Matrix(int row,int con) {
		this.value = new int[row][];
		for(int i=0;i<row;i++) {
			this.value[i] = new int[con];
		}
	}
	public Matrix(int[][] value) {
		this.value = new int[value.length][];
		for(int i=0;i<value.length;i++) {
			this.value[i] = new int[value[i].length];
			for(int j=0;j<value[i].length;j++) {
				this.value[i][j] = value[i][j];
			}
		}
	}
	
	public Matrix add(Matrix another) {
		if(this.value.length!=another.value.length||
			this.value[0].length!=another.value[0].length) {
			try {
				throw new Exception("不可加");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Matrix res = new Matrix(value.length,value[0].length);
		for(int i=0;i<value.length;i++)
			for(int j=0;j<value[i].length;j++) {
				res.value[i][j] = this.value[i][j] + another.value[i][j];
			}
		return res;
	}
	
	public void print() {
			for(int i=0;i<value.length;i++) {
				for(int j=0;j<value[i].length;j++) { 
					System.out.print(value[i][j]+"\t");
				}
				System.out.println();
			}
	}
		
	public static void main(String[] args) {
		
		int[][] v = {
				{1,2,0},
				{4,5,6},
				{7,8,9}
			};
		int[][] k = {
				{1,2,0},
				{4,5,6},
				{7,8,9}
			};
		Matrix m = new Matrix(v);
		Matrix n = new Matrix(k);
		m.print();
		Matrix r = m.add(n);
		r.print();
	}
}
