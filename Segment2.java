import java.util.Scanner;
/** 
* using a class represents a line (parallel to the x-axis) using a center point and length
* 
* @author Amit Dana (322519604)
* @version 16.11.2020
*/


public class Segment2
{
    private Point _poCenter;
    private double _length;
    
    public final double DEFAULT_VAL = 0;

    //constructors:
     /**
    * Constructs a new segment using 4 specified x y coordinates: two coordinates for the left point and two coordinates for the right point.
    * @param leftX The value of x coordinate of left point
    * @param leftY The value of y coordinate of left point
    * @param rightX The value of x coordinate of right point
    * @param rightY The value of y coordinate of right point
    */
     public Segment2 (double leftX, double leftY, double rightX, double rightY){
         if (leftY != rightY)
             rightY=leftY;
         double center= (rightX + leftX)/2;
         _poCenter= new Point (center,leftY);     
         _length = rightX- leftX;
     }
     
    /**
    * Constructs a new segment using a center point and the segment length
    * @param poCenter The value of the center point of the segment
    * @param length The length of the segment
    */
    public Segment2 (Point poCenter, double length){
        _poCenter=new Point (poCenter.getX(),poCenter.getY());
         _length = length;         
        }
        
    /**
    * Constructs a new segment using two Points
    * @param left The value of the left point of the segment
    * @param right The value of the right point of the segment
    */   
     public Segment2 (Point left, Point right){
        double center = (right.getX() + left.getX())/2;
         if (left.getY() != right.getY()){
             right = new Point(right);
             right.setY(left.getY());
             _poCenter= new Point (center,left.getY());
             _length = right.getX() - left.getX();

        }
         else{
             _poCenter= new Point (center,left.getY());     
             _length = right.getX() - left.getX();
         }
        }

     /**
    * Copy Constructor.
    * @param other the reference segment
    */  
    public Segment2 (Segment2 other){
            _poCenter=new Point (other._poCenter);
            _length=other._length;
        }

    /**
    * Change the segment size by moving the right point by delta
    * @param delta the length change
    */          
    public void changeSize (double delta){
        double pointRight= this.getPoRight().getX();
        double pointLeft= this.getPoLeft().getX();
        if (pointRight + delta >= pointLeft){
           _poCenter.setX((pointRight+pointLeft+delta)/2);           
           _length= _length +delta;
     }
    }
    
    /**
    * Check if the reference segment is equal to this segment.
    * @param other  the reference segment
    * @return True if the reference segment is equal to this segment

    */      
    public boolean equals (Segment2 other) {
        return other._poCenter.equals(this._poCenter) && other._length==this._length;
    }
    
    /**
    * Returns the segment length
    * @return The segment length
    */  
    public double getLength(){
        return this._length;
    }
    
    /**
    * Returns the left point of the segment.
    * @return The left point of the segment
    */      
    public Point getPoLeft(){
        double leftX= Math.abs(_poCenter.getX() - (_length/2));
        double leftY= _poCenter.getY();
        return new Point (leftX,leftY);
    }
    
    /**
    * Returns the right point of the segment
    * @return The right point of the segment
    */ 
    public Point getPoRight(){
        double rightX= _poCenter.getX() + (_length/2);
        double rightY= _poCenter.getY();
        return new Point (rightX,rightY);
    }
    
    /**
    * Check if this segment is above a reference segment.
    * @param other the reference segment
    * @return True if this segment is above the reference segment
    */ 
    public boolean isAbove (Segment2 other){
        return other._poCenter.getY()<this._poCenter.getY();
    }

    /**
    * Check if this segment is bigger than a reference segment.
    * @param other the reference segment
    * @return True if this segment is bigger than the reference segment
    */     
    public boolean isBigger (Segment2 other){
         return  this._length > other._length ; 
    }
        
    /**
     * Check if this segment is left of a received segment.
     * @param other -> the reference segment
     * @return True if this segment is left to the reference segment
     */
    public boolean isLeft(Segment2 other){
        // using Point isLeft() method to check if the right point of the first segment is 
        // left to the left point of the second segment 
        return this.getPoRight().isLeft(other.getPoLeft());
    }
    
    /**
     * Check if this segment is right of a received segment
     * @param other -> the reference segment
     * @return True if this segment is right to the reference segment
     */
    public boolean isRight (Segment2 other){
        return other.isLeft(this);
    }
    
    /**
     * Check if this segment is under a reference segment
     * @param other -> the reference segment
     * @return True if this segment is under the reference segment
     */
    public boolean isUnder (Segment2 other){
        return other.isAbove(this);
    }
  
    /**
     * Move the segment horizontally by delta. Will be implemented only for a valid delta
     * @param delta -> the displacement size
     */
    public void moveHorizontal (double delta){
        if ((this.getPoRight().getX()+delta) >=DEFAULT_VAL && (this.getPoLeft().getX()+delta)>=DEFAULT_VAL){
            _poCenter.setX(_poCenter.getX()+delta);
        }
    }
    
    /**
     * Move the segment horizontally by delta. Will be implemented only for a valid delta
     * @param delta -> the displacement size
     */
    public void moveVertical (double delta){
        if ((this.getPoRight().getY()+delta) >=DEFAULT_VAL && (this.getPoLeft().getY()+delta)>=DEFAULT_VAL)
            _poCenter.setY(_poCenter.getY()+delta);   
    }   
    
    /**
     * Returns the overlap size of this segment and a reference segment.
     * @param other ->  the reference segment
     * @return the overlap size
     */ 
    public double overlap (Segment2 other){
         if (this.isLeft(other)==false && this.isRight(other)==false){
            if (this.getPoRight().getX() > other.getPoRight().getX() && this.getPoLeft().getX()<=other.getPoLeft().getX())
                  return other.getLength();
            else if (this.getPoRight().getX() < other.getPoRight().getX() && this.getPoLeft().getX()>=other.getPoLeft().getX())
                return this.getLength();
            else if (this.getPoRight().getX() >= other.getPoLeft().getX() && this.getPoLeft().getX()>other.getPoLeft().getX())
                 return other.getPoRight().getX() - this.getPoLeft().getX();
            else return this.getPoRight().getX() - other.getPoLeft().getX() ;
            }
        else return 0; 
     }
    
     /**
     * Check if a point is located on the segment
     * @param P ->  a point to be checked
     * @return True if p is on this segment
     */ 
     public boolean pointOnSegment (Point P){
         return getPoLeft().getX()<= P.getX() && getPoRight().getX()>=P.getX() && P.getY()==getPoLeft().getY();
    }
    
    /**
     * Return a string representation of this segment in the format (3.0,4.0)---(3.0,6.0).
     * @return String representation of this segment
     */ 
    public String toString (){
        return "(" + getPoLeft().getX() + "," + getPoLeft().getY() + ")" + "---" + "/n"
       + "(" + getPoRight().getX() + "," + getPoRight().getY() + ")";
    }
       
    /**
     * Compute the trapeze perimeter, which constructed by this segment and a reference segment.
     * @param other the reference segment
     * @return The trapeze perimeter
     */ 
     public double trapezePerimeter (Segment2 other){
        double base1 = this._length;
        double base2 = other._length;
        double edge3= this.getPoLeft().distance(other.getPoLeft());
        double edge4= this.getPoRight().distance(other.getPoRight());
        double perimeter=base1 + base2 + edge3 + edge4;
        return perimeter;
    }
}