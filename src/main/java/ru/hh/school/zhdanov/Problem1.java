package ru.hh.school.zhdanov;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem1 {
	
	
	
	public static void run() throws Exception {
		System.out.println("1. Минимальное расстояние");
		System.out.println("Дан набор из N точек на плоскости (для простоты можно считать, что у всех точек целочисленные координаты).");
		System.out.println("Найдите минимальное расстояние между двумя точками из этого набора.");
		ArrayList<Point> pointsList = new ArrayList<Point>();
		getListFromFile(pointsList);
		brute(pointsList);
	}

	private static void brute(ArrayList<Point> pointsList) {
		if (pointsList.size() < 2) {
			System.err.println("There are not enough points specified.");
			return;
		}
		double minD = pointsList.get(1).distance(pointsList.get(2));
		for (int i = 1; i <= pointsList.size()-1; i++) {
			for (int j = i+1; j <=pointsList.size() ; j++) {
				if (pointsList.get(i).distance(pointsList.get(j)) < minD ) {
					minD = pointsList.get(i).distance(pointsList.get(j));
				}
			}
		}
		System.out.println("Minimal distance is: " + minD);
		return;
	}

	private static void getListFromFile(ArrayList<Point> pointsList) throws Exception {
		Path path = Paths.get("points.txt");
	    List<String> fileData = Files.readAllLines(path);
	    Pattern pattern = Pattern.compile("^(\\d+) (\\d+)$");
	    Matcher matcher;
	    for (String line : fileData) {
			matcher = pattern.matcher(line);
			if (matcher.matches()) {
				int x = Integer.parseInt(matcher.group(1));
				int y = Integer.parseInt(matcher.group(2));
				pointsList.add(new Point(x, y));
			} else {
				System.err.println("Line has invalid format: " + line);
			}
		}
	
	}

}
