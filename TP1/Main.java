package TP1;
/**
 * TP1:
 * 首先构建单项式类,形如ax^n,可以计算两个单项式和,单项式积,并计算x为特定值时单项式的值
 * 然后构建多项式,包含多个单项式,单项式以指数降幂排序,
 * 实现多项式加,乘,辗转相除求商和余数,并计算x为特定值时多项式的值
 * @author cedricxs
 * Class Main: 测试类
 * Class Monome:单项式类体及一些非静态方法
 * Class Polynome:多项式类体及一些非静态方法
 * Class Util:有关单项式和多项式处理的静态方法工具类
 */
public class Main {
	public static void main(String[] args) {
		Polynome a1 = new Polynome();
		a1.add(new Monome(1,3));
		a1.add(new Monome(1,2));
		a1.add(new Monome(-1,0));
		Polynome a2 = new Polynome();
		a2.add(new Monome(1,2));
		a2.add(new Monome(-1,0));
		Polynome quotient = new Polynome();
		Polynome rest = new Polynome();
		Util.division(quotient, rest, a1, a2);
		System.out.println(a1+" division with "+a2);
		System.out.println("quotient= "+quotient);
		System.out.println("rest= "+rest);
	}
}


