package model;

/**
 *
 * @author Tzuhsien Liu
 * 
 *         Duplicate card exception call
 */

@SuppressWarnings("serial")
public class DuplicateCardException extends RuntimeException {
	public DuplicateCardException(String string) {
		super(string);
	}

}
