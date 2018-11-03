package TP1;

/**
 * 单项式类体
 * @author cedricxs
 *
 */
public class Monome {
	//记录此单项式的下一个单项式,在多项式中会用到
	Monome next;
	//此单项式的系数
	double coefficient;
	//此单项式的指数
	int puissante;
	//含系数和指数的构造函数
	public Monome(double coefficient,int puissante) {
		this.coefficient = coefficient;
		this.puissante = puissante;
		next = null;
	}
	//计算此单项式x为输入值时的值
	public double value(int x) {
		return  coefficient*Math.pow(x, puissante);
	}
	//重写toString()方法,此方法会在System.out.println()是自动调用,用以确定打印单项式的结果
	public String toString() {
		return puissante==0?String.valueOf(coefficient):new String(coefficient+"x^"+puissante);
	}
	//计算此单项式与另一单项式的积,系数相乘,指数相加
	public void mul(Monome another) {
		this.coefficient *=another.coefficient;
		this.puissante +=another.puissante;
	}
	//判断此单项式是否还有后继,在多项式中用到
	public boolean hasNext() {
		return this.next==null?false:true;
	}
	//返回此单项式的相反单项式,用于计算单项式的减法
	public  Monome negative() {
		return new Monome(-coefficient,puissante);
	}
}
