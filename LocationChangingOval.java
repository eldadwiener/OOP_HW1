package homework1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * A LocationChangingOval is a LocationChangingShape that can be drawn as an Oval using the draw method.
 * the size of the LocationChangingOval can be set using it's setSize method, and the Oval's rectangle bounds
 * are received by it's getBound method. 
 */
public class LocationChangingOval extends LocationChangingShape {

	// Abs. Function:
	// represents a LocationChangingShape that can be drawn as an Oval which is bounded by the
	// rectangle defined by this.dim.

	// Rep. Invariant:
	// dim.width > 0, dim.height > 0
	
	private Dimension dim;
	
	/**
	 * @requires height > 0, width > 0.
	 * @effects Initializes an Oval with a given location, color, height and width. Each
	 *          of the horizontal and vertical velocities of the new
	 *          object is set to a random integral value i such that
	 *          -5 <= i <= 5 and i != 0
	 */
	public LocationChangingOval(Point location, Color color, int height, int width) {
		super(location, color);
		dim = new Dimension(width, height);
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
     * @throws ImpossibleSizeException if the new size is illegal
     */
	@Override
	public void setSize(Dimension dimension) throws ImpossibleSizeException {
		checkRep();
		if ( dimension.getHeight() <= 0 || dimension.getWidth() <= 0)
			throw new ImpossibleSizeException((Dimension)dim.clone());

		dim.setSize(dimension);
		checkRep();
	}

    /**
     * @return the bounding rectangle of this.
     */
	@Override
	public Rectangle getBounds() {
		checkRep();
		return new Rectangle(getLocation(),(Dimension)dim.clone());
	}

    /**
     * @modifies g
     * @effects Draws this onto g.
     */
	@Override
	public void draw(Graphics g) {
		checkRep();
		Point topLeft = getLocation();
		g.setColor(getColor());
		g.fillOval((int)topLeft.getX(), (int)topLeft.getY(), (int)dim.getWidth(), (int)dim.getHeight());
		checkRep();
	}
	
    /**
     * @effects Creates and returns a copy of this.
     */
	@Override
	public Object clone() {
		checkRep();
    	LocationChangingOval clonedOval = null;
    	clonedOval = (LocationChangingOval)super.clone();
    	//deep copy
    	clonedOval.dim = (Dimension)dim.clone();
    	return clonedOval;
	}

	/**
	 * @effects checks the representation invariant of this, and aborts if it is fault. (assert)
	 */
    private void checkRep() {
    	assert (dim.getHeight() > 0 && dim.getWidth() > 0) : "failed at LocationChangingOval.checkRep()";
    	}
}
