package homework1;

import java.awt.*;
import java.util.Random;

enum BoundsOverflow{
	NONE, HORIZONAL, VERTICAL, BOTH; 
}
/**
 * A LocationChaningShape is a Shape that can change its location using its step()
 * method. A LocationChaningShape has a velocity property that determines the speed
 * of location changing.
 * Thus, a typical LocationChaningShape consists of the following set of
 * properties: {location, color, shape, size, velocity}
 */
public abstract class LocationChangingShape extends Shape implements Animatable {

	// TODO: Write Abstraction Function
	
	// TODO: Write Representation Invariant
	
	private int horizonalVelocity;
	private int verticalVelocity;
	
	private static final int MIN_VELOCUTY = -5;
	private static final int VELOCUTY_RANGE = 10;
	private static final int NON_VELOCITY = 0;
	
	/**
	 * @effects Initializes this with a a given location and color. Each
	 *          of the horizontal and vertical velocities of the new
	 *          object is set to a random integral value i such that
	 *          -5 <= i <= 5 and i != 0
	 */
	LocationChangingShape(Point location, Color color) {
    	super(location, color);
    	Random rand = new Random();
    	do {
    		horizonalVelocity = MIN_VELOCUTY + rand.nextInt(VELOCUTY_RANGE);
    	} while (horizonalVelocity == NON_VELOCITY );
    	do {
    		verticalVelocity = MIN_VELOCUTY + rand.nextInt(VELOCUTY_RANGE);
    	} while (verticalVelocity == NON_VELOCITY );
    }

    /**
     * @return the horizontal velocity of this.
     */
    public int getVelocityX() {
    	return horizonalVelocity;
    }

    /**
     * @return the vertical velocity of this.
     */
    public int getVelocityY() {
    	return verticalVelocity;
    }

    /**
     * @modifies this
     * @effects Sets the horizontal velocity of this to velocityX and the
     * 			vertical velocity of this to velocityY.
     */
    public void setVelocity(int velocityX, int velocityY) {
    	horizonalVelocity = velocityX;
    	verticalVelocity = velocityY;
    }


    /**
     * @modifies this
     * @effects Let p = location
     * 				v = (vx, vy) = velocity
     * 				r = the bounding rectangle of this
     *         	If (part of r is outside bound) or (r is within bound but
     *          	adding v to p would bring part of r outside bound) {
     * 				If adding v to p would move r horizontally farther away
     * 				from the center of bound,
     * 					vx = -vx
     * 				If adding v to p would move r vertically farther away
     * 				from the center of bound,
     * 					vy = -vy
     *          }
     * 			p = p + v
     */
    public void step(Rectangle bound) {
    	BoundsOverflow  boundsOverflowResult = calcBounds (bound);
    	switch (boundsOverflowResult) {
    	case BOTH:
    		horizonalVelocity *= -1;
    		verticalVelocity *= -1;
    		break;
    	case HORIZONAL:
    		horizonalVelocity *= -1;
    		break;
    	case VERTICAL:
    		verticalVelocity *= -1;
    		break;
    	default:
    		break;	
    	}
    	Point newLocation = new Point((int)(getLocation().getX()) + horizonalVelocity,
    								  (int)(getLocation().getY()) + verticalVelocity);
    	setLocation(newLocation);
    }
    
    private BoundsOverflow calcBounds(Rectangle bound) {
    	//prepare the parameters
    	boolean xOverflow = false;
    	boolean yOverflow = false;
    	double boundHeight =  bound.getHeight();
    	double boundWidth =  bound.getWidth();
    	double shapeHeight =  getBounds().getHeight();
    	double shapeWidth =  getBounds().getWidth();
    	double xLocation =  getLocation().getX();
    	double yLocation =  getLocation().getY();
    	
    	//check for X overflow
    	if ((xLocation < 0) || (xLocation + horizonalVelocity < 0) ||
    		(xLocation > boundWidth - shapeWidth) || 
    		(xLocation + horizonalVelocity > boundWidth - shapeWidth)) {
    		xOverflow = true;
    	}
    	//check for Y overflow
    	if ((yLocation < 0) || (yLocation + verticalVelocity < 0) ||
        	(yLocation > boundHeight - shapeHeight) || 
        	(yLocation + verticalVelocity > boundHeight - shapeHeight)) {
       		yOverflow = true;
        }
    	//calculate the return value
    	if (xOverflow && yOverflow) {//if both
    		return BoundsOverflow.BOTH;
    	}
    	if (xOverflow) {//if just X
    		return BoundsOverflow.HORIZONAL;
    	}
    	if (yOverflow) {//if just Y
    		return BoundsOverflow.VERTICAL;
    	}
    	//else
    	return BoundsOverflow.NONE;
    }
    
    /**
     * @effects Creates and returns a copy of this.
     */
    public Object clone() {
}
