package ru.stqa.course.sandbox;

/**
 * Created by Оля on 20.09.2016.
 */
public class Point {

  private double x;
  private double y;

  public static void main(String[] args) {
    Point p1 = new Point();
    Point p2 = new Point();
    p1.x = 4;
    p1.y = -5;
    p2.x = 7;
    p2.y = -1;
    System.out.println("Расстояние между точками p1(" + p1.x + "," + p1.y + ") и p2(" + p2.x + "," + p2.y + ") = " + distance(p1, p2));
  }

  private static double distance(Point p1, Point p2) {
    return Math.sqrt(Math.pow((p2.x - p1.x), 2) + (Math.pow((p2.y - p1.y), 2)));
  }
}