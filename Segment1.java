import java.util.Scanner;
/** 
* using a class to use the x, y coordinates and represent them using alpha and radius
* 
* @author Amit Dana (322519604)
* @version 16.11.2020
*/


public class Segment1
{
    private Point _poLeft;
    private Point _poRight;
    
    public final double DEFAULT_VAL = 0;

    //constructors:
    /**
    * Creates the coordinate values of the endpoints of the segment
    * @param _poRight The value of the right point of the segment
    * @param _poLeft The value of the right point of the segment
    */
     public Segment1 (Point left, Point right){

         
         if (left.getY() != right.getY()){
             _poRight= new Point (right);   
              _poRight.setY(left.getY());     
              _poLeft = new Point (left);}
         else{
         _poRight= new Point (right);     
         _poLeft = new Point (left);
         }
        }
    
     /**
    * Constructs a new segment using 4 specified x y coordinates: Two coordinates for the left point and two coordinates for the right point.
    * @param leftX X value of left point
    * @param leftY Y value of left point
    * @param rightX X value of right point
    * @param rightY Y value of right point
    */   
     public Segment1 (double leftX, double leftY, double rightX, double rightY){

        if (leftY != rightY){
             _poLeft=new Point (leftX,leftY);
             _poRight=new Point (rightX,rightY);
             _poRight.setY(leftY);
        }
        else{
             _poLeft=new Point (leftX,leftY);
             _poRight=new Point (rightX,rightY);
        }
        
     }
     
     /**
    * Copy Constructor. Construct a segment using a reference segment.
    * @param other the reference segment
    */ 
    public Segment1 (Segment1 other){
            _poLeft=other._poLeft;
            _poRight=other._poRight;
        }
 
     /**
    * Returns the left point of the segment.
    * @return The left point of the segment
    */    
    public Point getPoLeft(){
        return _poLeft;
    }
    
     /**
    * Returns the right point of the segment.
    * @return The right point of the segment
    */
    public Point getPoRight(){
        return _poRight;
    }
    
     /**
    * Returns the segment length.
    * @return The segment length
    */
    public double getLength(){
            return _poRight.getX() - _poLeft.getX();
        
    }
    
     /**
    * Return a string representation of this segment in the format (3.0,4.0)---(3.0,6.0)
    * @return String representation of this segment
    */
    public String toString (){
        return "(" + _poLeft.getX() + "," + _poLeft.getY() + ")" + "---" +"(" + _poRight.getX() + "," + _poRight.getY() + ")";
    }

     /**
    * Check if the reference segment is equal to this segment.
    * @param other the reference segment
    * @return True if the reference segment is equal to this segment
    */
    public boolean equals (Segment1 other) {
        return other._poLeft.equals(_poLeft) && other._poRight.equals(_poRight);
    }
    
     /**
    * Check if this segment is above a reference segment.
    * @param other the reference segment
    * @return True if this segment is above the reference segment
    */
    public boolean isAbove (Segment1 other){
        return other._poLeft.getY()<this._poLeft.getY();
    }
    
     /**
    * Check if this segment is under a reference segment.
    * @param other the reference segment
    * @return True if this segment is under the reference segment
    */
    public boolean isUnder (Segment1 other){
        return other.isAbove(this);
    }
    
    
    /**
     * Check if this segment is left of a received segment.
     * @param other -> the other Segment1 object.
     * @return True if this segment is left to the reference segment
     */
    public boolean isLeft(Segment1 other){
        // using Point isLeft() method to check if the right point of the first segment is 
        // left to the left point of the second segment 
        return this.getPoRight().isLeft(other.getPoLeft());
    }

     /**
    * Check if this segment is right of a received segment.
    * @param other the reference segment
    * @return True if this segment is right to the reference segment
    */
      public boolean isRight (Segment1 other){
        return other.isLeft(this);
    }
    
     /**
    * Move the segment horizontally by delta.
    * @param delta the displacement size
    */
    public void moveHorizontal (double delta){
        if ((this._poRight.getX()+delta) >=DEFAULT_VAL && (this._poLeft.getX()+delta)>=DEFAULT_VAL){
            _poRight.setX(_poRight.getX()+delta);
            _poLeft.setX(_poLeft.getX()+delta);
        }
    }
    
     /**
    * Move the segment vertically by delta.
    * @param delta the displacement size
    */
    public void moveVertical (double delta){
        if ((this._poRight.getY()+delta) >=DEFAULT_VAL && (this._poLeft.getY()+delta)>=DEFAULT_VAL){
            _poRight.setY(_poRight.getY()+delta);
            _poLeft.setY(_poLeft.getY()+delta);
        }
    }
    
     /**
    * Change the segment size by moving the right point by delta. Will be implemented only for a valid delta: only if the new right point remains the right point.
    * @param delta the length change
    */
    public void changeSize (double delta){
        if (_poRight.getX() + delta >= _poLeft.getX())
           _poRight.setX(_poRight.getX()+delta);
    }
    
      /**
    * Check if a point is located on the segment.
    * @param p a point to be checked
    * @return True if p is on this segment
    */
    public boolean pointOnSegment (Point P){
        return _poLeft.getX()<= P.getX() && _poRight.getX()>=P.getX() && P.getY()==_poLeft.getY();
    }
    
      /**
    * Check if this segment is bigger than a reference segment.
    * @param other the reference segment
    * @return True if this segment is bigger than the reference segment
    */
    public boolean isBigger (Segment1 other){
        double distanceThis = this._poRight.getX()-this._poLeft.getX();
        double distanceOther = other._poRight.getX()-other._poLeft.getX();
        return  distanceThis > distanceOther ;
        
    }
    
     /**
    * Returns the overlap size of this segment and a reference segment.
    * @param other the reference segment
    * @return The overlap size
    */
    public double overlap (Segment1 other){
        if (this.isLeft(other)==false && this.isRight(other)==false){
            if (this._poRight.getX() > other._poRight.getX() && this._poLeft.getX()<=other._poLeft.getX())
                  return other.getLength();
            else if (this._poRight.getX() < other._poRight.getX() && this._poLeft.getX()>=other._poLeft.getX())
                return this.getLength();
            else if (this._poRight.getX() >= other._poRight.getX() && this._poLeft.getX()>other._poLeft.getX())
                return other._poRight.getX() - this._poLeft.getX();
            else return this._poRight.getX() - other._poLeft.getX() ;
            }
        else return 0; 
     }
    
      /**
    * Compute the trapeze perimeter, which is constructed by this segment and a reference segment.
    * @param other the reference segment
    * @return The trapeze perimeter
    */
     public double trapezePerimeter (Segment1 other){
        double base1 = this.getLength();
        double base2 = other.getLength();
        double edge3= this._poLeft.distance(other._poLeft);
        double edge4= this._poRight.distance(other._poRight);
        double perimeter=base1 + base2 + edge3 + edge4;
        return perimeter;
    }
}