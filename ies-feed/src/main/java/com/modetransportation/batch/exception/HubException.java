package com.modetransportation.batch.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Date;

import com.modetransportation.batch.util.ModeConstants;

public class HubException extends Exception
{
	/**
	 * Static variables
	 */
	public static final int SEVERITY_CRITICAL = ModeConstants.SEVERITY_CRITICAL;
	public static final int SEVERITY_HIGH = ModeConstants.SEVERITY_HIGH;
	public static final int SEVERITY_MEDIUM = ModeConstants.SEVERITY_MEDIUM;
	public static final int SEVERITY_LOW = ModeConstants.SEVERITY_LOW;
	public static final int SEVERITY_WARNING = ModeConstants.SEVERITY_WARNING;

	// Instance Variable.
    private Date            exceptionDateTime = null;
    private int             severity = ModeConstants.SEVERITY_LOW;
    private String          exceptionMessage = null;
    private Throwable       baseException = null;
    private static String   programName = null;

    // This can contain any useful object that will help in debugging the exception
    private Object          exceptionScope = null;

    /**
     * The no argument constructor.
     */
    public HubException()
    {
        this( "", SEVERITY_LOW, null, null );
    }

    /**
     * This Constructor just takes the message.
     *
     * @param message The message that describes this exception
     */
    public HubException( String message )
    {
        this( message, SEVERITY_LOW, null, null );
    }

    /**
     * The Constructor takes in severity and a message.
     *
     * @param message The message that describes this exception
     * @param exceptionSeverity This identifies the severity of the exception
     */
    public HubException( String message, int exceptionSeverity )
    {
        this( message, exceptionSeverity, null, null );
    }

    /**
     * The Constructor takes in a message and a base exception
     *
     * @param message The message that describes this exception
     * @param baseExc Contains the Base exception that may be the cause of this exception
     */
    public HubException( String message, Throwable baseExc )
    {
        this( message, SEVERITY_LOW, baseExc, null );
    }

    /**
     * The Constructor takes a base exception
     *
     * @param baseExc Contains the Base exception that may be the cause of this exception
     */
    public HubException( Throwable baseExc )
    {
        this( null, SEVERITY_LOW, baseExc, null );
    }

	/**
	 * The Constructor takes in several parameters.
	 *
	 * @param baseExc Contains the Base exception that may be the cause of this exception
	 * @param exceptionSeverity This identifies the severity of the exception
	 * @param message The message that describes this exception
	 */
	public HubException( Throwable baseExc, int exceptionSeverity, String message )
	{
        this( message, exceptionSeverity, baseExc, null );
	}

    /**
     * The Constructor takes in message, severity and base exception.
     *
     * @param message The message that describes this exception
     * @param exceptionSeverity This identifies the severity of the exception
     * @param baseExc Contains the Base exception that may be the cause of this exception
     */
    public HubException( String message, int exceptionSeverity, Throwable baseExc )
    {
        this( message, exceptionSeverity, baseExc, null );
    }


    /**
     * The Constructor takes in several parameters.
     *
     * @param message The message that describes this exception
     * @param exceptionSeverity This identifies the severity of the exception
     * @param baseExc Contains the Base exception that may be the cause of this exception
     * @param scope the business object in whose scope this exception occured.
     */
    public HubException( String message, int exceptionSeverity, Throwable baseExc, Object scope )
    {
        super( message );
        exceptionMessage = message;
        severity = exceptionSeverity;
        baseException = baseExc;
        exceptionScope = scope;

        //Set the current time to be the time this exception has happened
        exceptionDateTime = new Date();
    }

    /**
     * Returns the base exception that may have caused this exception
     *
     * @return Base exception on which this exception is built.
     */
    public Throwable getBaseException()
    {
        return baseException;
    }

    /**
     * Returns the exception scope. This can be any object that can help in
     * debugging the exception. Its upto the application to convert the object
     * to its actual type and handle it.
     *
     * @return the exception of the object
     */
    public Object getExceptionScope()
    {
        return exceptionScope;
    }

    /**
     * Returns the exception message associated with this exception
     *
     * @return message associated with the current exception.
     */
    public String getMessage()
    {
        return exceptionMessage;
    }

    /**
     * Returns the current Program's Name
     *
     * @return name of the program that generated the exception
     */
    public String getProgramName()
    {
        return programName;
    }

    /**
     * Returns the severity for this exception.
     *
     * @return the severity of the exception
     */
    public int getSeverity()
    {
        return severity;
    }

    /**
     * Set the severity for this exception.
     */
    public void setSeverity(int s)
    {
        severity = s;
    }
    
    /**
     * Returns the severity as a String value.
     *
     * @param theSeverity The severity
     * @return The string version of the severity.
     */
    public static String getSeverityAsString( int theSeverity )
    {
        try
        {
            return ModeConstants.SEVERITIES[ theSeverity ];
        }
        catch ( ArrayIndexOutOfBoundsException e )
        {
            return "Unknown";
        }
    }

    /**
     * Returns the string version of this object
     * @return the string version of the HubException object
     */
    public String getAsString()
    {
        StringBuffer excBuf = new StringBuffer();
        String message = exceptionMessage;

        if ( message == null || message.length() == 0 )
        {
            message = "Unknown Error";
        }

        String baseExceptionMessage = "None";
        if ( baseException != null )
        {
            baseExceptionMessage = baseException.getClass().getName();
        }

        String  strSeverity = getSeverityAsString( severity );
        excBuf.append( "Exception: " + getClass().getName());
        excBuf.append( " Program Name: " );
        excBuf.append( ( programName != null ) ? programName : "Unknown" );
        excBuf.append( " [").append( exceptionDateTime ).append("]" );
        excBuf.append( " [Severity:" ).append( strSeverity).append("] " ).append( message );
        excBuf.append( "[Base Exception: ").append( baseExceptionMessage ).append("]" );

        return excBuf.toString();
    }

    /**
     * Sets Program Name to this exception.
     *
     * @param   value   name of the program to be set to be used to handle exception
     */
    public void setProgramName( String value )
    {
        programName = value;
    }

    /**
     * Prints this exception and its backtrace to the specified stream.
     * Overridden from java.lang.Throwable to include printing stack trace
     * of base exception
     *
     * @param   s   PrintStream to write the stack trace to
     */
    public void printStackTrace(PrintStream s)
    {
        super.printStackTrace(s);
        if ( baseException != null )
        {
            baseException.printStackTrace(s);
        }
    }

    /**
     * Prints this exception and its backtrace to the specified Writer.
     * Overridden from java.lang.Throwable to include printing stack trace
     * of base exception
     *
     * @param   s   PrintWriter to write the stack trace to
     */
    public void printStackTrace(PrintWriter s)
    {
        super.printStackTrace(s);
        if ( baseException != null )
        {
            baseException.printStackTrace(s);
        }
    }

    /**
     * Prints this exception and its backtrace to the standard error stream.
     * Overridden from java.lang.Throwable to include printing stack trace
     * of base exception
     */
    public void printStackTrace()
    {
        super.printStackTrace();
        if ( baseException != null )
        {
            baseException.printStackTrace();
        }
    }
}
