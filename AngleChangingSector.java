package homework1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;


/**
 * An AngleChangingSector is an arc Shape that can be drawn using the draw method.
 * the size of the AngleChangingSector can be set using it's setSize method, and the rectangle bounds
 * are received by it's getBound method. 
 * using the step method will increase the starting angle for the arc by 1 degree.
 */
public class AngleChangingSector extends Shape implements Animatable {

	// Abs. Function:
	// represents a AngleChangingSector that can be drawn.
	// The size of the bounding rectangle is defined by this.dim
	// The start angle of the arc is defined by this.startingAngle
	// and the end of the arc is defined at (this.startingAngle + this.arcAngle)

	// Rep. Invariant:
	// dim.width > 0, dim.height > 0, startinAngle >= 0, arcAngle > 0
	
	private final int FULLROTATION = 360;
	private int startingAngle;
	private int arcAngle;
	private Dimension dim;

	/**
	 * @requires height > 0, width > 0, startAngle >= 0, arcAngle > 0.
	 * @effects Initializes an Arc with a given location, color, height, width, startingAngle and arcAngle.
	 */

	public AngleChangingSector(Point location, Color color, int height, int width, int startAngle, int arcAngle) {
		super(location, color);
		assert (height > 0 && width > 0 && startAngle >= 0 && arcAngle > 0): 
			"Bad input parameters to AngleChangingSector constructor";
		dim = new Dimension(width, height);
		startingAngle = startAngle % FULLROTATION;
		this.arcAngle = arcAngle % FULLROTATION;
		checkRep();
	}

	/**
	 * @modifies this
	 * @effects increases the starting angle of the arc by 1 degree.
	 * 			if a full rotation has been completed (e.g. angle = 360)
	 * 			then the starting angle will wrap around to 0.
	 */
	@Override
	public void step(Rectangle bound) {
		checkRep();
		startingAngle = (startingAngle + 1) % FULLROTATION;
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
		g.fillArc((int)topLeft.getX(), (int)topLeft.getY(), (int)dim.getWidth(), (int)dim.getHeight(),
				startingAngle, arcAngle);
		checkRep();
	}

	/**
	 * @effects checks the representation invariant of this, and aborts if it is fault. (assert)
	 */
    private void checkRep() {
    	assert (dim.getHeight() > 0 && dim.getWidth() > 0 &&
    			startingAngle >= 0 && arcAngle > 0);
	}
}
