import java.util.Comparator;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdDraw;
public class BruteCollinearPoints {

    private int segmentCount;
    private Point[] points;
    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        validatePoints(points);
        this.points = points;
        
        
        
        for (int i = 0; i < points.length; i++)
            for (int j = i; j < points.length; j++)
                for (int k = j; k < points.length; k++)
                    if (isLineSegment(points[i].slopeOrder(), points[j], points[k]) && (points[j] != points[k]) && (points[j] != points[i]) ) 
                        for (int l = 0; l < points.length; l++)
                            if (isLineSegment(points[l].slopeOrder(), points[j], points[k]) && (points[j] != points[k]) && (points[j] != points[l])  && (points[j] != points[i]) && (points[l] != points[i]) )
                                System.out.println(points[i].toString() + points[j].toString() + points[k].toString() + points[l].toString() + isLineSegment(points[i].slopeOrder(), points[j], points[k]));
      
    }
    
    
    private boolean isLineSegment(Comparator<Point> c,Point a, Point b) {
        return c.compare(a,b) == 0;  
    }  
    // the number of line segments
    public int numberOfSegments() {
        return segmentCount;
    }
    // the line segments
    public LineSegment[] segments() {
        Point a = new Point(1,1);
        Point b = new Point(2,2);
        LineSegment ls = new LineSegment(a,b);
        return null;
    }
    //Corner cases. Throw a java.lang.IllegalArgumentException if the argument to the constructor is null, 
    //if any point in the array is null, or if the argument to the constructor contains a repeated point.
    private void validatePoints(Point[] points) {
        if (points == null) throw new java.lang.IllegalArgumentException("Points array is null");

        for (int j = 0;j < points.length;j++)
            for (int k = j+1;k<points.length;k++)
                if (k != j && points[k] == points[j])
                    throw new java.lang.IllegalArgumentException("Array contains a repeated points");
        
        for (int i = 0; i < points.length; i++)
            if (points[i] == null) 
                throw new java.lang.IllegalArgumentException("Array contains null points");
        
    }
      
    public static void main(String[] args) {
        int length = 100;
        int x,y;
        Point[] points = new Point[length];
        for (int i = 0; i < length; i++) {
             x = StdRandom.uniform(0, 100);
             y = StdRandom.uniform(0, 100);
             points[i] = new Point(x,y);    
        }
        
            StdDraw.setXscale(0, 100);
            StdDraw.setYscale(0, 100);
            
                for (Point p : points) {
  //      p.draw();
    }
  //  StdDraw.show();
      
    
    BruteCollinearPoints bcp = new BruteCollinearPoints(points);
//StdDraw.line(35,71,50,83);

//StdDraw.line(35,71,30,67);
        
 /*     
(28, 0)(60, 37)(19, 32)false
(28, 0)(60, 37)(47, 96)false
(28, 0)(60, 37)(48, 55)false
(28, 0)(60, 37)(51, 88)false
(28, 0)(60, 37)(66, 16)false
(28, 0)(60, 37)(73, 87)true*/
 // draw the points

    }
}