package Pro;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * ���ӻ���������
 * @author ����
 *
 */
public class VisualCalendar {
	public static void main(String[] args) {
		System.out.print("����������(���ո�ʽ:1970-1-1):");
		Scanner sc = new Scanner(System.in);
		String temp = sc.nextLine();
		sc.close();
		int x = 0;
		int n = 0;
		int nowday = 0;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = null;
		try {
			//�ɹ涨��ʽ����->Date SimpleDateFormatDateFormat(format).parse(String)
			//��Date->�涨��ʽ���� SimpleDateFormat(format).format(Date)
			Date date = format.parse(temp);
			calendar = new GregorianCalendar();
			calendar.setTime(date);
			nowday = calendar.get(Calendar.DATE);
			calendar.set(Calendar.DATE,1);
			x = calendar.get(Calendar.DAY_OF_WEEK);
			n = calendar.getActualMaximum(Calendar.DATE);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("��\tһ\t��\t��\t��\t��\t��");
		/*for(int i=-x+2;i<=n;i++) {
			if(i>0) {
				if(i==nowday) 
					System.out.print("_");
			System.out.print(i+"\t");
			
			}
			else {
				System.out.print("\t");
			}
			if((i+x-1)%7==0)
				System.out.println();
		}*/
		
		getRightpos(x);
		for(int i=1;i<=n;i++) {
			if(calendar.get(Calendar.DATE) == nowday)
				System.out.print("*");
			System.out.print(calendar.get(Calendar.DATE)+"\t");
			if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
				System.out.println();
			}
			calendar.add(Calendar.DATE, 1);
		}
		
	}
	
	private static void getRightpos(int startpos) {
		for(int i=0;i<startpos-1;i++) {
			System.out.print("\t");
		}
	}
}
