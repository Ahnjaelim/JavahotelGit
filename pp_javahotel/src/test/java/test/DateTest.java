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

		 /* Date타입으로 변경 */

		 //Date d1 = format.parse( date1 );
		 //Date d2 = format.parse( date2 );
			Date d1 = java.sql.Date.valueOf(LocalDate.of(2023, 1, 1));
			Date d2 = java.sql.Date.valueOf(LocalDate.of(2023, 1, 5));		 
		 long Sec = (d1.getTime() - d2.getTime()) / 1000; // 초
		 long Min = (d1.getTime() - d2.getTime()) / 60000; // 분
		 long Hour = (d1.getTime() - d2.getTime()) / 3600000; // 시
		 long Days = Sec / (24*60*60); // 일자수
	        
		 System.out.println(Sec + "초 차이");
		 System.out.println(Min + "분 차이");
		 System.out.println(Hour + "시 차이");
		 System.out.println(Days + "일 차이");
		 
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 String result = df.format(d1);
		 
		 System.out.println(result);
		 
		//  String keydate = String.valueOf(d1.getYear());
		 // System.out.println(keydate);
	}
}
