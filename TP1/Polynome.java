package TP1;

/**
 * 多项式类体
 * 在多项式中,单项式以链表的形式组合在一起
 * 多项式只需保存头单项式,并将以后的单项式以next的形式保存在前一个单项式中
 * 这样只需头单项式即可牵引出整个多项式
 * @author cedricxs
 *
 */
public class Polynome{
	//
	Monome premier;
	public Polynome() {
		
	}
	//多项式的复制构造函数,深拷贝
	public Polynome(Polynome another) {
		for(Monome i=another.premier;i!=null;i=i.next) {
			add(new Monome(i.coefficient,i.puissante));
		}
	}
	//往多项式中添加新的单项式,同时保证所有单项式以降幂排序
	public void add(Monome ele) {
		//如果头结点为空，则此单项式为头结点
		if(premier == null) {
			this.premier = ele;
		}
		else {
			//从头结点开始遍历多项式
			//it为当前遍历结点,t为下一个结点
			Monome it = premier;
			Monome t;
			//如果要添加的单项式指数大于或等于头结点指数,
			//则直接插在头结点之前(并另头结点为当前单项式)或头结点
			if(ele.puissante>it.puissante) {
				t = premier;
				this.premier = ele;
				ele.next = t;
				return ;
			}
			if(ele.puissante==it.puissante) {
				it.coefficient+=ele.coefficient;
				//如果头结点在插入新节点后系数为0,则将头结点设为下一个节点
				if(it.coefficient==0) {
					premier = premier.next;
				}
				return ;
			}
			//在中间插入
			while(it.hasNext()) {
				t = it.next;
				//找到插入位置
				if(ele.puissante<it.puissante&&ele.puissante>t.puissante) {
					ele.next = t;
					it.next = ele;
					return ;
				}
				if(ele.puissante==t.puissante) {
					t.coefficient+=ele.coefficient;
					if(t.coefficient==0) {
						it.next = t.next;
					}
					return ;
				}
				it=t;
			}
			//所有结点遍历结束,插入位置在最后位置新增加结点
			it.next = ele;
			ele.next = null;
		}
	}
	@Override
	public String toString() {
		if(premier==null)return "0";
		String res = "";
		for(Monome it = premier;it!=null;it=it.next) {
			res+=it.toString();
			if(it.hasNext()&&it.next.coefficient>0)res+="+";
		}
		return res;
	}
	public int valeur(int x) {
		if(premier==null)return 0;
		int res = 0;
		for(Monome i = this.premier;i!=null;i=i.next) {
			res+=i.value(x);
		}
		return res;
	}

}
