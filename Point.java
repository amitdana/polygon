import java.util.Scanner;
/** 
* using a class to use the x, y coordinates and represent them using alpha and radius
* 
* @author Amit Dana (322519604)
* @version 16.11.2020
*/


public class Point
{
    private double _radius;
    private double _alpha;
    
    public final double DEFAULT_VAL = 0.0;
    public final double ROUND = 10000.0;
    public final int NINETHֹֹ_DEG = 90;
    public final int FLAT_DEG = 180;
    
    /**
    * A private method that calculates radians as degrees
    * @param angle the angle value for calculation 
    * @return Angle size in degrees
    */
    private double toDegrees(double angle)
    {
        return (FLAT_DEG*angle)/Math.PI;
    }
    
    /**
    * A private method that calculates degrees as radians
    * @param angle the angle value for calculation 
    * @param Angle size in radian
    */
    private double toRadian(double angle)
    {
        return (Math.PI*angle)/FLAT_DEG;
    }
     
    /**
    * A private method that calculate the radius by given x,y coordinates
    * @param X the x coordinate 
    * @param Y the y coordinate
    */
    private void radiusCacu(double x,double y)
    {
            _radius =  Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
    }
    
    /**
    * A private method that calculate the angle by given x,y coordinates
    * @param X the x coordinate 
    * @param Y the y coordinate
    */
    private void alphaCacu(double x,double y)
    {
            if (x>=DEFAULT_VAL)
                 _alpha = toDegrees(Math.atan(y/x));
            if ((x==DEFAULT_VAL && y==DEFAULT_VAL) || (x==DEFAULT_VAL))
                _alpha=NINETHֹֹ_DEG;
    }    
    //constructors:
    /**
    * creates a representation from coordinates to alpha&radius
    * @param X the x coordinate 
    * @param Y the y coordinate
    */
     public Point (double x, double y){
        if (x<DEFAULT_VAL)
           x=DEFAULT_VAL;
        if (y<DEFAULT_VAL)
           y=DEFAULT_VAL;
        radiusCacu(x,y);
        alphaCacu(x,y);
    }
    
    /**
    * Constructor for objects of class Point. Copy constructor, construct a point using another point.
    * @param other - The point from which to construct the new object
    */
    public Point (Point other){
        if (other!=null){ //cheking the given object is initialized
            _radius=other._radius;
            _alpha=other._alpha;
        }
    }
    
    /**
    * This method returns the x coordinate of the point.
    * @return This method returns the x coordinate of the point. 
    */
    public double getX(){
        return Math.round((Math.cos(toRadian(_alpha))*_radius)*ROUND)/(double)ROUND;
    }
    
    /**
    * This method returns the y coordinate of the point.
    * @return The y coordinate of the point 
    */
    public double getY(){
        return Math.round((Math.sin(toRadian(_alpha))*_radius)*ROUND)/(double)ROUND;
    }
    
    /**
    * This method sets the x coordinate of the point. If the new x coordinate is negative the old x coordinate will remain unchanged.
    * @param X the x coordinate 
    */
    public void setX (double num){
        double tempY=getY();
        if (num>=DEFAULT_VAL){
            radiusCacu (num,tempY);
            alphaCacu(num,tempY);
        }
    }
    
    /**
    * This method sets the y coordinate of the point. If the new y coordinate is negative the old y coordinate will remain unchanged.
    * @param Y the y coordinate
    */
    public void setY (double num){
        double tempX=getX();
        if (num>=DEFAULT_VAL){
            radiusCacu (tempX,num);
            alphaCacu(tempX,num);
        }
    }
    
    /**
    * Returns a string representation of Point in the format (x,y).
    * @return String representation of the point 
    */   
    public String toString (){
        return "(" + getX() + "," + getY() + ")";
    }
    
    /**
    * Check if the given point is equal to this point.
    * @param other the point to compare
    * @return true if this point equals other point 
    */   
    public boolean equals (Point other) {
        return other._radius==_radius && other._alpha==_alpha;
    }
    
    /**
    * Check if this point is above a received point.
    * @param other the point to be compared with this point
    * @return true if this point is above other point 
    */   
    public boolean isAbove (Point other){
        return other.getY()<this.getY();
    }
    
    /**
    * Check if this point is below a received point.
    * @param other the point to be compared with this point
    * @return true if this point is under other point 
    */   
    public boolean isUnder (Point other){
        return other.isAbove(this);
    }
    
    /**
    * Check if this point is left of a received point
    * @param other the point to check if this point is left of other point
    * @return true if this point is left other point 
    */   
    public boolean isLeft (Point other){
        return other.getX()>this.getX();
    }
    
    /**
    * Check if this point is right of a received point
    * @param other the point to check if this point is right of other point
    * @return true if this point is right other point 
    */   
      public boolean isRight (Point other){
        return other.isLeft(this);
    }
    
    /**
    * Check the distance between this point and a given point
    * @param p the point to check the distace with this point
    * @return the caculated distance of the points 
    */
    public double distance (Point p){
        return Math.sqrt(Math.pow(this.getX()-p.getX(),2)+Math.pow(this.getY()-p.getY(),2));
    }
    
    /**
    * Moves a point
    * @param dx The delta of the sliding axis X
    * @param dy The delta of the sliding axis y
    * @return the new points after adding delta 
    */
    public void move (double dx,double dy){
        if ((this.getX()+dx) >=DEFAULT_VAL && (this.getY()+dy)>=DEFAULT_VAL){
            setX(getX()+dx);
            setY(getY()+dy);
        }
    }
}