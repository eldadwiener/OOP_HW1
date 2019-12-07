package homework1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

/**
 * A LocationChangingRoundedRetangle is a LocationChangingRetangle which rounded edges. 
 */

public class LocationChangingRoundedRetangle extends LocationChangingRectangle {
	// Abs. Function:
	// represents a LocationChangingRetangle which rounded edges

	// Rep. Invariant:
	// none
	
	/**
	 * @requires height > 0, width > 0.
	 * @effects Initializes an rounded edges rectangle with a given location, color, height and width.
	 */ 
	public LocationChangingRoundedRetangle(Point location, Color color, int height, int width) {
		super(location, color, height, width);
	}
	
    /**
     * @modifies g
     * @effects Draws this onto g.
     */
	@Override
	public void draw(Graphics g) {
		Point topLeft = getLocation();
		g.setColor(getColor());
		Dimension dim = getDimension();
		g.fillRoundRect((int)topLeft.getX(), (int)topLeft.getY(), (int)dim.getWidth(), (int)dim.getHeight(), 1, 1);
	}
}
