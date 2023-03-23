package test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DateTest {
	public static void main(String[] args) throws Exception {
		 String date1="2023-01-01";
		 String date2="2023-01-02";

		 DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		 /* DateŸ������ ���� */

		 //Date d1 = format.parse( date1 );
		 //Date d2 = format.parse( date2 );
			Date d1 = java.sql.Date.valueOf(LocalDate.of(2023, 1, 1));
			Date d2 = java.sql.Date.valueOf(LocalDate.of(2023, 1, 5));		 
		 long Sec = (d1.getTime() - d2.getTime()) / 1000; // ��
		 long Min = (d1.getTime() - d2.getTime()) / 60000; // ��
		 long Hour = (d1.getTime() - d2.getTime()) / 3600000; // ��
		 long Days = Sec / (24*60*60); // ���ڼ�
	        
		 System.out.println(Sec + "�� ����");
		 System.out.println(Min + "�� ����");
		 System.out.println(Hour + "�� ����");
		 System.out.println(Days + "�� ����");
		 
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 String result = df.format(d1);
		 
		 System.out.println(result);
		 
		//  String keydate = String.valueOf(d1.getYear());
		 // System.out.println(keydate);
	}
}
