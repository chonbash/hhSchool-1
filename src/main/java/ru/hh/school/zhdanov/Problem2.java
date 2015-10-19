package ru.hh.school.zhdanov;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem2 {

	private static String s = "0123456789101112131415";
	private static Integer n = new Integer(15);

	public static void run() throws Exception {
		System.out.println("2. Бесконечная последовательность");
		System.out
				.println("Возьмём бесконечную цифровую последовательность, образованную склеиванием последовательных положительных");
		System.out.println("чисел: S = 123456789101112131415...");
		System.out
				.println("Определите первое вхождение заданной последовательности A в бесконечной последовательности S (нумерация");
		System.out.println("начинается с 1).");
		ArrayList<String> searchList = new ArrayList<String>();
		getListFromFile(searchList);
		brute(searchList);
		intelecSearch(searchList);
	}

	private static void intelecSearch(ArrayList<String> searchList) {
		
		
	}

	private static void brute(ArrayList<String> searchList) {
		if (searchList.size() < 1) {
			System.err.println("There are no any subsequence at file.");
			return;
		}
		for (String subsequence : searchList) {
			while (n > 0) {
				int i = s.indexOf(subsequence);
				if (i > 0) {
					System.out.println("Search supsequance: " + subsequence
							+ " | Position: " + i);
					break;
				}
				n++;
				System.out.println(n);
				s = s + n.toString();
			}
		}
		return;
	}

	private static void getListFromFile(ArrayList<String> searchList)
			throws Exception {
		Path path = Paths.get("searchList.txt");
		List<String> fileData = Files.readAllLines(path);
		Pattern pattern = Pattern.compile("^(\\d+)$");
		Matcher matcher;
		for (String line : fileData) {
			matcher = pattern.matcher(line);
			if (matcher.matches()) {
				searchList.add(matcher.group(1));
			} else {
				System.err.println("Line has invalid format: " + line);
			}
		}

	}
	
	public static String getS() {
		return s;
	}

}
