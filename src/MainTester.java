import model.Algorithms;
import model.InitialCommandException;
import model.Point;

public class MainTester {
    public static void main(String[] args) {
        try {
            System.out.println(Algorithms.getSegmentPoints(new Point(-2,4),new Point(0,2)));
            System.out.println(Algorithms.getCircleCenter(new Point(0,20),new Point(10,30),10));
        } catch (InitialCommandException e) {
            e.printStackTrace();
        }
        System.out.println(Algorithms.getArcPoints(0,10,10,0,new Point(0,0),10));
    }
}
