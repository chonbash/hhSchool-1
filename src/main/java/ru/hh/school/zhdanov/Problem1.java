package ru.hh.school.zhdanov;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem1 {

	public static void run() throws Exception {
		System.out.println("1. Минимальное расстояние");
		System.out
				.println("Дан набор из N точек на плоскости (для простоты можно считать, что у всех точек целочисленные координаты).");
		System.out
				.println("Найдите минимальное расстояние между двумя точками из этого набора.");
		ArrayList<Point> pointsList = new ArrayList<Point>();
		getListFromFile(pointsList);
//		System.out.println("min brute: " + brute(pointsList));
		System.out.println("min distanse is: " + divideAndConquer(pointsList));
	}

	private static double divideAndConquer(ArrayList<Point> pointsList) {
	    List<Point> pointsSortedByX = new ArrayList<Point>(pointsList);
	    sortByX(pointsSortedByX);
	    List<Point> pointsSortedByY = new ArrayList<Point>(pointsList);
	    sortByY(pointsSortedByY);
	    return divideAndConquer(pointsSortedByX, pointsSortedByY);		
	}

	private static double divideAndConquer(
			List<Point> pointsSortedByX,
			List<Point> pointsSortedByY) {
		int numPoints = pointsSortedByX.size();
	    if (numPoints <= 3) {
	      return brute(pointsSortedByX);
	    }
	    
	    int dividingIndex = numPoints >>> 1;
	    List<Point> leftOfCenter = pointsSortedByX.subList(0, dividingIndex);
	    List<Point> rightOfCenter = pointsSortedByX.subList(dividingIndex, numPoints);
	    
	    List<Point> tempList = new ArrayList<Point>(leftOfCenter);
	    sortByY(tempList);
	    Double closest = divideAndConquer(leftOfCenter, tempList);
	    
	    tempList.clear();
	    tempList.addAll(rightOfCenter);
	    sortByY(tempList);
	    
	    double closestRight = divideAndConquer(rightOfCenter, tempList);
	    
	    if (closestRight < closest) {
	    	closest = closestRight;
	    }
	    
	    tempList.clear();
	    double shortestDistance =closest;
	    double centerX = rightOfCenter.get(0).getX();
	    for (Point point : pointsSortedByY)
	      if (Math.abs(centerX - point.getX()) < shortestDistance)
	        tempList.add(point);
	    
	    for (int i = 0; i < tempList.size() - 1; i++)
	    {
	      Point point1 = tempList.get(i);
	      for (int j = i + 1; j < tempList.size(); j++)
	      {
	        Point point2 = tempList.get(j);
	        if ((point2.getY() - point1.getY()) >= shortestDistance)
	          break;
	        double distance = point1.distance(point2);
	        if (distance < closest)
	        {
	          closest = distance;
	          shortestDistance = distance;
	        }
	      }
	    }
	    return closest;
	    
	}

	private static void sortByY(List<ru.hh.school.zhdanov.Point> pointsSortedByY) {
		Collections.sort(pointsSortedByY, new Comparator<Point>() {
	        public int compare(Point point1, Point point2)
	        {
	          if (point1.getY() < point2.getY())
	            return -1;
	          if (point1.getY() > point2.getY())
	            return 1;
	          return 0;
	        }
	      }
	    );		
	}

	private static void sortByX(List<Point> pointsSortedByX) {
		Collections.sort(pointsSortedByX, new Comparator<Point>() {
	        public int compare(Point pointMore, Point pointLess)
	        {
	          if (pointMore.getX() < pointLess.getX())
	            return -1;
	          if (pointMore.getX() > pointLess.getX())
	            return 1;
	          return 0;
	        }
	      }
		
	    );		
	}

	private static double brute(List<Point> pointsList) {
		if (pointsList.size() < 2) {
			System.err.println("There are not enough points specified.");
			return 0;
		}
		double minDistance = pointsList.get(0).distance(pointsList.get(1));
		double curDistance = 0;
		for (int i = 0; i < pointsList.size(); i++) {
			for (int j = i + 1; j < pointsList.size(); j++) {
				curDistance = pointsList.get(i).distance(pointsList.get(j));
				if (curDistance < minDistance) {
					minDistance = curDistance;
				}
			}
		}
//		System.out.println("Minimal distance is: " + minDistance);
		return minDistance;
	}

	private static void getListFromFile(ArrayList<Point> pointsList)
			throws Exception {
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
