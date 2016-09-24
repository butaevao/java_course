package ru.stqa.course.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Оля on 24.09.2016.
 */
public class PointTests {

  @Test
  public void testArea() {
    /** Проверка вычисления расстояния между двумя точками, если координаты обеих точек - положительные числа */
    Point p1 = new Point(4, 5);
    Point p2 = new Point(3, 4);
    Assert.assertEquals(Math.round(p2.newDistance(p1)), 1);

    /** Проверка вычисления расстояния между двумя точками, если координаты обеих точек - положительные и отрицательные числа */
    Point p3 = new Point(4, -5);
    Point p4 = new Point(-6, 3);
    Assert.assertEquals(Math.round(p4.newDistance(p3)), 13);

    /** Проверка вычисления расстояния между двумя точками, если координаты точек совпадают */
    Point p5 = new Point(3, -6);
    Point p6 = new Point(3, -6);
    Assert.assertEquals(p6.newDistance(p5), 0.0);

    /** Проверка вычисления расстояния между двумя точками, если одна из точек - начало координат */
    Point p7 = new Point(8, 3);
    Point p8 = new Point(0, 0);
    Assert.assertEquals(Math.round(p8.newDistance(p7)), 9);
  }
}


