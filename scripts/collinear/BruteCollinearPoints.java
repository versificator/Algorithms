import java.util.Comparator;
import java.util.ArrayList;
import java.util.Arrays;
/*
 * Write a program BruteCollinearPoints.java 
 * that examines 4 points at a time and checks whether they all
 * lie on the same line segment, returning all such line segments.
 * 
 * To check whether the 4 points p, q, r, and s are collinear, 
 * check whether the three slopes between p and q, between p and r, 
 * and between p and s are all equal.
 */

public class BruteCollinearPoints {

    private final ArrayList<LineSegment> segments = new ArrayList<>();
    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] inPoints) {
        Point[] points = inPoints.clone();
        Arrays.sort(points);
        validateOrderedPoints(points);
        for (int i = 0; i < points.length; i++) {
            Arrays.sort(points);
//            Arrays.sort(points, points[i].slopeOrder());
            for (int j = i+1; j < points.length; j++)
                for (int k = j+1; k < points.length; k++)
                    if (isLineSegment(points[i].slopeOrder(), points[j], points[k])) 
                        for (int m = k+1; m < points.length; m++)
                            if (isLineSegment(points[m].slopeOrder(), points[j], points[k])) {
//                                    System.out.println(points[i].toString() + points[j].toString() + points[k].toString() + points[m].toString()
//                                      + isLineSegment(points[i].slopeOrder(), points[j], points[k]));
                                    segments.add(new LineSegment(points[i], points[m]));
                            }
        }
    }
    
    
    private boolean isLineSegment(Comparator<Point> c, Point a, Point b) {
        return c.compare(a, b) == 0;  
    }  
    // the number of line segments
    public int numberOfSegments() {
        return segments.size();
    }
    
    // the line segments
    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[segments.size()]);
    }
    // Corner cases. Throw a java.lang.IllegalArgumentException if the argument to the constructor is null, 
    // if any point in the array is null, or if the argument to the constructor contains a repeated point.
    private void validateOrderedPoints(Point[] points) {
        if (points == null) throw new java.lang.IllegalArgumentException("Points array is null");
        
        for (int i = 0; i < points.length - 1; i++) 
            if (points[i].compareTo(points[i + 1]) == 0) throw new java.lang.IllegalArgumentException("Array contains a repeated points");
        
        for (int i = 0; i < points.length; i++)
            if (points[i] == null) throw new java.lang.IllegalArgumentException("Array contains null points");
    }    

}