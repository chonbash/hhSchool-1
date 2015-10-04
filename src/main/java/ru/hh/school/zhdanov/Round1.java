package ru.hh.school.zhdanov;

import java.math.BigInteger;
import java.util.Arrays;

public class Round1 {
	public static void main(String[] args) {
		System.out.println("Problem1 : " + problem1(286).toString());
		System.out.println("Problem2 (1): " + problem2(180, 1000000).toString());
		System.out.print("Problem3 (2): ");
		problem3(1377);
		System.out.println("Problem4 (5): " + problem4(1001).toString());
		System.out.println("Problem5 : " + problem5(1195));
		System.out.println("Problem6 : " + problem6());

	}

	private static Integer problem1(Integer i) {
		BigInteger factorial = fact(i);
		String factorialString = factorial.toString();
		Character num;
		Integer sum = new Integer(0);
		for (int j = 0; j < factorialString.length(); j++) {
			num = factorialString.charAt(j);
			sum += Integer.parseInt(num.toString());
		}
		return sum;
	}

	private static BigInteger fact(Integer i) {
		BigInteger n = new BigInteger(i.toString());
		BigInteger factorial = new BigInteger("1");
		while (i > 0) {
			factorial = factorial.multiply(n);
			i--;
			n = n.subtract(BigInteger.ONE);
		}
		return factorial;
	}

	private static Long problem2(Integer n, Integer lim) {
		BigInteger c = new BigInteger("0");
		BigInteger l = new BigInteger(lim.toString());
		Long sum = new Long(0);
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < i; j++) {
				c = fact(i).divide(fact(j).multiply(fact(i - j)));
				if (c.compareTo(l) > 0) {
					sum++;
				}
			}
		}
		return sum;
	}

	private static void problem3(Integer n) {
		BigInteger el = new BigInteger(n.toString());
		BigInteger sum = new BigInteger("0");
		while (n > 0) {
			sum = sum.add(el.pow(n));
			n--;
			el = el.subtract(BigInteger.ONE);
		}
		String sumString = sum.toString();
		for (int i = 0; i < sumString.length(); i++) {
			if ((sumString.length() - 10) <= i) {
				System.out.print(sumString.charAt(i));
			}
		}
		System.out.println();
		// System.out.println(sum.toString());
	}

	private static String problem4(Integer i) {
		Integer elN = new Integer(3);
		BigInteger n_2 = new BigInteger("1");
		BigInteger n_1 = new BigInteger("1");
		BigInteger N = new BigInteger("2");
		while (true) {
			n_2 = n_1;
			n_1 = N;
			N = n_1.add(n_2);
			elN++;
			if (N.toString().length() >= i) {
				return elN.toString();
			}
		}

	}

	private static String problem5(Integer i) {
		BigInteger sum = new BigInteger("0");
		BigInteger n = new BigInteger(i.toString());
		n = n.pow(2);
		sum = sum.add(n);
		i--;
		while (i > 0) {
			for (int j = 0; j < 4; j++) {
				n = n.subtract(new BigInteger(i.toString()));
				sum = sum.add(n);
			}
			i -= 2;
		}
		return sum.toString();
	}
	
	private static String problem6() {
		Integer x = new Integer(0);
		Long a = new Long(0);
		Long b = new Long(0);
		char[] ac;
		char[] bc;
		
		while(x >= 0){
			x++;
			a = 3l * x;
			b = 5l * x;
			if (a.toString().length() == b.toString().length()){
				ac = a.toString().toCharArray();
				bc = b.toString().toCharArray();
				Arrays.sort(ac);
				Arrays.sort(bc);
				if (Arrays.equals(ac, bc)){
					return x.toString();
				}
			}
		}
		return x.toString();
	}

}
