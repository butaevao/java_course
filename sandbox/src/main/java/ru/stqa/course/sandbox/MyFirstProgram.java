package ru.stqa.course.sandbox;

public class MyFirstProgram {
	public static void main(String[] args) {
		System.out.println("Hello, world!");


	  Point p1 = new Point(4, -5);
	  Point p2 = new Point(7, -1);
		System.out.println("Вычисление расстояния между двумя точками с использованием функции:");
    System.out.println("Расстояние между точками p1(" + p1.x + "," + p1.y + ") и p2(" + p2.x + "," + p2.y + ") = " + distance(p1, p2));

		Point p3 = new Point(4, -5);
		Point p4 = new Point(4, -5);
		System.out.println("Вычисление расстояния между двумя точками с использованием метода:");
		System.out.println("Расстояние между точками p3(" + p3.x + "," + p3.y + ") и p4(" + p4.x + "," + p4.y + ") = " + p4.newDistance(p3));
}

	public static double distance(Point p1, Point p2) {
		return Math.sqrt(Math.pow((p2.x - p1.x), 2) + (Math.pow((p2.y - p1.y), 2)));
	}
}