package monitor;

import java.time.LocalDate;
import java.util.Date;

public class test {

	public static void main(String[] args) {
		
		LocalDate ldt = LocalDate.now();
		Date d = new Date();
		java.sql.Date s = new java.sql.Date(d.getTime());
		System.out.println(ldt);
		System.out.println(d);		
		System.out.println(s);		
	}
	
}
