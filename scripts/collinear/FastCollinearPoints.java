import java.util.ArrayList;
import java.util.Arrays;
public class FastCollinearPoints {
    private final ArrayList<LineSegment> segments = new ArrayList<>();
    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] inPoints) {
        Point[] points = inPoints.clone();  
        Arrays.sort(points);
        validateOrderedPoints(points);
        
        for (int i = 0; i < points.length; i++) {
            Arrays.sort(points);
            Arrays.sort(points, inPoints[i].slopeOrder());
            populateSegments(points); 
        }
    }
    
    private void populateSegments(Point[] orderedPoints) {
        Point previousPoint = null;
        int count = 0;
        double value = 0;
        for (int i = 0; i < orderedPoints.length; i++) {
 //          System.out.println("i=" + i + " count=" + count + " value=" + value +  "  " + orderedPoints[0].toString() + " " + orderedPoints[i].toString() 
 //                               + "   " +  orderedPoints[0].slopeTo(orderedPoints[i]) + (Double.compare(orderedPoints[0].slopeTo(orderedPoints[i]), value) == 0) );
            if (previousPoint == null || Double.compare(orderedPoints[0].slopeTo(orderedPoints[i]), value) == 0) {
                count++;
            }
            else if  (count > 1 && orderedPoints[0].compareTo(orderedPoints[i-count-1]) == -1) {
                segments.add(new LineSegment(orderedPoints[0], orderedPoints[i-1]));
                count = 0;

            }
            else  {
                count = 0;
            }
            previousPoint = orderedPoints[i];
            value = orderedPoints[0].slopeTo(previousPoint);
            if (count > 1 && orderedPoints[0].compareTo(orderedPoints[i-count]) == -1 && i == orderedPoints.length - 1) {
                segments.add(new LineSegment(orderedPoints[0], orderedPoints[i]));
 //               System.out.println("count>3");
                              
            }
        }
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
            if (points[i] == null) 
                throw new java.lang.IllegalArgumentException("Array contains null points");
    }    
        
}
