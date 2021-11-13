import model.Algorithms;
import model.FileInterpreter;
import model.InitialCommandException;
import model.Point;

public class MainTester {
    public static void main(String[] args) {
        try {
            System.out.println(Algorithms.getSegmentPoints(new Point(0,0),new Point(-11,0)));
            System.out.println(Algorithms.getCircleCenter(new Point(0,20),new Point(10,30),10));
        } catch (InitialCommandException e) {
            e.printStackTrace();
        }
        System.out.println(Algorithms.getArcPoints(new Point(0,-10), new Point(10, 0), new Point(0,0),10));
        try {
            FileInterpreter.interpretFile("D:\\Facultate-An3\\Sem1\\StructuraSistemelorDeCalcul\\Proiect\\fisierTest.txt");
        } catch (InitialCommandException e) {
            e.printStackTrace();
        }
    }
}
