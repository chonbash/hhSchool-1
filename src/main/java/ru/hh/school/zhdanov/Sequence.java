package ru.hh.school.zhdanov;

import java.util.Date;

public class Sequence {
	
	private static String s = "0123456789101112131415";
	private static Integer n = new Integer(15);
	
	public static String getS() {
		return s;
	}
	
	public void addElement() {
		n++;
		if (n>0){
			s = s + n.toString();
			if (n % 10000 == 0){
			System.out.print(n + "---");
			System.out.println(new Date());}
		}
	}

}
