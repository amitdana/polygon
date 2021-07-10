import java.util.Scanner;

/** 
* Using a class represents a polygon using an array of points and a number of vertices
* 
* @author Amit Dana (322519604)
* @version 04.12.2020
*/

public class Polygon
{
    private Point [] _vertices;
    private int _noOfVertices;
    
    public final int MAXֹֹ_VERTEX = 10;
 
    /**
    * Constructor create a new Array size 10 and number of vertices
    */
     public Polygon (){
         _vertices = new Point [MAXֹֹ_VERTEX];
         _noOfVertices = 0;
     }
     
    /**
    * Adds a vertex to the polygon
    * @param x the X coordinate of the new vertex
    * @param y the Y coordinate of the new vertex 
    * @return true if the array has been updated
    * @return false if there is no place in the array
    */
    public boolean addVertex(double x, double y){
      if (_noOfVertices==MAXֹֹ_VERTEX)
            return false;
      _vertices[_noOfVertices++]=new Point (x,y);
      return true;
    }
     
    /**
    * Finds the highest vertex in the polygon
    * @return null if there are no vertices in the polygon
    * @return the highest point in the polygon
    */
    public Point highestVertex(){
      if (_noOfVertices==0)
        return null;
      Point highest=_vertices[0];
      for (int i=1; i<_noOfVertices;i++)
        if (_vertices[i].isAbove(highest))
            highest=_vertices[i];
      return new Point (highest);  
    }
 
    /**
     * Return a string representation of this polygon
     * @return String representation of this polygon
     */ 
    public String toString (){
      int i;  
        if (_noOfVertices==0)
            return "The polygon has 0 vertices.";
        String points="The polygon has " +_noOfVertices+ " vertices:\n("; 
        for (i=0;i<_noOfVertices-1;i++)
           points+=_vertices[i]+",";
        points+=_vertices[i]+")";
        return points;         
    }
   
   /**
     * caculate the perimeter of the polygon
     * @return the value of polygon's perimeter
   */ 
   public double calcPerimeter(){
       int i;
       double sum=0;
       if (_noOfVertices==1 || _noOfVertices==0)
          return 0;
       if (_noOfVertices==2)
            return _vertices[0].distance(_vertices[1]);
       for (i=0;i<_noOfVertices-1;i++)
          sum+= _vertices[i].distance(_vertices[i+1]);
       sum+=_vertices[i].distance(_vertices[0]);
       return sum;
    }
   private double triangularArea(double a,double b,double c){
       double perimeter=(a+b+c)/2;
       double area=Math.sqrt(perimeter*(perimeter-a)*(perimeter-b)*(perimeter-c));
       return area;
    }
    
   /**
     * caculate the area of the polygon
     * @return the value of polygon's area
   */ 
   public double calcArea(){ 
      double area=0;
      if (_noOfVertices>=3)
      for (int i=1;i<=_noOfVertices-2;i++){
          double a = _vertices[0].distance(_vertices[i]);
          double b= _vertices[i].distance(_vertices[i+1]);
          double c= _vertices[i+1].distance(_vertices[0]);
          area+=triangularArea(a,b,c);
       }
      else
          return 0;
      return area;
    }
   /**
     * Check if this polygon's area is bigger then received polygon's.
     * @param other the polygon to be compared with this polygon
     * @return true if this polygon's area is bigger then the other polygon's area. else- false
     */ 
   public boolean isBigger (Polygon other){
    if (this.calcArea() > other.calcArea())
        return true;
    return false;
    }
   
    /**
     * Checks if a given point is on the polygon
     * @param p the point to check if in the array
     * @return The position of the point in the array. If the point is not in the array - will return -1
       */
   public int findVertex (Point p){
     int i;
     for (i=0;i<_noOfVertices; i++)
        if (_vertices[i].equals(p))
           return i;
     return -1;
    }
    
   /**
     * Gets a point and makes a copy of the next point in the polygon
     * @param p the point to check where it is in the array
     * @return The point that is next in the array
       */
   public Point getNextVertex (Point p){
       int pointIndex = findVertex(p);
       if (pointIndex==-1)
            return null;
       else if (pointIndex==_noOfVertices-1)
           return new Point (_vertices[0]);
       else
       return new Point (_vertices[pointIndex+1]);
        }
    
   /**
     * Creates a rectangle that bounding the polygon
     * @return A new polygon that is the rectangle that bounding the polygon
       */
   public Polygon getBoundingBox()
   {
       if (_noOfVertices<3 )
        return null;
       Point p1=new Point (_vertices[0].getX(),_vertices[0].getY());
       Point p2=new Point (_vertices[0].getX(),_vertices[0].getY());
      for (int i=1;i<=_noOfVertices-1;i++)
      {
          if (p1.isLeft(_vertices[i])) //p1 will represent the lowest values in polygon-x, y
              p1.setX(_vertices[i].getX()); 
          if (p2.isRight(_vertices[i])) //p2 will represents the highest values in polygon-x, y
              p2.setX(_vertices[i].getX());
          if (p2.isAbove(_vertices[i]))
              p2.setY(_vertices[i].getY());
          if (p1.isUnder(_vertices[i]))
              p1.setY(_vertices[i].getY());
            }    
      Polygon rectangle = new Polygon ();
      rectangle.addVertex(p2.getX(),p2.getY());
      rectangle.addVertex(p1.getX(),p2.getY());
      rectangle.addVertex(p1.getX(),p1.getY());
      rectangle.addVertex(p2.getX(),p1.getY());
      return rectangle;
       }
   }
