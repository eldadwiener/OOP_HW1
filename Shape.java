package homework1;

import java.awt.*;


/**
 * A Shape is an abstraction of a shape object. A typical Shape consists of
 * a set of properties: {location, color, shape, size}.
 * Shapes are mutable and cloneable.
 */
public abstract class Shape implements Cloneable {

	private Point location;
	private Color color;

	
	// Abs. Function:
	// represents a shape with:
	//		top left corner of the bounding rectangle of the shape at this.location and
	// 		color this.color
	
	// Rep. Invariant:
	// location != null && location.x >= 0 && location.y >= 0
	// color != null

	/**
	 * @effects Initializes this with a a given location and color.
	 */
    public Shape(Point location, Color color) {
    	this.location = (Point)location.clone();
    	this.color = color;
    	checkRep();
    }


    /**
     * @return the top left corner of the bounding rectangle of this.
     */
    public Point getLocation() {
    	checkRep();
    	return (Point)location.clone();
    }


    /**
     * @modifies this
     * @effects Moves this to the given location, i.e. this.getLocation()
     * 			returns location after call has completed.
     */
    public void setLocation(Point location) {
    	checkRep();
    	this.location = (Point)location.clone();
    	checkRep();
    }


    /**
     * @modifies this
     * @effects Resizes this so that its bounding rectangle has the specified
     * 			dimension.
     * 			If this cannot be resized to the specified dimension =>
     * 			this is not modified, throws ImpossibleSizeException
     * 			(the exception suggests an alternative dimension that is
     * 			 supported by this).
     */
    public abstract void setSize(Dimension dimension)
    	throws ImpossibleSizeException;

    
    /**
     * @return the bounding rectangle of this.
     */
    public abstract Rectangle getBounds();
  

    /**
     * @return true if the given point lies inside the bounding rectangle of
     * 		   this and false otherwise.
     */
    public boolean contains(Point point) {
    	checkRep();
    	return getBounds().contains(point);
    }
        

    /**
     * @return color of this.
     */
    public Color getColor() {
    	checkRep();
    	return color;
    }


    /**
     * @modifies this
     * @effects Sets color of this.
     */
    public void setColor(Color color) {
    	checkRep();
    	this.color = color;
    	checkRep();
    }


    /**
     * @modifies g
     * @effects Draws this onto g.
     */
    public abstract void draw(Graphics g);


    /**
     * @effects Creates and returns a copy of this.
     */
    public Object clone() {
    	checkRep();
    	Shape clonedShape = null;
    	try {
    		clonedShape = (Shape)super.clone();
    	}
    	catch (CloneNotSupportedException e) {
    		// this shouldn't happen, since this is Cloneable
    		assert (false);
		}
    	clonedShape.setLocation((Point)location.clone());
    	clonedShape.setColor(color);
    	return clonedShape;
    }
    
	/**
	 * @effects checks the representation invariant of this, and aborts if it is fault. (assert)
	 */
    private void checkRep() {
    	assert ( (color != null) && (location != null)
    			&& (location.getX() >= 0) && (location.getY() >= 0) );
    }
}