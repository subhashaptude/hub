package com.modetransportation.batch.exception;

import java.io.PrintWriter;
import java.io.Writer;

public class IndentingWriter extends PrintWriter
{
	int indentLevel;
	private static final char TAB_CHAR = '\t';

	/**
	 * The constructor sets the base writer to use and the
	 *
	 */
	public IndentingWriter( int indent, Writer writer )
	{
		super( writer );
		indentLevel = indent;
	}

	/**
	 * This method will be called whenever a new line character needs to be inserted.
	 * We will call the base class println() method and then increase the indent.
	 */
	public void println()
	{
		super.println();

		for ( int i = 0; i < indentLevel; i++ )
		{
			super.print( TAB_CHAR );
		}
	}
}
