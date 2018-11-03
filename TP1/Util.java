package TP1;

public class Util {
	//计算两个单项式的积
	public static Monome mul(Monome a,Monome b) {
		return new Monome(a.coefficient*b.coefficient,a.puissante+b.puissante);
	}
	
	//计算两个单项式的商,系数相除,指数相减
	public static Monome division(Monome a,Monome b) {
		return mul(a,new Monome(1/b.coefficient,-b.puissante));
	}
	
	//计算两个多项式的乘积并返回新的多项式
	public static Polynome mul(Polynome a,Polynome b) {
		Polynome res = new Polynome();
		for(Monome i = a.premier;i!=null;i=i.next) {
			for(Monome j = b.premier;j!=null;j=j.next){
				res.add(Util.mul(i, j));
			}
		}
		return res;
	}
	//计算多项式与一个单项式的乘积
	public static Polynome mul(Polynome a,Monome b) {
		Polynome res = new Polynome();
		for(Monome i = a.premier;i!=null;i=i.next) {
				res.add(Util.mul(i, b));
		}
		return res;
	}
	//计算两个多项式的相减的结果,多项式a减去多项式b的每个单项式
	public static Polynome moin(Polynome a,Polynome b) {
		Polynome res = new Polynome(a);
		for(Monome i = b.premier;i!=null;i=i.next) {
			res.add(i.negative());
		}
		return res;
	}
	// 计算a/b,商放在quotient里,余数放在rest里
	public static void division(Polynome quotient,Polynome rest,Polynome a,Polynome b) {
		while(true) {
			Monome t = Util.division(a.premier, b.premier);
			quotient.add(t);
			Polynome mul = Util.mul(b, t);
			a = Util.moin(a,mul);
			if(t.puissante==0) {
				rest.add(a.premier);
				break;
			}
		}
	}
}
