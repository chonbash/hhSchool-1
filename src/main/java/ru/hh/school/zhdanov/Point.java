package ru.hh.school.zhdanov;

public class Point {
	private Integer x;
	private Integer y;

	public Point() {
		x = 0;
		y = 0;
	}

	public Point(int xPos, int yPos) {
		x = xPos;
		y = yPos;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public double distance(Point a) {
		return Math.sqrt(Math.pow((this.getX() - a.getX()), 2)
				+ Math.pow((this.getY() - a.getY()), 2));
	}
	
	public boolean compareX(Point a){
		return (this.getX() < a.getX());
	}
	
	public boolean compareY(Point a){
		return (this.getY() < a.getY());
	}

}
