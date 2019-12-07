package homework1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * A LocationChangingNumberedOval is a LocationChangingOval which also contains an oval ID,
 * and displays it with the oval when drawn. 
 */
public class LocationChangingNumberedOval extends LocationChangingOval {

	// Abs. Function:
	// represents a LocationChangingOval which has a unique ID drawn with it, defined by this.ID.
	// this.ID is defined by the value of count when this is created.

	// Rep. Invariant:
	// ID > 0, count >= 0
	
	static private int count = 0;
	private int ID;
	
	public LocationChangingNumberedOval(Point location, Color color, int height, int width) {
		super(location, color, height, width);
		++count;
		ID = count;
		checkRep();
	}
	

    /**
     * @modifies g
     * @effects Draws this onto g.
     */
	@Override
	public void draw(Graphics g) {
		checkRep();
		super.draw(g);
		// find middle of bounding square
		Rectangle frame = getBounds();
		g.setColor(Color.WHITE);
		g.drawString(Integer.toString(ID), (int)frame.getCenterX(), (int)frame.getCenterY());
		checkRep();
	}
	
    /**
     * @effects Creates and returns a copy of this.
     */
	@Override
	public Object clone() {
		checkRep();
    	LocationChangingNumberedOval clonedOval = null;
    	clonedOval = (LocationChangingNumberedOval)super.clone();
    	clonedOval.ID = ID;
    	return clonedOval;
	}

	/**
	 * @effects checks the representation invariant of this, and aborts if it is fault. (assert)
	 */
    private void checkRep() {
    	assert (count >= 0 && ID > 0);
    	}

}
