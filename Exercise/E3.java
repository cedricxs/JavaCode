package Exercise;
import java.math.BigInteger;
public class E3 {
	public static void main(String[] args) {
		Complex c = new Complex(2,3);
		Complex res ;
		//res = quickPow(c,2);
		//System.out.println(res.a);
		System.out.println(quickPow(c,123456));
	}
	public static Complex quickPow(Complex m,int n) {
		if(n==0) {
			return new Complex(1,0);
		}
		Complex temp = quickPow(m,n/2);
		temp = temp.mul(temp);
		if(n%2!=0) {
			temp = temp.mul(m);
		}
		return temp;
	}
}


class Complex{
	BigInteger a;
	BigInteger b;
	public Complex(BigInteger a,BigInteger b) {
		this.b = b;
		this.a = a;
	}
	public Complex(int a,int b) {
		this.b = BigInteger.valueOf(b);
		this.a = BigInteger.valueOf(a);
	}
	public Complex mul(Complex an) {
		BigInteger resa = this.a.multiply(an.a).subtract(this.b.multiply(an.b));
		BigInteger resb = this.b.multiply(an.a).add(this.a.multiply(an.b));
		return new Complex(resa,resb);
	}
	public String toString() {
		String res = "";
		res+=a.toString();
		res+="+i";
		res+=b.toString();
		return res;
	}
}
