package com.modetransportation.batch.exception;

public class NotInitializedException extends RuntimeException {

    /**
	 * Constructs a NotInitializedException with the specified message.
	 */
	public NotInitializedException(String errmsg) {
		super(errmsg);
    }

	/**
	 * Returns a short description of this object.
	 *
	 * @return concatenation of three strings:
	 *         The name of this class
	 *         ": " (a colon and a space)
	 *         The result of the getMessage() method for this object
	 */
	public String toString() {
		return "NotInitializedException: " + getMessage();
	}

}
