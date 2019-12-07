package homework1;

import java.awt.Dimension;
/**
 * ImpossibleSizeException is Thrown to indicate that the required size 
 * is not legal, and offers a legal size 
 */
@SuppressWarnings("serial")
public class ImpossibleSizeException extends Exception {
	
	private final Dimension legalDimension;

	/**
	 * @requires legal Dimension for the one who throw thus exception  
	 * @effects Initializes this with a a given Dimension.
	 */
	public ImpossibleSizeException(Dimension legalDimension) {
		this.legalDimension = legalDimension;
	}
	
	/**
	 * @return a legal Dimension
	 */
	public Dimension getDimension () {
		return legalDimension;
	}
}
