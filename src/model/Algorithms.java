package model;

import java.util.ArrayList;
import java.util.List;

public class Algorithms {

    public static List<Point> getSegmentPoints(Point startPoint, Point endpoint)
    {
        List<Point> points = new ArrayList<>();
        points.add(startPoint);
        int xDifference = endpoint.getX() - startPoint.getX();
        int yDifference = endpoint.getY() - startPoint.getY();
        double m = (double) yDifference / xDifference;
        int steps;
        if(Math.abs(xDifference) > Math.abs(yDifference))
            steps = Math.abs(xDifference);
        else
            steps = Math.abs(xDifference);
        double xCurrentPoint = startPoint.getX();
        double yCurrentPoint = startPoint.getY();
        while(steps != 0)
        {
            if(m > 1)
            {
                xCurrentPoint += 1/m;
                yCurrentPoint++;
                points.add(new Point(Math.round(xCurrentPoint),Math.round(yCurrentPoint)));
            }
            else
            {
                if(m < 1)
                {
                    xCurrentPoint++;
                    yCurrentPoint += m;
                    points.add(new Point(Math.round(xCurrentPoint),Math.round(yCurrentPoint)));
                }
                else
                {
                    xCurrentPoint++;
                    yCurrentPoint++;
                    points.add(new Point(Math.round(xCurrentPoint),Math.round(yCurrentPoint)));
                }
            }
            steps--;
        }
        return points;
    }
}
