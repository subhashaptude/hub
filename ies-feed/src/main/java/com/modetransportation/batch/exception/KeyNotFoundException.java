package com.modetransportation.batch.exception;

public class KeyNotFoundException extends RuntimeException {

    /**
	 * Constructs a KeyNotFoundException with the specified message.
	 */
	public KeyNotFoundException(String errmsg) {
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
		return "KeyNotFoundException: " + getMessage();
	}

}
