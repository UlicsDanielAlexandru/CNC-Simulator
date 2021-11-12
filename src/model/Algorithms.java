package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Algorithms {

    public static List<Point> getSegmentPoints(Point startPoint, Point endpoint) throws InitialCommandException {
        if(startPoint.equals(endpoint))
            throw new InitialCommandException("Points are equal!");
        List<Point> points = new ArrayList<>();
        points.add(startPoint);
        int xDifference = endpoint.getX() - startPoint.getX();
        int yDifference = endpoint.getY() - startPoint.getY();
        double m = (double) yDifference / xDifference;
        int steps;
        if(Math.abs(xDifference) > Math.abs(yDifference))
            steps = Math.abs(xDifference);
        else
            steps = Math.abs(yDifference);
        double xCurrentPoint = startPoint.getX();
        double yCurrentPoint = startPoint.getY();
        while(steps != 0)
        {
            if(m > 1)
            {
                xCurrentPoint += 1/m;
                yCurrentPoint++;
            }
            else
            {
                xCurrentPoint++;
                if(m < 1)
                    yCurrentPoint += m;
                else
                    yCurrentPoint++;
            }
            points.add(new Point(Math.round(xCurrentPoint),Math.round(yCurrentPoint)));
            steps--;
        }
        return points;
    }

    public static Point getCircleCenter(Point startPoint, Point endPoint, int radius) throws InitialCommandException
    {
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

    public static List<Point> getArcPoints(int xStartPoint, int yStartPoint, int xEndPoint, int yEndPoint, Point center, int radius)
    {
        if(xEndPoint < xStartPoint)
        {
            int aux = xEndPoint;
            xEndPoint = xStartPoint;
            xStartPoint = aux;
        }
        if(yEndPoint < yStartPoint)
        {
            int aux = yEndPoint;
            yEndPoint = yStartPoint;
            yStartPoint = aux;
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
            if(xStartPoint <= xDraw && xDraw <= xEndPoint && yStartPoint <= yDraw && yDraw <= yEndPoint)
                points.add(new Point(xDraw, yDraw));
            xDraw = xCurrentPoint + center.getX();
            yDraw = -yCurrentPoint + center.getY();
            if(xStartPoint <= xDraw && xDraw <= xEndPoint && yStartPoint <= yDraw && yDraw <= yEndPoint)
                points.add(new Point(xDraw, yDraw));
            xDraw = -xCurrentPoint + center.getX();
            yDraw = yCurrentPoint + center.getY();
            if(xStartPoint <= xDraw && xDraw <= xEndPoint && yStartPoint <= yDraw && yDraw <= yEndPoint)
                points.add(new Point(xDraw, yDraw));
            xDraw = -xCurrentPoint + center.getX();
            yDraw = -yCurrentPoint + center.getY();
            if(xStartPoint <= xDraw && xDraw <= xEndPoint && yStartPoint <= yDraw && yDraw <= yEndPoint)
                points.add(new Point(xDraw, yDraw));
            xDraw = yCurrentPoint + center.getX();
            yDraw = xCurrentPoint + center.getY();
            if(xStartPoint <= xDraw && xDraw <= xEndPoint && yStartPoint <= yDraw && yDraw <= yEndPoint)
                points.add(new Point(xDraw, yDraw));
            xDraw = -yCurrentPoint + center.getX();
            yDraw = xCurrentPoint + center.getY();
            if(xStartPoint <= xDraw && xDraw <= xEndPoint && yStartPoint <= yDraw && yDraw <= yEndPoint)
                points.add(new Point(xDraw, yDraw));
            xDraw = yCurrentPoint + center.getX();
            yDraw = -xCurrentPoint + center.getY();
            if(xStartPoint <= xDraw && xDraw <= xEndPoint && yStartPoint <= yDraw && yDraw <= yEndPoint)
                points.add(new Point(xDraw, yDraw));
            xDraw = -yCurrentPoint + center.getX();
            yDraw = -xCurrentPoint + center.getY();
            if(xStartPoint <= xDraw && xDraw <= xEndPoint && yStartPoint <= yDraw && yDraw <= yEndPoint)
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
        Collections.sort(points);
        return points;
    }
}
