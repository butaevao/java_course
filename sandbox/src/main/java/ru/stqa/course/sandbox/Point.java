package ru.stqa.course.sandbox;

/**
 * Created by Оля on 22.09.2016.
 */
public class Point {

  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double newDistance(Point p3) {
    return Math.sqrt(Math.pow((x - p3.x), 2) + (Math.pow((y - p3.y), 2)));
  }
}
