package model;

import java.util.ArrayList;
import java.util.List;

public class Algorithms {

    public static List<Point> getSegmentPoints(Point startPoint, Point endPoint) throws InitialCommandException {
        if(startPoint.equals(endPoint))
            throw new InitialCommandException("Points are equal!");
        List<Point> points = new ArrayList<>();
        points.add(startPoint);
        double xCurrentPoint = startPoint.getX();
        double yCurrentPoint = startPoint.getY();
        int xDifference = endPoint.getX() - startPoint.getX();
        int yDifference = endPoint.getY() - startPoint.getY();
        int steps;
        if(Math.abs(xDifference) > Math.abs(yDifference))
            steps = Math.abs(xDifference);
        else
            steps = Math.abs(yDifference);
        if(xDifference == 0)
        {
            for(int index = 0; index < steps; index++)
            {
                yCurrentPoint = yCurrentPoint + yDifference / steps;
                points.add(new Point(Math.round(xCurrentPoint), Math.round(yCurrentPoint)));
            }
            return points;
        }
        double m = (double) yDifference / xDifference;
        xDifference /= steps;
        yDifference /= steps;
        while(steps != 0)
        {
            if(m > 1)
            {
                xCurrentPoint += 1/m;
                yCurrentPoint += yDifference;
            }
            else
            {
                xCurrentPoint += xDifference;
                if(m < 1)
                    yCurrentPoint += m;
                else
                    yCurrentPoint += yDifference;
            }
            points.add(new Point(Math.round(xCurrentPoint),Math.round(yCurrentPoint)));
            steps--;
        }
        return points;
    }

    public static Point getCircleCenter(Point startPoint, Point endPoint, int radius) throws InitialCommandException
    {
        if(startPoint.equals(endPoint))
            throw new InitialCommandException("Points are equal!");
        if(radius <= 0)
            throw new InitialCommandException("Incorrect radius!");
        double distance = startPoint.distance(endPoint);
        double diameter = 2.0 * radius;
        if(distance > diameter)
            throw new InitialCommandException("Points are too distanced!");
        Point center = new Point(Math.round((startPoint.getX() + endPoint.getX()) / 2.0),Math.round((startPoint.getY() +
                endPoint.getY()) / 2.0));
        if(distance == diameter)
            return center;
        double mirrorDistance = Math.sqrt(radius * radius - distance * distance / 4.0);
        double xDifference = (endPoint.getX() - startPoint.getX()) * mirrorDistance / distance;
        double yDifference = (endPoint.getY() - startPoint.getY()) * mirrorDistance / distance;
        return new Point(Math.round(center.getX() + yDifference),Math.round(center.getY() - xDifference));
    }

    public static List<Point> getArcPoints(Point startPoint, Point endPoint, Point center, int radius)
    {
        int xLeftInterval = startPoint.getX();
        int yLeftInterval = startPoint.getY();
        int xRightInterval = endPoint.getX();
        int yRightInterval = endPoint.getY();
        if(xRightInterval < xLeftInterval)
        {
            int aux = xRightInterval;
            xRightInterval = xLeftInterval;
            xLeftInterval = aux;
        }
        if(yRightInterval < yLeftInterval)
        {
            int aux = yRightInterval;
            yRightInterval = yLeftInterval;
            yLeftInterval = aux;
        }
        List<Point> points = new ArrayList<>();
        int xCurrentPoint = 0;
        int yCurrentPoint = radius;
        int p = 1 - radius;
        int xDraw;
        int yDraw;
        while(xCurrentPoint < yCurrentPoint)
        {
            xDraw = xCurrentPoint + center.getX();
            yDraw = yCurrentPoint + center.getY();
            if(xLeftInterval <= xDraw && xDraw <= xRightInterval && yLeftInterval <= yDraw && yDraw <= yRightInterval)
                points.add(new Point(xDraw, yDraw));
            xDraw = xCurrentPoint + center.getX();
            yDraw = -yCurrentPoint + center.getY();
            if(xLeftInterval <= xDraw && xDraw <= xRightInterval && yLeftInterval <= yDraw && yDraw <= yRightInterval)
                points.add(new Point(xDraw, yDraw));
            xDraw = -xCurrentPoint + center.getX();
            yDraw = yCurrentPoint + center.getY();
            if(xLeftInterval <= xDraw && xDraw <= xRightInterval && yLeftInterval <= yDraw && yDraw <= yRightInterval)
                points.add(new Point(xDraw, yDraw));
            xDraw = -xCurrentPoint + center.getX();
            yDraw = -yCurrentPoint + center.getY();
            if(xLeftInterval <= xDraw && xDraw <= xRightInterval && yLeftInterval <= yDraw && yDraw <= yRightInterval)
                points.add(new Point(xDraw, yDraw));
            xDraw = yCurrentPoint + center.getX();
            yDraw = xCurrentPoint + center.getY();
            if(xLeftInterval <= xDraw && xDraw <= xRightInterval && yLeftInterval <= yDraw && yDraw <= yRightInterval)
                points.add(new Point(xDraw, yDraw));
            xDraw = -yCurrentPoint + center.getX();
            yDraw = xCurrentPoint + center.getY();
            if(xLeftInterval <= xDraw && xDraw <= xRightInterval && yLeftInterval <= yDraw && yDraw <= yRightInterval)
                points.add(new Point(xDraw, yDraw));
            xDraw = yCurrentPoint + center.getX();
            yDraw = -xCurrentPoint + center.getY();
            if(xLeftInterval <= xDraw && xDraw <= xRightInterval && yLeftInterval <= yDraw && yDraw <= yRightInterval)
                points.add(new Point(xDraw, yDraw));
            xDraw = -yCurrentPoint + center.getX();
            yDraw = -xCurrentPoint + center.getY();
            if(xLeftInterval <= xDraw && xDraw <= xRightInterval && yLeftInterval <= yDraw && yDraw <= yRightInterval)
                points.add(new Point(xDraw, yDraw));
            xCurrentPoint++;
            if(p < 0)
                p += 2 * xCurrentPoint + 1;
            else
            {
                yCurrentPoint--;
                p = p - 2 * yCurrentPoint + 2 * xCurrentPoint + 1;
            }
        }
        Point upPoint = new Point(center.getX(),center.getY() + radius);
        Point downPoint = new Point(center.getX(), center.getY() - radius);
        Point leftPoint = new Point(center.getX() - radius, center.getY());
        Point rightPoint = new Point(center.getX() + radius, center.getY());
        points.remove(upPoint);
        points.remove(downPoint);
        points.remove(leftPoint);
        points.remove(rightPoint);
        points.sort((x, y) -> (int) Math.round(x.distance(startPoint) - y.distance(startPoint)));
        return points;
    }
}
