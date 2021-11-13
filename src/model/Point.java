package model;

public class Point{
    private int x;
    private int y;

    public Point(int x,int y)
    {
        this.x = x;
        this.y = y;
    }

    public Point(long x,long y)
    {
        this.x = (int) x;
        this.y = (int) y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double distance(Point point)
    {
        double xDifference = this.x - point.x;
        double yDifference = this.y - point.y;
        return Math.sqrt(xDifference * xDifference + yDifference * yDifference);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    public Point add(Point point)
    {
        return new Point(this.x + point.x, this.y + point.y);
    }

    public Point subtract(Point point)
    {
        return new Point(this.x - point.x, this.y - point.y);
    }

    @Override
    public String toString()
    {
        return "(" + this.x + "," + this.y + ")";
    }

}
